package vn.iotstar.services.impl;

import java.util.List;
import java.util.ArrayList;

import vn.iotstar.daos.OrderDao; 
import vn.iotstar.daos.OrderDetailDao; 
import vn.iotstar.daos.impl.OrderDaoImpl; 
import vn.iotstar.daos.impl.OrderDetailDaoImpl; 

import vn.iotstar.models.CartItemModel;
import vn.iotstar.models.OrderDetailModel;
import vn.iotstar.models.OrderModel;
import vn.iotstar.services.OrderService;

import vn.iotstar.utils.EmailUtil; // Import lớp EmailUtil
import vn.iotstar.utils.EmailTemplate; // Cần tạo lớp này để có mẫu email

public class OrderServiceImpl implements OrderService {
    
    OrderDao orderDao = new OrderDaoImpl();
    OrderDetailDao detailDao = new OrderDetailDaoImpl();

    @Override
    public boolean placeOrder(OrderModel order, List<CartItemModel> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) {
            return false;
        }

        // Bắt đầu giao dịch (Transaction)
        try {
            // 1. Lưu Order Header vào bảng ORDERS
            // Giả định insert() trả về order_id được tạo tự động
            int orderId = orderDao.insertOrder(order); 

            if (orderId > 0) {
                List<OrderDetailModel> details = new ArrayList<>();
                
                for (CartItemModel item : cartItems) {
                    OrderDetailModel detail = new OrderDetailModel();
                    detail.setOrderId(orderId); // Gán khóa ngoại
                    detail.setProductId(item.getProductId());
                    detail.setProductName(item.getProductName());
                    detail.setQuantity(item.getQuantity());
                    detail.setPrice(item.getPrice());
                    
                    details.add(detail);
                }

                // 2. Lưu tất cả Order Details vào bảng ORDER_DETAILS
                boolean detailsSuccess = detailDao.insertDetails(details);
                
                
                
                if (detailsSuccess) {
                    // Nếu cả hai bước đều thành công, hoàn tất giao dịch
                    // (Trong DAO thực tế, bạn cần gọi commit())
                	
                	 String subject = "[XÁC NHẬN] Đơn hàng #" + orderId + " của bạn đã được đặt thành công!";
                     String htmlContent = EmailTemplate.buildOrderConfirmationEmail(order, cartItems);
                     
                     // Gửi email, không cần dừng giao dịch nếu email thất bại 
                     EmailUtil.sendEmail(order.getEmail(), subject, htmlContent);
                   
                     
                    return true;
                } else {
                    // Nếu lưu details thất bại, cần hủy Order Header vừa tạo
                    // (Trong DAO thực tế, cần gọi rollback() và xóa Order Header)
                    orderDao.deleteOrder(orderId); 
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Nếu có lỗi, hủy giao dịch
            // (Trong DAO thực tế, cần gọi rollback())
            return false;
        }
        return false;
    }
    
    @Override
    public List<OrderModel> findAllOrdersPaging(int offset, int limit) {
        return orderDao.findAllOrdersPaging(offset, limit);
    }

    @Override
    public int countAllOrders() {
        return orderDao.countAllOrders();
    }
}