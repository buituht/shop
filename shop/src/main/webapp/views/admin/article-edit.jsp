<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<div class="content-wrapper">
    <div class="container-fluid">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value='/admin/home'/>">Admin</a></li>
            <li class="breadcrumb-item active">Chỉnh Sửa Bài viết</li>
        </ol>
        
        <div class="card mb-3">
            <div class="card-header">Chỉnh Sửa Bài Viết ID: ${article.articleId}</div>
            <div class="card-body">
                
                <form action="<c:url value='/admin/article/edit'/>" method="POST" enctype="multipart/form-data">
                    
                    <input type="hidden" name="articleId" value="${article.articleId}"/>
                    
                    <div class="form-group">
                        <label for="title">Tiêu đề Bài viết</label>
                        <input type="text" class="form-control" id="title" name="title" required
                               value="${article.title}">
                    </div>

                    <div class="form-group">
                        <label for="summary">Tóm tắt ngắn</label>
                        <textarea class="form-control" id="summary" name="summary" rows="3">${article.summary}</textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="content">Nội dung chi tiết</label>
                        <textarea class="form-control" id="content" name="content" rows="10">${article.content}</textarea>
                    </div>
                    
                    <div class="form-group">
                        <label for="status">Trạng thái</label>
                        <select class="form-control" id="status" name="status">
                            <option value="Published" ${article.status eq 'Published' ? 'selected' : ''}>Xuất bản (Published)</option>
                            <option value="Draft" ${article.status eq 'Draft' ? 'selected' : ''}>Nháp (Draft)</option>
                            <option value="Hidden" ${article.status eq 'Hidden' ? 'selected' : ''}>Ẩn (Hidden)</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="imageFile">Ảnh Đại diện</label>
                        <input type="file" class="form-control-file" id="imageFile" name="imageFile">
                        <c:if test="${article.image != null}">
                            <small class="form-text text-muted">Ảnh hiện tại: ${article.image}</small>
                            </c:if>
                    </div>

                    <button type="submit" class="btn btn-success">Cập Nhật Bài Viết</button>
                    <a href="<c:url value='/admin/article/list'/>" class="btn btn-secondary">Hủy</a>
                </form>
            </div>
        </div>
    </div>
</div>
