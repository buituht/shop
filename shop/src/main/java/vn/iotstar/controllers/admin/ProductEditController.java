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


@WebServlet(urlPatterns = {"/admin/product/edit"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class ProductEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = Constant.DIR;
    ProductService productService = new ProductServiceImpl();
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("id");
        
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            ProductModel product = productService.get(id);
            
            if (product != null) {
            	
                req.setAttribute("product", product);
                req.setAttribute("categories", cateService.findAll());
                RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-product.jsp");
                dispatcher.forward(req, resp);
                
            } else {
            	
                resp.sendRedirect(req.getContextPath() + "/admin/product/list");
                
            }
        } else {
        	
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int cateId = Integer.parseInt(req.getParameter("cateId"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String description = req.getParameter("description");
            String oldImage = req.getParameter("oldImage");
            Part part = req.getPart("image");
            
            String fileName = oldImage;
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
            product.setId(id);
            product.setCateId(cateId);
            product.setName(name);
            product.setPrice(price);
            product.setImage(fileName);
            product.setDescription(description);
            
            productService.edit(product);
            
            resp.sendRedirect(req.getContextPath() + "/admin/product/list");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("Có lỗi xảy ra: " + e.getMessage());
        }
    }
}