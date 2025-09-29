package vn.iotstar.utils;

import java.util.List;
import vn.iotstar.models.CartItemModel;
import vn.iotstar.models.OrderModel;

public class EmailTemplate {
    
    public static String buildOrderConfirmationEmail(OrderModel order, List<CartItemModel> cartItems) {
        
        StringBuilder tableContent = new StringBuilder();
        double total = 0;
        
        // Tạo bảng chi tiết sản phẩm
        for (CartItemModel item : cartItems) {
            double subTotal = item.getPrice() * item.getQuantity();
            total += subTotal;
            tableContent.append("<tr>");
            tableContent.append("<td style='padding: 8px; border: 1px solid #ddd;'>").append(item.getProductName()).append("</td>");
            tableContent.append("<td style='padding: 8px; border: 1px solid #ddd; text-align: center;'>").append(item.getQuantity()).append("</td>");
            tableContent.append("<td style='padding: 8px; border: 1px solid #ddd; text-align: right;'>").append(String.format("%,.0f₫", item.getPrice())).append("</td>");
            tableContent.append("<td style='padding: 8px; border: 1px solid #ddd; text-align: right;'>").append(String.format("%,.0f₫", subTotal)).append("</td>");
            tableContent.append("</tr>");
        }
        
        // Mẫu HTML email cơ bản
        String html = "<!DOCTYPE html>"
                + "<html lang='vi'>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<title>Xác nhận Đơn hàng</title>"
                + "</head>"
                + "<body style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>"
                + "<div style='max-width: 600px; margin: 20px auto; border: 1px solid #eee; padding: 20px; border-radius: 8px;'>"
                + "<h2 style='color: #007bff; text-align: center;'>XÁC NHẬN ĐƠN HÀNG THÀNH CÔNG</h2>"
                + "<p>Chào " + order.getFullName() + ",</p>"
                + "<p>Đơn hàng của bạn đã được đặt thành công. Chúng tôi sẽ xử lý đơn hàng sớm nhất có thể.</p>"
                + "<h3 style='border-bottom: 1px solid #eee; padding-bottom: 10px;'>Chi tiết đơn hàng #" + order.getOrderId() + "</h3>"
                
                // Bảng chi tiết sản phẩm
                + "<table style='width: 100%; border-collapse: collapse; margin-bottom: 20px;'>"
                + "<thead><tr>"
                + "<th style='padding: 10px; background-color: #f8f8f8; border: 1px solid #ddd; text-align: left;'>Sản phẩm</th>"
                + "<th style='padding: 10px; background-color: #f8f8f8; border: 1px solid #ddd;'>SL</th>"
                + "<th style='padding: 10px; background-color: #f8f8f8; border: 1px solid #ddd; text-align: right;'>Đơn giá</th>"
                + "<th style='padding: 10px; background-color: #f8f8f8; border: 1px solid #ddd; text-align: right;'>Thành tiền</th>"
                + "</tr></thead>"
                + "<tbody>"
                + tableContent.toString()
                + "<tr>"
                + "<td colspan='3' style='padding: 10px; text-align: right; font-weight: bold;'>Tổng cộng:</td>"
                + "<td style='padding: 10px; text-align: right; font-weight: bold; color: #dc3545;'>" + String.format("%,.0f₫", total) + "</td>"
                + "</tr>"
                + "</tbody>"
                + "</table>"
                
                // Thông tin giao hàng
                + "<h3 style='border-bottom: 1px solid #eee; padding-bottom: 10px;'>Thông tin nhận hàng</h3>"
                + "<p><strong>Người nhận:</strong> " + order.getFullName() + "</p>"
                + "<p><strong>Địa chỉ:</strong> " + order.getShippingAddress() + "</p>"
                + "<p><strong>Điện thoại:</strong> " + order.getPhone() + "</p>"
                + "<p><strong>Thanh toán:</strong> " + order.getPaymentMethod() + "</p>"
                
                + "<p style='text-align: center; margin-top: 30px; font-size: 0.9em; color: #999;'>Nếu bạn có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi.</p>"
                + "</div>"
                + "</body>"
                + "</html>";
        
        return html;
    }
}