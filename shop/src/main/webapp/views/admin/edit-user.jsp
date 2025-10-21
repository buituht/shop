<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="col-sm-9">
    <h1><small>Chỉnh Sửa Người Dùng: ${user.userName}</small></h1>
    <hr/>
    <form action="${pageContext.request.contextPath}/admin/user/edit" method="post" class="form-horizontal p-4 border rounded shadow-sm bg-white">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="id" value="${user.id}">
        
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="${user.userName}" disabled>
        </div>
        <div class="mb-3">
            <label for="fullName" class="form-label">Họ và Tên</label>
            <input type="text" class="form-control" id="fullName" name="fullName" value="${user.fullName}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email (*)</label>
            <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
        </div>
        <div class="mb-3">
            <label for="roleId" class="form-label">Vai trò</label>
            <select class="form-select" id="roleId" name="roleId">
                <option value="1" <c:if test="${user.roleid == 1}">selected</c:if>>Admin</option>
                <option value="2" <c:if test="${user.roleid == 2}">selected</c:if>>Manager</option>
                <option value="3" <c:if test="${user.roleid == 3}">selected</c:if>>User</option>
                <option value="0" <c:if test="${user.roleid == 0}">selected</c:if>>Khách</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Số điện thoại</label>
            <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}">
        </div>
        
        <button type="submit" class="btn btn-success me-2">
            <i class="bi bi-save"></i> Lưu Thay Đổi
        </button>
        
    </form>
</div>