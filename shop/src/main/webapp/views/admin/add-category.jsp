<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4 text-primary border-bottom pb-2">Thêm Danh mục mới</h1>
        
        <form action="${pageContext.request.contextPath}/admin/category/add" method="post" enctype="multipart/form-data" class="p-4 border rounded shadow-sm bg-white">
            
            <div class="mb-3">
                <label for="cateName" class="form-label fw-bold">Tên Danh mục:</label>
                <input type="text" id="cateName" name="cateName" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="image" class="form-label fw-bold">Hình ảnh:</label>
                <input type="file" id="image" name="image" class="form-control">
            </div>

            <div class="mb-3">
                <label for="status" class="form-label fw-bold">Trạng thái:</label>
                <select name="status" id="status" class="form-select">
                    <option value="1">Hoạt động</option>
                    <option value="0">Ngừng hoạt động</option>
                </select>
            </div>

            <div class="d-flex justify-content-between mt-4">
                <button type="submit" class="btn btn-success me-2">Thêm Danh mục</button>
                <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-secondary">Quay lại</a>
            </div>
            
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>