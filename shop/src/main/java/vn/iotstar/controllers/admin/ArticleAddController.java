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

@WebServlet(urlPatterns = {"/admin/article/add"})
public class ArticleAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ArticleService articleService = new ArticleServiceImpl();

    // Hiển thị Form thêm mới
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển tiếp đến trang form rỗng
        req.getRequestDispatcher("/views/admin/article/form.jsp").forward(req, resp);
    }

    // Xử lý lưu Bài viết mới
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // 1. Lấy dữ liệu từ Form
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String summary = req.getParameter("summary");
        String status = req.getParameter("status");
        // Giả định bạn có Category ID và User ID (lấy từ Session)
        
        // 2. Tạo và gán giá trị cho Model
        ArticleModel article = new ArticleModel();
        article.setTitle(title);
        article.setContent(content);
        article.setSummary(summary);
        article.setStatus(status);
        article.setUserId(1); // Giả sử User ID mặc định
        article.setCategoryId(1); // Giả sử Category ID mặc định
        
        // 3. Gọi Service để lưu
        articleService.saveOrUpdate(article);
        
        // 4. Chuyển hướng về trang danh sách và thông báo thành công
        req.getSession().setAttribute("message", "Thêm bài viết mới thành công!");
        resp.sendRedirect(req.getContextPath() + "/admin/article/list");
    }
}