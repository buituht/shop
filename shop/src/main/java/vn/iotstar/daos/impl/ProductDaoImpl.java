package vn.iotstar.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vn.iotstar.daos.ProductDao;
import vn.iotstar.jdbc.DBConnection;
import vn.iotstar.models.ProductModel;

public class ProductDaoImpl implements ProductDao {
	
	
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	
    @Override
    public void insert(ProductModel product) {
        String sql = "INSERT INTO product(cate_id, name, price, image, description) VALUES (?, ?, ?, ?, ?)";
        try{
        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
            ps.setInt(1, product.getCateId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getImage());
            ps.setString(5, product.getDescription());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(ProductModel product) {
        String sql = "UPDATE product SET cate_id=?, name=?, price=?, image=?, description=? WHERE id=?";
        try{
        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
            ps.setInt(1, product.getCateId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getImage());
            ps.setString(5, product.getDescription());
            ps.setInt(6, product.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM product WHERE id = ?";
        try{
        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
        	
        	
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductModel get(int id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        ProductModel product = null;
        
        try{
        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
        	
        	
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    product = new ProductModel();
                    product.setId(rs.getInt("id"));
                    product.setCateId(rs.getInt("cate_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setImage(rs.getString("image"));
                    product.setDescription(rs.getString("description"));
                    product.setStatus(rs.getInt("status"));
                    product.setCreatedAt(rs.getTimestamp("created_at"));
                    product.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<ProductModel> findAll() {
        String sql = "SELECT * FROM product";
        List<ProductModel> products = new ArrayList<>();
        
        
        try {
        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
     
			System.out.println("Executing SQL: " + sql);
        ResultSet rs = ps.executeQuery();
        	
            while (rs.next()) {
                ProductModel product = new ProductModel();
                product.setId(rs.getInt("id"));
                product.setCateId(rs.getInt("cate_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                product.setDescription(rs.getString("description"));
                product.setStatus(rs.getInt("status"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                product.setUpdatedAt(rs.getTimestamp("updated_at"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}