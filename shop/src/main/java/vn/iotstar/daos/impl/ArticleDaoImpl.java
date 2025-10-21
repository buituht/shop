package vn.iotstar.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.daos.ArticleDao;
import vn.iotstar.jdbc.DBConnection; // Sử dụng lớp kết nối DB của bạn
import vn.iotstar.models.ArticleModel;

public class ArticleDaoImpl implements ArticleDao {
    
    // Hàm hỗ trợ mapping dữ liệu từ ResultSet sang ArticleModel
    private ArticleModel mapData(ResultSet rs) throws Exception {
        ArticleModel article = new ArticleModel();
        
        article.setArticleId(rs.getInt("article_id"));
        article.setCategoryId(rs.getInt("category_id"));
        
        Integer userId = rs.getInt("user_id");
        article.setUserId(rs.wasNull() ? null : userId); // Xử lý userId có thể NULL
        
        article.setTitle(rs.getString("title"));
        article.setSlug(rs.getString("slug"));
        article.setSummary(rs.getString("summary"));
        article.setContent(rs.getString("content"));
        article.setImage(rs.getString("image"));
        article.setViews(rs.getInt("views"));
        article.setStatus(rs.getString("status"));
        article.setCreatedAt(rs.getTimestamp("created_at"));
        article.setUpdatedAt(rs.getTimestamp("updated_at"));
        
        // Cột thêm từ JOIN (nếu có)
        try {
            article.setAuthorName(rs.getString("authorName")); // Alias từ bảng users
        } catch (Exception e) {
            // Bỏ qua nếu không có cột authorName trong query (ví dụ: query findPublishedPaging)
        }
        
        return article;
    }

    @Override
    public List<ArticleModel> findAllPaging(int offset, int limit) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ArticleModel> articles = new ArrayList<>();
        
        // Lấy tên tác giả bằng cách JOIN với bảng users
        String sql = "SELECT a.*, u.userName AS authorName " +
                     "FROM articles a " +
                     "LEFT JOIN users u ON a.user_id = u.id " +
                     "ORDER BY a.created_at DESC " +
                     "LIMIT ? OFFSET ?";
        
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                articles.add(mapData(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên: DBConnection.close(rs, ps, conn);
        }
        return articles;
    }
    
    @Override
    public int countAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(article_id) FROM articles";
        int count = 0;
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
        }
        return count;
    }
    
    
    
    @Override
    public void insert(ArticleModel article) {
       
    }
    
    @Override
    public void update(ArticleModel article) {
        
    }
    
    @Override
    public void delete(int articleId) {
       
    }
    
    @Override
    public ArticleModel findById(int articleId) {
       
        return null;
    }
    
    @Override
    public List<ArticleModel> findPublishedPaging(int offset, int limit) {
  
        return null;
    }
    
    @Override
    public ArticleModel findBySlug(String slug) {
      
        return null;
    }
    
    @Override
    public void incrementViews(int articleId) {
        
    }
}