package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.iotstar.models.ArticleModel;
import vn.iotstar.services.ArticleService;
import vn.iotstar.services.impl.ArticleServiceImpl;

@WebServlet(urlPatterns = {"/admin/article/list"})
public class ArticleListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ArticleService articleService = new ArticleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Thiết lập tham số phân trang
        int page = 1;
        int itemsPerPage = 10;
        
        String pageStr = req.getParameter("page");
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                page = 1; 
            }
        }
        
        // Lấy dữ liệu
        List<ArticleModel> articleList = articleService.getArticlesForAdmin(page, itemsPerPage);
        int totalArticles = articleService.getTotalArticles();
        int totalPages = (int) Math.ceil((double) totalArticles / itemsPerPage);
        
        // Đặt thuộc tính và chuyển tiếp đến View
        req.setAttribute("articleList", articleList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalArticles", totalArticles);
        
        // Hiển thị thông báo (nếu có từ các Controller khác)
        String message = (String) req.getSession().getAttribute("message");
        if (message != null) {
            req.setAttribute("message", message);
            req.getSession().removeAttribute("message");
        }
        
        req.getRequestDispatcher("/views/admin/article-list.jsp").forward(req, resp);
    }
}