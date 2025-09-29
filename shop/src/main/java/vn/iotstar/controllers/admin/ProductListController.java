package vn.iotstar.controllers.admin;

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

@WebServlet(urlPatterns = {"/admin/product/list"})
public class ProductListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductModel> products = productService.findAll();
        req.setAttribute("products", products);
        // tạo biến imgUrl
        req.setAttribute("imgUrl", req.getContextPath() + "/product_images/");
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-product.jsp");
        dispatcher.forward(req, resp);
    }
}