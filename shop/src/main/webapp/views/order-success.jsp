<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt hàng Thành công!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <div class="container mt-5 mb-5 text-center">
        <div class="card shadow-lg p-5 border-success">
            <i class="bi bi-check-circle-fill text-success" style="font-size: 5rem;"></i>
            
            <h1 class="mt-3 text-success">ĐẶT HÀNG THÀNH CÔNG!</h1>
            
            <p class="lead">Cảm ơn bạn đã tin tưởng và mua sắm tại cửa hàng của chúng tôi. Đơn hàng của bạn đang được xử lý.</p>
            <p>Thông tin chi tiết đơn hàng (Mã đơn hàng: **#<c:out value="${param.orderId}" default="VNN00XX"/>**) đã được gửi qua email.</p>
            
            <hr>
            
            <a href="${pageContext.request.contextPath}/products" class="btn btn-primary btn-lg mt-3">
                <i class="bi bi-shop"></i> Tiếp tục mua sắm
            </a>
            <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-secondary mt-2">
                Quay lại trang chủ
            </a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>