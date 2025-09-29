package vn.iotstar.services;

import java.util.List;
import vn.iotstar.models.CartItemModel;
import vn.iotstar.models.OrderModel;

public interface OrderService {
    
    /**
     * Thực hiện toàn bộ giao dịch đặt hàng: lưu Order, lưu Order Details.
     * @param order Thông tin đơn hàng (header)
     * @param cartItems Danh sách các mục trong giỏ hàng (details)
     * @return true nếu đặt hàng thành công, ngược lại là false.
     */
    boolean placeOrder(OrderModel order, List<CartItemModel> cartItems);
}