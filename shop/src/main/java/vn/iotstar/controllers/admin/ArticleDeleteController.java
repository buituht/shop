package vn.iotstar.controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.services.ArticleService;
import vn.iotstar.services.impl.ArticleServiceImpl;

@WebServlet(urlPatterns = {"/admin/article/delete"})
public class ArticleDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ArticleService articleService = new ArticleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String articleIdStr = req.getParameter("id");
        
        if (articleIdStr != null) {
            try {
                int articleId = Integer.parseInt(articleIdStr);
                
                // Gọi Service để xóa
                articleService.removeArticle(articleId);
                
                req.getSession().setAttribute("message", "Xóa bài viết thành công!");
            } catch (NumberFormatException e) {
                req.getSession().setAttribute("message", "ID bài viết không hợp lệ.");
            }
        }
        
        // Chuyển hướng về trang danh sách
        resp.sendRedirect(req.getContextPath() + "/admin/article/list");
    }
}