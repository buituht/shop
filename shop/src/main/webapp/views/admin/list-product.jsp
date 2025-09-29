<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <div class="container mt-5"> 
        <h1 class="mb-4 text-primary">Danh Sách Sản Phẩm</h1>
        
        <a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-primary mb-3">
            <i class="bi bi-plus-circle"></i> Thêm Sản phẩm mới
        </a>
        
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover shadow-sm">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Tên Sản phẩm</th>
                        <th>Giá</th>
                        <th>Hình ảnh</th>
                        <th>Mô tả</th>
                        <th class="text-center">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.price}</td>
                            <td>
                                <img src="/product_images/${product.image}" alt="${product.name}" width="80" class="img-fluid rounded border">
                            </td>
                            <td>${product.description}</td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/admin/product/edit?id=${product.id}" class="btn btn-sm btn-warning me-2">
                                    <i class="bi bi-pencil-square"></i> Sửa
                                </a>
                                <a href="${pageContext.request.contextPath}/admin/product/delete?id=${product.id}" class="btn btn-sm btn-danger">
                                    <i class="bi bi-trash"></i> Xóa
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>