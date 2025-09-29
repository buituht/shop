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

@WebServlet(urlPatterns = {"/admin/category/edit"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class CategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy ID: Tên tham số URL là 'id'
        int id = Integer.parseInt(req.getParameter("id"));
        
        CategoryModel category = categoryService.get(id);
        
        req.setAttribute("category", category);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        // Lấy ID: Tên tham số hidden trong form là 'catId'
        int id = Integer.parseInt(req.getParameter("catId"));
        String name = req.getParameter("cateName");
        String oldImage = req.getParameter("oldImage"); 
        String statusStr = req.getParameter("status"); 
        Part filePart = req.getPart("image");
        
        String fileName = oldImage; // Mặc định giữ lại tên ảnh cũ
        
        if (filePart != null && filePart.getSize() > 0) {
            String newFileName = filePart.getSubmittedFileName();
            if (newFileName != null && !newFileName.isEmpty()) {
                
                // Xóa ảnh cũ nếu nó tồn tại
                if (oldImage != null && !oldImage.isEmpty() && new File(Constant.DIR + File.separator + oldImage).exists()) {
                    new File(Constant.DIR + File.separator + oldImage).delete();
                }
                
                // Lưu ảnh mới
                String timestamp = String.valueOf(System.currentTimeMillis());
                fileName = timestamp + "_" + newFileName;
                String uploadPath = Constant.DIR + File.separator + fileName; 
                filePart.write(uploadPath);
            }
        }
        
        CategoryModel category = new CategoryModel();
        category.setCatId(id);
        category.setCateName(name);
        category.setImage(fileName);
        
        int status = Integer.parseInt(statusStr);
        category.setStatus(status);
        
        categoryService.edit(category);
        
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}