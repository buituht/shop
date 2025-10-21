package vn.iotstar.daos.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import vn.iotstar.daos.UserDao;
import vn.iotstar.models.CategoryModel;
import vn.iotstar.models.ProductModel;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;
import vn.iotstar.jdbc.DBConnection;


public class UserDaoImpl implements UserDao{

	
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	public UserDaoImpl(String email, String username, String fullname, String password, Object object, int roleid,
			String phone, Date date) {
		// TODO Auto-generated constructor stub
	}

	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserModel findByUserName(String username) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM users WHERE userName = ? ";
		try {

		Connection conn = DBConnection.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		
		rs = ps.executeQuery();
		while (rs.next()) {
			UserModel user = new UserModel();
			user.setId(rs.getInt("id"));
			user.setEmail(rs.getString("email"));
			user.setUserName(rs.getString("username"));
			user.setFullName(rs.getString("fullname"));
			user.setPassWord(rs.getString("password"));
			user.setRoleid(rs.getInt("roleid"));
			user.setAvatar(rs.getString("avatar"));
			user.setPhone(rs.getString("phone"));
		
			return user;
		}
		} catch (Exception e) {e.printStackTrace();
		
		
		}
		return null;
	}

	@Override
	public UserModel Insert(String email,String username,String fullname, String password,String avatar,int roleid,String phone) {
String query = "INSERT INTO users(email, username, fullname, password, avatar,roleid, phone, createddate) VALUES (?,?,?,?,?,?,?,?)";
		
Connection conn = DBConnection.getConnection();

		try (PreparedStatement ps = conn.prepareStatement(query)){
							
				ps.setString(1, email);
				ps.setString(2, username);
				ps.setString(3, fullname);
				ps.setString(4, password);
				ps.setString(5, avatar);				
				ps.setInt(6, roleid);
				ps.setString(7,phone);
		
				ps.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
				
		        System.out.println("Executing SQL: " + query);
		        
				ps.executeUpdate();
				
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}

	@Override
	public int getRoleid() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "SELECT COUNT(*) FROM users WHERE email = ?";
		try {
		
			
			 	Connection conn = DBConnection.getConnection();
				ps = conn.prepareStatement(query);
				ps.setString(1, email);
				rs = ps.executeQuery();
				 System.out.println("Executing SQL check email: " + ps);
				if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
	} catch (Exception ex) {}
	return duplicate;
	}
	
	
	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "SELECT COUNT(*) FROM users username = ?";
		try {
			
			 	Connection conn = DBConnection.getConnection();
				ps = conn.prepareStatement(query);
				ps.setString(1, username);
				rs = ps.executeQuery();
				 System.out.println("Executing SQL check username: " + ps);
				if (rs.next()) {
				duplicate = true;
				}
				ps.close();
				conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}
	
	//user on admin
	 @Override
	    public List<UserModel> findAll() {
	        // Query cần lấy tất cả các cột
	     
	        List<UserModel> list = new ArrayList<>();
	        String sql = "SELECT id, email, userName, fullName, avatar, roleId, phone, createdDate FROM users ORDER BY id DESC";
	        try {
	        	conn = DBConnection.getConnection();
				ps = conn.prepareStatement(sql);
				
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                UserModel users = new UserModel();
	                users.setId(rs.getInt("id"));
	                users.setUserName(rs.getString("userName"));
	                users.setEmail(rs.getString("email"));
	                users.setFullName(rs.getString("fullName"));
	                users.setRoleid(rs.getInt("roleid"));
	                
	                 
	                list.add(users);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	    
	    @Override
	    public UserModel findById(int id) {
	        String sql = "SELECT * FROM users WHERE id = ?";
	        UserModel user = null;
	        System.out.println("Lay user: " + ps);
	        try{
	        	conn = DBConnection.getConnection();
				ps = conn.prepareStatement(sql);
	        	
	        	
	            ps.setInt(1, id);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                	user = new UserModel();
	                	user.setId(rs.getInt("id"));
	                	user.setUserName(rs.getString("username"));
	                	user.setFullName(rs.getString("fullName"));
	                	user.setEmail(rs.getString("email"));
	                	user.setAvatar(rs.getString("avatar"));
	                	user.setPhone(rs.getString("phone"));
	                	user.setRoleid(rs.getInt("roleid"));
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return user;
	    }

	    @Override
	    public void insert(UserModel user) {
	        // Query sử dụng tên cột DB: userName, fullName, passWord, roleId, createdDate
	        String sql = "INSERT INTO users (email, userName, fullName, passWord, avatar, roleId, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        
	    }

	    @Override
	    public void update(UserModel user) {
	       
	        String sql = "UPDATE users SET email=? ,fullName=?, avatar=?, roleId=?, phone=? WHERE id=?";
	        
	        try {

	        	conn = DBConnection.getConnection();
				ps = conn.prepareStatement(sql);
				
				
				
	            ps.setString(1, user.getEmail());
	  
	            ps.setString(2, user.getFullName());
	            ps.setString(3, user.getAvatar());
	            ps.setInt(4, user.getRoleid());
	            ps.setString(5, user.getPhone());
	            ps.setInt(6, user.getId());
	            
	            ps.executeUpdate();
	            System.out.println("Cap nhat user: " + ps);
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void delete(int id) {
	        String sql = "DELETE FROM users WHERE id = ?";
	       
	    }

	//end user on admin
	

	
}
