package vn.iotstar.controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.services.CategoryService;
import vn.iotstar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/delete" })
public class CategoryDeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                cateService.delete(id);
                System.out.println("Deleted category with ID: " + id); // In ra console để theo dõi
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu id không phải là số
                System.err.println("Lỗi: ID không phải là một số hợp lệ. ID: " + idParam);
                e.printStackTrace();
            }
        }
        
        // Luôn chuyển hướng người dùng về trang danh sách
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}
