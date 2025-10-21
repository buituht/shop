package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.daos.ArticleDao;
import vn.iotstar.daos.impl.ArticleDaoImpl; // Giả định
import vn.iotstar.models.ArticleModel;
import vn.iotstar.services.ArticleService;

public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;

    // Constructor để khởi tạo DAO
    public ArticleServiceImpl() {
        this.articleDao = new ArticleDaoImpl();
    }

    // --- PHƯẦN ADMIN ---

    @Override
    public List<ArticleModel> getArticlesForAdmin(int page, int itemsPerPage) {
        // 1. Tính toán OFFSET
        int offset = (page - 1) * itemsPerPage;
        
        // 2. Gọi DAO để lấy danh sách (bao gồm cả Draft/Hidden)
        return articleDao.findAllPaging(offset, itemsPerPage);
    }

    @Override
    public int getTotalArticles() {
        // 1. Gọi DAO để lấy tổng số lượng bài viết
        return articleDao.countAll();
    }

    @Override
    public void saveOrUpdate(ArticleModel article) {
        // Bổ sung logic nghiệp vụ: Xử lý SLUG, kiểm tra dữ liệu, v.v.
        
        // Ví dụ: Đảm bảo tiêu đề không rỗng
        if (article.getTitle() == null || article.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        
        // Thường thì logic tạo slug sẽ ở đây:
        // article.setSlug(SlugUtils.toSlug(article.getTitle()));

        if (article.getArticleId() > 0) {
            // Cập nhật
            articleDao.update(article);
        } else {
            // Thêm mới
            articleDao.insert(article);
        }
    }

    @Override
    public void removeArticle(int articleId) {
        // Bổ sung logic nghiệp vụ: Kiểm tra quyền xóa, xóa các file liên quan
        articleDao.delete(articleId);
    }

    @Override
    public ArticleModel getArticleById(int articleId) {
        // Bổ sung logic: Kiểm tra ID hợp lệ
        if (articleId <= 0) {
            return null;
        }
        return articleDao.findById(articleId);
    }

    // --- PHẦN FRONT-END ---

    @Override
    public List<ArticleModel> getPublishedArticles(int page, int itemsPerPage) {
        // 1. Tính toán OFFSET
        int offset = (page - 1) * itemsPerPage;
        
        // 2. Gọi DAO để lấy danh sách CHỈ những bài đã xuất bản ('Published')
        return articleDao.findPublishedPaging(offset, itemsPerPage);
    }

    @Override
    public int getTotalPublishedArticles() {
        // Cần thêm phương thức count riêng trong DAO nếu muốn đếm CHỈ bài đã xuất bản
        // Hiện tại dùng tạm countAll hoặc bạn phải tự implement trong DAO
        // return articleDao.countPublished(); 
        return articleDao.countAll(); // Dùng tạm, nên sửa trong DAO
    }
    
    @Override
    public ArticleModel getArticleBySlug(String slug) {
        // 1. Gọi DAO tìm theo slug
        ArticleModel article = articleDao.findBySlug(slug);
        
        // 2. Logic kiểm tra (Chỉ hiển thị nếu trạng thái là 'Published')
        if (article != null && "Published".equalsIgnoreCase(article.getStatus())) {
            return article;
        }
        return null;
    }

    @Override
    public void viewArticle(int articleId) {
        // Bổ sung logic: Kiểm tra người dùng có đang xem bài viết (tránh spam view)
        // Gọi DAO để tăng lượt xem
        articleDao.incrementViews(articleId);
    }
}