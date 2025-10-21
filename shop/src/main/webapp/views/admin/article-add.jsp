<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<div class="content-wrapper">
    <div class="container-fluid">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value='/admin/home'/>">Admin</a></li>
            <li class="breadcrumb-item active">Thêm Mới Bài viết</li>
        </ol>
        
        <div class="card mb-3">
            <div class="card-header">Thêm Bài Viết Mới</div>
            <div class="card-body">
                
                <form action="<c:url value='/admin/article/add'/>" method="POST" enctype="multipart/form-data">
                    
                    <div class="form-group">
                        <label for="title">Tiêu đề Bài viết</label>
                        <input type="text" class="form-control" id="title" name="title" required placeholder="Nhập tiêu đề bài viết">
                    </div>

                    <div class="form-group">
                        <label for="summary">Tóm tắt ngắn</label>
                        <textarea class="form-control" id="summary" name="summary" rows="3" placeholder="Tóm tắt nội dung chính"></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="content">Nội dung chi tiết</label>
                        <textarea class="form-control" id="content" name="content" rows="10" placeholder="Nội dung bài viết"></textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="status">Trạng thái</label>
                        <select class="form-control" id="status" name="status">
                            <option value="Draft" selected>Nháp (Draft)</option>
                            <option value="Published">Xuất bản (Published)</option>
                            <option value="Hidden">Ẩn (Hidden)</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="imageFile">Ảnh Đại diện</label>
                        <input type="file" class="form-control-file" id="imageFile" name="imageFile">
                    </div>

                    <button type="submit" class="btn btn-success">Thêm Bài Viết</button>
                    <a href="<c:url value='/admin/article/list'/>" class="btn btn-secondary">Hủy</a>
                </form>
            </div>
        </div>
    </div>
</div>
