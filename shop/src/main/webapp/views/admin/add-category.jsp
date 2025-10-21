<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="col-sm-9">
      <h1><small>Thêm Danh mục mới</small></h1>
        <hr/>

        <form action="${pageContext.request.contextPath}/admin/category/add" method="post" enctype="multipart/form-data" class="p-4 border rounded shadow-sm bg-white">
            
           <div class="form-group">
                <label for="cateName" class="form-label fw-bold">Tên Danh mục:</label>
                <input type="text" id="cateName" name="cateName" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="image" class="form-label fw-bold">Hình ảnh:</label>
                <input type="file" id="image" name="image" class="form-control">
            </div>

            <div class="form-group">
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

