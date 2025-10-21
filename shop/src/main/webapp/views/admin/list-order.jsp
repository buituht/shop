<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="col-sm-9">
      <h1><small>Danh Sách Đơn Hàng</small></h1>
        <hr/>
        
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover shadow-sm">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Khách hàng</th>
                        <th>Địa chỉ</th>
                        <th>Tổng tiền</th>
                        <th>Thanh toán</th>
                        <th>Ngày đặt</th>
                        <th>Trạng thái</th>
                        <th class="text-center">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.userId} (${order.fullName})</td>
                            <td>${order.shippingAddress}</td>
                            <td><fmt:formatNumber value="${order.totalAmount}" pattern="#,###" />₫</td>
                            <td>${order.paymentMethod}</td>
                            <td>
                                <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm"/>
                            </td>
                            <td>
                                <span class="badge 
                                    <c:choose>
                                        <c:when test="${order.status == '1'}">bg-success</c:when>
                                        <c:when test="${order.status == '2'}">bg-primary</c:when>
                                        <c:when test="${order.status == '3'}">bg-danger</c:when>
                                        <c:otherwise>bg-warning</c:otherwise>
                                    </c:choose>
                                ">${order.status}</span>
                            </td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/admin/order/detail?id=${order.orderId}" class="btn btn-sm btn-info" title="Xem chi tiết">
                                    <i class="bi bi-eye"></i>
                                </a>
                                <a href="${pageContext.request.contextPath}/admin/order/update-status?id=${order.orderId}" class="btn btn-sm btn-primary" title="Cập nhật trạng thái">
                                    <i class="bi bi-gear"></i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty orders}">
                        <tr>
                            <td colspan="8" class="text-center text-muted">Không có đơn hàng nào được tìm thấy.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
        
        <c:if test="${totalPages > 1}">
            <nav aria-label="Order list navigation" class="mt-4">
                <ul class="pagination justify-content-center">
                    <li class="page-item <c:if test='${currentPage == 1}'>disabled</c:if>">
                        <a class="page-link" href="${pageContext.request.contextPath}/admin/order/list?page=${currentPage - 1}" aria-label="Previous">&laquo;</a>
                    </li>
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="page-item <c:if test='${i == currentPage}'>active</c:if>">
                            <a class="page-link" href="${pageContext.request.contextPath}/admin/order/list?page=${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>">
                        <a class="page-link" href="${pageContext.request.contextPath}/admin/order/list?page=${currentPage + 1}" aria-label="Next">&raquo;</a>
                    </li>
                </ul>
            </nav>
        </c:if>
        <div class="text-center text-muted small mt-2">
            Đang hiển thị Trang **${currentPage}** trên **${totalPages}** trang
        </div>
        
    </div>