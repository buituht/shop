package vn.iotstar.daos;

import java.util.List;
import vn.iotstar.models.ArticleModel;

public interface ArticleDao {
    
    // Thao tác CRUD cơ bản
    void insert(ArticleModel article);
    void update(ArticleModel article);
    void delete(int articleId);
    ArticleModel findById(int articleId);
    
    // Lấy danh sách (Admin)
    List<ArticleModel> findAllPaging(int offset, int limit);
    int countAll();
    
    // Lấy danh sách hiển thị cho người dùng (Front-end)
    List<ArticleModel> findPublishedPaging(int offset, int limit);
    
    // Lấy bài viết theo slug
    ArticleModel findBySlug(String slug);
    
    // Cập nhật lượt xem
    void incrementViews(int articleId);
}