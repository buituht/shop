package vn.iotstar.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import vn.iotstar.daos.OrderDao;
import vn.iotstar.jdbc.DBConnection;
import vn.iotstar.models.OrderModel;

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
}