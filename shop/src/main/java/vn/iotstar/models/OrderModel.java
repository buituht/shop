package vn.iotstar.models;

import java.util.Date;
import java.util.List;

public class OrderModel {
    
    private int orderId;
    private Integer userId; // Có thể là null nếu là khách vãng lai
    
    // Thông tin người nhận
    private String fullName;
    private String phone;
    private String email;
	
    private String shippingAddress;
    
    private double totalAmount;
    private String paymentMethod;
    private Date orderDate;
    private int status; // Trạng thái đơn hàng
    
   
    private List<OrderDetailModel> orderDetails; 

    
    public OrderModel() {}
    
    // Getters and Setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }



    public List<OrderDetailModel> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailModel> orderDetails) {
        this.orderDetails = orderDetails;
    }

	public void setStatus(int i) {
		// TODO Auto-generated method stub
		
	}
}