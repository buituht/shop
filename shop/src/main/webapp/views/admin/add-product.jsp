<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="col-sm-9">
      <h1><small>Thêm sản phẩm mới</small></h1>
        <hr/>

        
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
