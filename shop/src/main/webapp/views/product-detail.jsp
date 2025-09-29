<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %> 
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${product.name} | Chi tiết sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    
    <style>
        .product-image-box {
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 8px;
        }
        .product-image-box img {
            max-height: 500px;
            object-fit: contain;
            width: 100%;
        }
    </style>
</head>
<body>
    
    <div class="container mt-5 mb-5">
        <div class="row">
            
            <div class="col-md-6">
                <div class="product-image-box shadow-sm bg-white">
                    <img src="/product_images/${product.image}" class="img-fluid rounded" alt="${product.name}">
                </div>
                </div>
            
            <div class="col-md-6">
                <h1 class="display-5 fw-bold mb-3">${product.name}</h1>
                
                <h2 class="text-danger mb-4">
                    <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                </h2>
                
                <p class="text-muted lead">${product.description.length() > 200 ? product.description.substring(0, 200).concat('...') : product.description}</p>
                
                <form action="${pageContext.request.contextPath}/cart/add" method="post">
				    <input type="hidden" name="productId" value="${product.id}"> 
				
				    <div class="d-flex align-items-center mb-4">
				        <input type="number" name="quantity" class="form-control me-3" value="1" min="1" style="width: 80px;">
				        
				        <button type="submit" class="btn btn-success btn-lg me-3">
				            <i class="bi bi-cart-plus"></i> Thêm vào giỏ hàng
				        </button>
				        
				        <button type="button" class="btn btn-outline-danger btn-lg">
				            <i class="bi bi-heart"></i> Yêu thích
				        </button>
				    </div>
				</form>
                
                <hr>
                
                <div class="mt-4">
                    <h4 class="mb-3">Mô tả chi tiết</h4>
                    <div class="card card-body bg-light">
                        <p>${product.description}</p>
                        </div>
                </div>
                
            </div>
        </div>
        
        <div class="mt-5">
             <a href="${pageContext.request.contextPath}/products" class="btn btn-secondary">
                 <i class="bi bi-arrow-left"></i> Quay lại danh sách sản phẩm
             </a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>