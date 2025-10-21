<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="col-sm-9">
      <h1><small>Chỉnh sửa sản phẩm</small></h1>
        <hr/>
        
        <form action="${pageContext.request.contextPath}/admin/product/edit" method="post" enctype="multipart/form-data" class="form-horizontal p-4 border rounded shadow-sm bg-white">
            
            <input type="hidden" name="id" value="${product.id}">
            <input type="hidden" name="oldImage" value="${product.image}">

            <div class="mb-3">
                <label for="cateId" class="form-label fw-bold">Danh mục:</label>
                <select name="cateId" id="cateId" class="form-select">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.catId}" ${category.catId == product.cateId ? 'selected' : ''}>
                            ${category.cateName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="name" class="form-label fw-bold">Tên Sản phẩm:</label>
                <input type="text" id="name" name="name" value="${product.name}" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label fw-bold">Giá:</label>
                <input type="number" id="price" name="price" value="${product.price}" step="0.01" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label fw-bold">Hình ảnh hiện tại:</label><br>
                <img src="/product_images/${product.image}" alt="${product.name}" width="150" class="img-fluid rounded border p-1">
            </div>

            <div class="mb-3">
                <label for="image" class="form-label fw-bold">Chọn hình ảnh mới:</label>
                <input type="file" id="image" name="image" class="form-control">
            </div>

            <div class="mb-3">
                <label for="description" class="form-label fw-bold">Mô tả:</label>
                <textarea id="description" name="description" class="form-control" rows="4">${product.description}</textarea>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-success me-2">Lưu Thay Đổi</button>
                <a href="${pageContext.request.contextPath}/admin/product/list" class="btn btn-secondary">Quay lại</a>
            </div>
            
        </form>
</div>
