<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="col-sm-9">
    <h1><small>Thêm Người Dùng Mới</small></h1>
    <hr/>
    <form action="${pageContext.request.contextPath}/admin/user/add" method="post">
        <input type="hidden" name="action" value="add">
        
        <div class="mb-3">
            <label for="username" class="form-label">Tên Tài Khoản (*)</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mật Khẩu (*)</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="mb-3">
            <label for="fullName" class="form-label">Họ và Tên</label>
            <input type="text" class="form-control" id="fullName" name="fullName">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email (*)</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>
        <div class="mb-3">
            <label for="roleId" class="form-label">Vai trò</label>
            <select class="form-select" id="roleId" name="roleId">
                <option value="1">Admin</option>
                <option value="2" selected>User</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Số điện thoại</label>
            <input type="text" class="form-control" id="phone" name="phone">
        </div>
        
        <button type="submit" class="btn btn-success">
            <i class="bi bi-save"></i> Thêm Người Dùng
        </button>
        <a href="${pageContext.request.contextPath}/admin/user/list" class="btn btn-secondary">Hủy</a>
    </form>
</div>