package vn.iotstar.services;

import java.util.List;
import vn.iotstar.models.ArticleModel;

public interface ArticleService {
    
    // Quản lý Admin
    List<ArticleModel> getArticlesForAdmin(int page, int itemsPerPage);
    int getTotalArticles();
    
    // Thao tác CRUD
    void saveOrUpdate(ArticleModel article);
    void removeArticle(int articleId);
    ArticleModel getArticleById(int articleId);
    
    // Hiển thị Front-end
    List<ArticleModel> getPublishedArticles(int page, int itemsPerPage);
    ArticleModel getArticleBySlug(String slug);
    
    // Cập nhật lượt xem (thường là logic Service)
    void viewArticle(int articleId);
	int getTotalPublishedArticles();
}