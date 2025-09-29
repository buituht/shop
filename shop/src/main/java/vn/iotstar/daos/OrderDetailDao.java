package vn.iotstar.daos;

import java.util.List;
import vn.iotstar.models.OrderDetailModel;

public interface OrderDetailDao {
    
    /**
     * Chèn nhiều Order Detail vào bảng 'order_details'.
     * @param details Danh sách các OrderDetailModel cần chèn.
     * @return true nếu tất cả các chi tiết đều được chèn thành công.
     */
    boolean insertDetails(List<OrderDetailModel> details);
}