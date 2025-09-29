<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %> 
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng của bạn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <div class="container mt-5 mb-5">
        <h2 class="text-primary mb-4">Giỏ hàng của bạn</h2>

        <c:choose>
            <c:when test="${empty cart}">
                <div class="alert alert-info text-center">
                    Giỏ hàng của bạn đang trống!
                    <a href="${pageContext.request.contextPath}/products" class="alert-link">Tiếp tục mua sắm</a>.
                </div>
            </c:when>
            <c:otherwise>
                <div class="row">
                    <div class="col-lg-8">
                        <table class="table table-bordered table-striped align-middle">
                            <thead class="table-light">
                                <tr>
                                    <th>Sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Tổng cộng</th>
                                    <th>Xóa</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${cart}">
                                    <tr>
                                        <td class="d-flex align-items-center">
                                            <img src="/product_images/${item.image}" alt="${item.productName}" width="60" class="me-3 rounded">
                                            <span>${item.productName}</span>
                                        </td>
                                        <td>
                                            <fmt:formatNumber value="${item.price}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                        </td>
                                        <td>
                                            ${item.quantity}
                                        </td>
                                        <td>
                                            <fmt:formatNumber value="${item.price * item.quantity}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                        </td>
                                        <td>
                                            <a href="#" class="btn btn-sm btn-outline-danger">
                                                <i class="bi bi-trash"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-lg-4">
                        <div class="card shadow-sm">
                            <div class="card-header bg-success text-white">
                                <h5>Tóm tắt đơn hàng</h5>
                            </div>
                            <div class="card-body">
                                <div class="d-flex justify-content-between mb-3">
                                    <strong>Tổng tiền hàng:</strong>
                                    <span>
                                        <fmt:formatNumber value="${cartTotal}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                    </span>
                                </div>
                                <div class="d-grid">
                                    <a href="${pageContext.request.contextPath}/checkout" class="btn btn-primary btn-lg">
                                        Tiến hành Thanh toán
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        
        <div class="mt-4">
             <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary">
                 <i class="bi bi-arrow-left"></i> Tiếp tục mua sắm
             </a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>