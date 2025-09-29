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

import vn.iotstar.models.CategoryModel;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = {"/admin/category/add"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        String name = req.getParameter("cateName");
        String statusStr = req.getParameter("status"); 
        Part filePart = req.getPart("image");
        
        String fileName = null;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = filePart.getSubmittedFileName();
            if (fileName != null && !fileName.isEmpty()) {
                String timestamp = String.valueOf(System.currentTimeMillis());
                fileName = timestamp + "_" + fileName;
                
                String uploadPath = Constant.DIR + File.separator + fileName; 
                filePart.write(uploadPath);
            }
        }
        
        CategoryModel category = new CategoryModel();
        category.setCateName(name);
        category.setImage(fileName);
        
        int status = 1; 
        try {
            status = Integer.parseInt(statusStr);
        } catch (NumberFormatException e) {
            // Sử dụng giá trị mặc định nếu parsing thất bại
        }
        category.setStatus(status); 
        
        categoryService.insert(category);
        
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}