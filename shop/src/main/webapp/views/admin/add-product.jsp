<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4 text-primary border-bottom pb-2">Thêm Sản phẩm mới</h1>
        
        <form action="${pageContext.request.contextPath}/admin/product/add" method="post" enctype="multipart/form-data" class="p-4 border rounded shadow-sm bg-white">
            
            <div class="mb-3">
                <label for="cateId" class="form-label fw-bold">Danh mục:</label>
                <select name="cateId" id="cateId" class="form-select">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.catId}">${category.cateName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="name" class="form-label fw-bold">Tên Sản phẩm:</label>
                <input type="text" id="name" name="name" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label fw-bold">Giá:</label>
                <input type="number" id="price" name="price" step="0.01" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="image" class="form-label fw-bold">Hình ảnh:</label>
                <input type="file" id="image" name="image" class="form-control">
            </div>

            <div class="mb-3">
                <label for="description" class="form-label fw-bold">Mô tả:</label>
                <textarea id="description" name="description" class="form-control" rows="4"></textarea>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-success me-2">Thêm Sản phẩm</button>
                <a href="${pageContext.request.contextPath}/admin/product/list" class="btn btn-secondary">Quay lại</a>
            </div>
            
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>