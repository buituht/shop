<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %> 


    
    <div class="container mt-5">
        <h2 class="text-center mb-5 text-dark">Khám phá các Sản phẩm của chúng tôi</h2>
        
        <div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4">
            
            <c:forEach var="product" items="${products}">
                <div class="col">
                    <div class="card h-100 product-card shadow-sm">
                        <img src="/product_images/${product.image}" class="card-img-top product-img" alt="${product.name}">
                        
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title text-truncate">${product.name}</h5>
                            
                            <p class="card-text text-muted small mb-2">
                                ${product.description.length() > 80 ? product.description.substring(0, 80).concat('...') : product.description}
                            </p>
                            
                            <h4 class="text-danger mt-auto mb-3">
                                <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                            </h4>
                            
                            <div class="d-grid gap-2">
                                <a href="${pageContext.request.contextPath}/product/detail?id=${product.id}" class="btn btn-outline-primary">
                                    <i class="bi bi-info-circle"></i> Xem chi tiết
                                </a>
                                <button class="btn btn-success">
                                    <i class="bi bi-cart-plus"></i> Thêm vào giỏ hàng
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            
            <c:if test="${empty products}">
                <div class="col-12">
                    <div class="alert alert-warning text-center" role="alert">
                        Chưa có sản phẩm nào được hiển thị.
                    </div>
                </div>
            </c:if>

        </div>
        </div>

