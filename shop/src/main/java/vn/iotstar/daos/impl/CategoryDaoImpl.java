package vn.iotstar.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import vn.iotstar.daos.CategoryDao;
import vn.iotstar.jdbc.DBConnection;
import vn.iotstar.models.CategoryModel;

public class CategoryDaoImpl implements CategoryDao {
    
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
    private static final String COLUMNS = "catId, CateName, Image, status";

    @Override
    public List<CategoryModel> findAll() {
        List<CategoryModel> list = new ArrayList<>();
        String sql = "SELECT " + COLUMNS + " FROM category";
        try {
        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setCatId(rs.getInt("catId"));
                category.setCateName(rs.getString("CateName"));
                category.setImage(rs.getString("Image"));
                category.setStatus(rs.getInt("status")); 
                list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public CategoryModel get(int catId) {
        String sql = "SELECT " + COLUMNS + " FROM category WHERE catId = ?";
        try {
        	
        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
            ps.setInt(1, catId);
            rs = ps.executeQuery();
            if (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setCatId(rs.getInt("catId"));
                category.setCateName(rs.getString("CateName"));
                category.setImage(rs.getString("Image"));
                category.setStatus(rs.getInt("status"));
                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(CategoryModel category) {
        String sql = "INSERT INTO category(CateName, Image, status) VALUES(?, ?, ?)";
        try {

        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
            ps.setString(1, category.getCateName());
            ps.setString(2, category.getImage());
            ps.setInt(3, category.getStatus());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(CategoryModel category) {
        String sql = "UPDATE category SET CateName = ?, Image = ?, status = ? WHERE catId = ?";
        try {

        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
            ps.setString(1, category.getCateName());
            ps.setString(2, category.getImage());
            ps.setInt(3, category.getStatus());
            ps.setInt(4, category.getCatId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int catId) {
        String sql = "DELETE FROM category WHERE catId = ?";
        try {

        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
            ps.setInt(1, catId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CategoryModel findByCategoryName(String cateName) {
        String sql = "SELECT " + COLUMNS + " FROM category WHERE CateName = ?";
        try {
        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
            ps.setString(1, cateName);
            rs = ps.executeQuery();
            if (rs.next()) {
                CategoryModel category = new CategoryModel();
                category.setCatId(rs.getInt("catId"));
                category.setCateName(rs.getString("CateName"));
                category.setImage(rs.getString("Image"));
                category.setStatus(rs.getInt("status"));
                return category;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}