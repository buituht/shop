package vn.iotstar.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import vn.iotstar.models.ProductModel;
import vn.iotstar.services.ProductService; 
import vn.iotstar.services.impl.ProductServiceImpl; 

@WebServlet(urlPatterns = {"/products"}) // Ánh xạ tới các đường dẫn phổ biến
public class UserProductListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    ProductService productService = new ProductServiceImpl(); 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy danh sách sản phẩm
        List<ProductModel> products = productService.findAll();
        
        // Đặt danh sách vào request
        req.setAttribute("products", products);
        
        // Chuyển hướng đến trang JSP trong thư mục views/web
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/list-products.jsp");
        dispatcher.forward(req, resp);
    }
}