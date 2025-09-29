package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.models.CartItemModel;
import vn.iotstar.models.ProductModel;
import vn.iotstar.services.ProductService;
import vn.iotstar.services.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/cart/add"})
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CART_SESSION = "cart";
    
    ProductService productService = new ProductServiceImpl(); 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        try {
            // 1. Lấy thông tin sản phẩm và  số lượng từ form
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            
            // 2. Lấy chi tiết sản phẩm từ Database
            ProductModel product = productService.get(productId);
            
            if (product == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Sản phẩm không tồn tại.");
                return;
            }

            // 3. Lấy hoặc tạo giỏ hàng (Cart) từ Session
            HttpSession session = req.getSession();
            @SuppressWarnings("unchecked")
            List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute(CART_SESSION);
            
            if (cart == null) {
                cart = new ArrayList<>();
            }
            
            // 4. Kiểm tra sản phẩm đã có trong giỏ hàng chưa
            boolean found = false;
            for (CartItemModel item : cart) {
                if (item.getProductId() == productId) {
                    // Nếu có rồi, cập nhật số lượng
                    item.setQuantity(item.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }
            
            // 5. Nếu chưa có, tạo item mới và thêm vào giỏ hàng
            if (!found) {
                CartItemModel newItem = new CartItemModel(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    quantity,
                    product.getImage()
                );
                cart.add(newItem);
            }
            
            // 6. Lưu lại giỏ hàng vào Session
            session.setAttribute(CART_SESSION, cart);
            
            // 7. Chuyển hướng người dùng về trang chi tiết sản phẩm hoặc trang giỏ hàng
            // Chuyển hướng về trang giỏ hàng để người dùng thấy kết quả
            resp.sendRedirect(req.getContextPath() + "/cart"); 

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dữ liệu gửi lên không hợp lệ.");
        }
    }
}