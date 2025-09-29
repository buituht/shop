package vn.iotstar.controllers.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import vn.iotstar.models.ProductModel;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.ProductService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import vn.iotstar.services.impl.ProductServiceImpl;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = {"/admin/product/add"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class ProductAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = Constant.DIR;
    ProductService productService = new ProductServiceImpl();
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", cateService.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-product.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            int cateId = Integer.parseInt(req.getParameter("cateId"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String description = req.getParameter("description");
            Part part = req.getPart("image");
            
            String fileName = null;
            if (part != null && part.getSize() > 0) {
                String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String ext = "";
                int dotIndex = originalFileName.lastIndexOf('.');
                if (dotIndex > 0 && dotIndex < originalFileName.length() - 1) {
                    ext = originalFileName.substring(dotIndex + 1);
                }
                fileName = System.currentTimeMillis() + "." + ext;
                part.write(UPLOAD_DIRECTORY + File.separator + fileName);
            }

            ProductModel product = new ProductModel();
            product.setCateId(cateId);
            product.setName(name);
            product.setPrice(price);
            product.setImage(fileName);
            product.setDescription(description);
            
            productService.insert(product);
            
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Có lỗi xảy ra: " + e.getMessage());
        }
    }
}