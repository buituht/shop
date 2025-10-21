package vn.iotstar.models;

import java.sql.Timestamp;

public class ArticleModel {
    private int articleId;
    private int categoryId;       
    private Integer userId;       
    private String title;
    private String slug;          
    private String summary;       
    private String content;
    private String image;         
    private int views;            
    private String status;        
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    // Thêm trường hỗ trợ hiển thị tên người tạo (từ bảng users)
    private String authorName; 

    // --- Constructor ---
    public ArticleModel() {
    }

    // --- Getters and Setters ---
    
    public int getArticleId() { return articleId; }
    public void setArticleId(int articleId) { this.articleId = articleId; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}