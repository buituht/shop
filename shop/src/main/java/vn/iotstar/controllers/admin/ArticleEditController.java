package vn.iotstar.controllers.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.models.ArticleModel;
import vn.iotstar.services.ArticleService;
import vn.iotstar.services.impl.ArticleServiceImpl;

@WebServlet(urlPatterns = {"/admin/article/edit"})
public class ArticleEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ArticleService articleService = new ArticleServiceImpl();

    // Hiển thị Form với dữ liệu cũ
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String articleIdStr = req.getParameter("id");
        
        if (articleIdStr != null) {
            try {
                int articleId = Integer.parseInt(articleIdStr);
                // Lấy Article hiện tại từ DB
                ArticleModel article = articleService.getArticleById(articleId);
                
                if (article != null) {
                    req.setAttribute("article", article);
                } else {
                    req.setAttribute("message", "Không tìm thấy bài viết để chỉnh sửa.");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("message", "ID bài viết không hợp lệ.");
            }
        }
        // Chuyển tiếp đến trang form
        req.getRequestDispatcher("/views/admin/article-edit.jsp").forward(req, resp);
    }

    // Xử lý Cập nhật Bài viết
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // 1. Lấy dữ liệu và ID cần update
        String articleIdStr = req.getParameter("articleId"); 
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String summary = req.getParameter("summary");
        String status = req.getParameter("status");

        if (articleIdStr == null || articleIdStr.isEmpty()) {
            req.getSession().setAttribute("message", "Lỗi: Không tìm thấy ID bài viết để cập nhật.");
            resp.sendRedirect(req.getContextPath() + "/admin/article/list");
            return;
        }

        try {
            int articleId = Integer.parseInt(articleIdStr);
            
            // 2. Tạo Model và gán giá trị
            ArticleModel article = articleService.getArticleById(articleId); // Lấy Model cũ
            if (article != null) {
                article.setTitle(title);
                article.setContent(content);
                article.setSummary(summary);
                article.setStatus(status);
                // Giữ nguyên Category ID, User ID, v.v.
                
                // 3. Gọi Service để cập nhật
                articleService.saveOrUpdate(article);
                
                req.getSession().setAttribute("message", "Cập nhật bài viết thành công!");
            }
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("message", "ID bài viết không hợp lệ.");
        }
        
        // 4. Chuyển hướng
        resp.sendRedirect(req.getContextPath() + "/admin/article/list");
    }
}