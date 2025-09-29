package vn.iotstar.daos;

import vn.iotstar.models.OrderModel;

public interface OrderDao {
    
    /**
     * Chèn Order Header mới vào bảng 'orders'.
     * @param order Đối tượng OrderModel chứa thông tin đặt hàng.
     * @return order_id (khóa chính) được tạo tự động nếu thành công, 0 nếu thất bại.
     */
    int insertOrder(OrderModel order);
    
    /**
     * Xóa Order Header (dùng trong trường hợp rollback nếu lưu OrderDetails thất bại).
     * @param orderId ID của đơn hàng cần xóa.
     */
    void deleteOrder(int orderId);
}