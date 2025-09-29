package vn.iotstar.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Date; // Dùng cho ngày đặt hàng

import vn.iotstar.models.CartItemModel;
import vn.iotstar.models.OrderModel; // Giả định OrderModel
import vn.iotstar.services.OrderService; // Giả định OrderService
import vn.iotstar.services.impl.OrderServiceImpl; // Giả định OrderServiceImpl

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CART_SESSION = "cart";
    
    OrderService orderService = new OrderServiceImpl();

    // Xử lý hiển thị trang thanh toán
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        @SuppressWarnings("unchecked")
        List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute(CART_SESSION);

        if (cart == null || cart.isEmpty()) {
            // Nếu giỏ hàng trống, chuyển hướng về trang giỏ hàng
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        // Tính tổng tiền giỏ hàng
        double total = 0;
        for (CartItemModel item : cart) {
            total += item.getPrice() * item.getQuantity();
        }
        
        req.setAttribute("cart", cart);
        req.setAttribute("cartTotal", total);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/checkout.jsp");
        dispatcher.forward(req, resp);
    }

    // Xử lý Đặt hàng (Place Order)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();

        @SuppressWarnings("unchecked")
        List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute(CART_SESSION);

        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        try {
            // 1. Lấy thông tin khách hàng từ form
            String fullName = req.getParameter("fullName");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String address = req.getParameter("address");
            String paymentMethod = req.getParameter("paymentMethod");

            // 2. Tính tổng tiền
            double totalAmount = 0;
            for (CartItemModel item : cart) {
                totalAmount += item.getPrice() * item.getQuantity();
            }

            // 3. Tạo đối tượng OrderModel
            OrderModel order = new OrderModel();
            
            // Giả định User ID (nếu chưa đăng nhập, dùng ID mặc định hoặc đặt là NULL)
            // Nếu có chức năng đăng nhập, lấy từ Session: int userId = (int) session.getAttribute("userId");
            // Tạm đặt userId là 0 (hoặc NULL nếu DB cho phép)
            order.setUserId(0); 
            
            order.setFullName(fullName);
            order.setPhone(phone);
            order.setEmail(email);
            order.setShippingAddress(address);
            order.setPaymentMethod(paymentMethod);
            order.setTotalAmount(totalAmount);
            order.setOrderDate(new Date()); 
            order.setStatus(1); // 1: Đang chờ xử lý

            // 4. Lưu Order và Order Details vào DB (Đây là logic quan trọng nhất)
            boolean success = orderService.placeOrder(order, cart);

            if (success) {
                // 5. Xóa giỏ hàng khỏi Session
                session.removeAttribute(CART_SESSION);

                // 6. Chuyển hướng đến trang thông báo thành công
                resp.sendRedirect(req.getContextPath() + "/checkout/success");
            } else {
                // Nếu đặt hàng thất bại
                req.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình đặt hàng. Vui lòng thử lại.");
                doGet(req, resp); // Quay lại trang checkout với thông báo lỗi
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", "Lỗi hệ thống: " + e.getMessage());
            doGet(req, resp);
        }
    }
}