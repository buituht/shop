<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4 text-primary">Danh Sách Danh mục</h1>
        
        <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-primary mb-3">
            <i class="bi bi-plus-circle"></i> Thêm Danh mục mới
        </a>
        
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover shadow-sm">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Tên Danh mục</th>
                        <th>Hình ảnh</th>
                        <th>Trạng thái</th>
                        <th class="text-center">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="category" items="${categories}">
                        <tr>
                            <td>${category.catId}</td>
                            <td>${category.cateName}</td>
                            <td>
                                <img src="/product_images/${category.image}" alt="${category.cateName}" width="60" class="img-fluid rounded">
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${category.status == 1}">
                                        <span class="badge bg-success">Hoạt động</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">Ngừng hoạt động</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/admin/category/edit?id=${category.catId}" class="btn btn-sm btn-warning me-2">Sửa</a>
                                <a href="${pageContext.request.contextPath}/admin/category/delete?id=${category.catId}" class="btn btn-sm btn-danger">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>