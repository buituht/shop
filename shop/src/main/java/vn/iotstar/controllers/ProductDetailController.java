package vn.iotstar.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import vn.iotstar.models.ProductModel;
import vn.iotstar.services.ProductService; 
import vn.iotstar.services.impl.ProductServiceImpl; 

@WebServlet(urlPatterns = {"/product/detail"})
public class ProductDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Giả định bạn có ProductService với phương thức get(int id)
    ProductService productService = new ProductServiceImpl(); 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. Lấy ID sản phẩm từ tham số URL
            int productId = Integer.parseInt(req.getParameter("id"));
            
            // 2. Gọi Service để lấy thông tin chi tiết sản phẩm
            // Giả định ProductService có phương thức get(int id) tương tự như CategoryService
            ProductModel product = productService.get(productId); 
            
            if (product != null) {
                // 3. Đặt đối tượng sản phẩm vào request
                req.setAttribute("product", product);
                
                // 4. Chuyển hướng đến trang JSP hiển thị chi tiết
                RequestDispatcher dispatcher = req.getRequestDispatcher("/views/product-detail.jsp");
                dispatcher.forward(req, resp);
            } else {
                // Xử lý trường hợp không tìm thấy sản phẩm (ví dụ: hiển thị trang 404 hoặc thông báo lỗi)
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sản phẩm này.");
            }
        } catch (NumberFormatException e) {
            // Xử lý trường hợp ID không hợp lệ
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ.");
        }
    }
}