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
import vn.iotstar.models.CartItemModel; // Giả định tên Model giỏ hàng của bạn

@WebServlet(urlPatterns = {"/cart"}) // Ánh xạ cả hai đường dẫn phổ biến
public class CartViewController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CART_SESSION = "cart";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        // 1. Lấy giỏ hàng từ Session
        @SuppressWarnings("unchecked")
        List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute(CART_SESSION);

        // 2. Đặt giỏ hàng vào request (dù nó rỗng hay có dữ liệu)
        req.setAttribute("cart", cart);

        // 3. Tính tổng tiền giỏ hàng (tùy chọn)
        double total = 0;
        if (cart != null) {
            for (CartItemModel item : cart) {
                total += item.getPrice() * item.getQuantity();
            }
        }
        req.setAttribute("cartTotal", total);

        // 4. Chuyển hướng đến trang JSP hiển thị giỏ hàng
        // Bạn cần tạo file này trong thư mục views/web
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/cart.jsp"); 
        dispatcher.forward(req, resp);
    }
}