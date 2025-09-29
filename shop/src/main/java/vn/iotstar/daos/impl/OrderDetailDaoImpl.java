package vn.iotstar.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import java.sql.Connection;
import vn.iotstar.daos.OrderDetailDao;
import vn.iotstar.jdbc.DBConnection;
import vn.iotstar.models.OrderDetailModel;

public class OrderDetailDaoImpl implements OrderDetailDao {
    
    Connection conn = null;
    PreparedStatement ps = null;

    @Override
    public boolean insertDetails(List<OrderDetailModel> details) {
        // Query chèn chi tiết Order
        String sql = "INSERT INTO order_details(order_id, product_id, product_name, quantity, price) "
                   + "VALUES(?, ?, ?, ?, ?)";
        
        try {
        	conn = DBConnection.getConnection();
			ps = conn.prepareStatement(sql);
            
            // Sử dụng Batch Insert để chèn nhiều dòng hiệu quả hơn
            for (OrderDetailModel detail : details) {
                ps.setInt(1, detail.getOrderId());
                ps.setInt(2, detail.getProductId());
                ps.setString(3, detail.getProductName());
                ps.setInt(4, detail.getQuantity());
                ps.setDouble(5, detail.getPrice());
                
                ps.addBatch(); // Thêm vào lô (batch)
            }
            
            int[] results = ps.executeBatch(); // Thực thi lô
            
            // Kiểm tra xem tất cả các bản ghi có được chèn thành công không
            for (int result : results) {
                if (result <= 0) {
                    return false; // Có ít nhất một bản ghi thất bại
                }
            }
            return true; // Tất cả đều thành công

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng kết nối
        }
    }
}