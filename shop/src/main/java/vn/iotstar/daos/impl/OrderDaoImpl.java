package vn.iotstar.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.daos.OrderDao;
import vn.iotstar.jdbc.DBConnection;
import vn.iotstar.models.OrderModel;
import vn.iotstar.models.ProductModel;

public class OrderDaoImpl implements OrderDao {
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public int insertOrder(OrderModel order) {
        // Query chèn thông tin Order Header
        String sql = "INSERT INTO orders(user_id, full_name, phone, email, shipping_address, total_amount, payment_method, status) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
        	conn = DBConnection.getConnection();
			
            
            // Sử dụng RETURN_GENERATED_KEYS để lấy ID vừa chèn
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            System.out.println("Insert Order: " + sql);
            
            // Tham số 1: user_id (Dùng setInt nếu Integer không null, dùng setNull nếu null)
            if (order.getUserId() != null && order.getUserId() > 0) {
                ps.setInt(1, order.getUserId());
            } else {
                ps.setNull(1, java.sql.Types.INTEGER);
            }
            
            // Các tham số còn lại
            ps.setString(2, order.getFullName());
            ps.setString(3, order.getPhone());
            ps.setString(4, order.getEmail());
            ps.setString(5, order.getShippingAddress());
            ps.setDouble(6, order.getTotalAmount());
            ps.setString(7, order.getPaymentMethod());
            ps.setInt(8, order.getStatus());

            int affectedRows = ps.executeUpdate();
            
            if (affectedRows > 0) {
                // Lấy ID tự động được tạo
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Trả về order_id
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
            // (Trong môi trường production, bạn nên đóng tài nguyên rs, ps, conn)
        }
        return 0; // Thất bại
    }

    @Override
    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        try {
        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
			
            ps.setInt(1, orderId);
            ps.executeUpdate();
            // Xử lý thành công/thất bại nếu cần
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối
        }
    }
    
    
    @Override
    public List<OrderModel> findAllOrdersPaging(int offset, int limit) {
    	String sql = "SELECT o.*, u.userName, u.id "
    			+ "FROM orders o "
    			+ "LEFT JOIN users u ON o.user_id = u.id " + 
                "ORDER BY o.order_id DESC " +
                "LIMIT ? OFFSET ?";
    	System.out.println("Truy van danh sach Order: " + sql);
List<OrderModel> orders = new ArrayList<>();
        
        try {
            conn = DBConnection.getConnection();
            ps = conn.prepareStatement(sql);
            
            // Đã sửa: Gán giá trị cho LIMIT (?) và OFFSET (?)
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                OrderModel order = new OrderModel();
                // 1. Mapping các trường cơ bản
                order.setOrderId(rs.getInt("order_id"));
                order.setUserId(rs.getInt("id"));
                order.setFullName(rs.getString("full_name"));
                order.setEmail(rs.getString("email"));
                order.setPhone(rs.getString("phone"));
                order.setShippingAddress(rs.getString("shipping_address"));
              
                order.setPaymentMethod(rs.getString("payment_method"));

                order.setOrderDate(rs.getTimestamp("order_date"));
                
                // 2. Mapping trường JOIN (user_id)
                order.setUserId(rs.getInt("id"));
                
                
                
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public int countAllOrders() {


         String sql = "SELECT COUNT(order_id) FROM orders";
         int count = 0;
         
         try {
             conn = DBConnection.getConnection();
             ps = conn.prepareStatement(sql);
             rs = ps.executeQuery();
             
             if (rs.next()) {
                 count = rs.getInt(1); // Lấy giá trị của cột COUNT
             }
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             // Đóng
             // DBConnection.close(rs, ps, conn); 
         }
         
       
         return count; 
    }
   
    
}