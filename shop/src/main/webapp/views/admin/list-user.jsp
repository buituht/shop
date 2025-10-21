<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %> 
<%-- Đảm bảo file này được include vào layout Admin chính của bạn (nơi có header, footer và sidebar) --%>

<div class="col-sm-9">
    <h1><small>Quản Lý Người Dùng</small></h1>
    <hr/>
    
    <%-- Hiển thị thông báo (ví dụ: sau khi xóa, thêm, sửa thành công) --%>
    <c:if test="${not empty param.msg}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <c:choose>
                <c:when test="${param.msg eq 'add_success'}">
                    <strong>Thành công!</strong> Đã thêm người dùng mới.
                </c:when>
                <c:when test="${param.msg eq 'edit_success'}">
                    <strong>Thành công!</strong> Đã cập nhật thông tin người dùng.
                </c:when>
                <c:when test="${param.msg eq 'delete_success'}">
                    <strong>Thành công!</strong> Đã xóa người dùng.
                </c:when>
                <c:otherwise>
                    Thao tác thành công!
                </c:otherwise>
            </c:choose>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover shadow-sm">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Họ và Tên</th>
                    <th>Vai trò</th>
                 
                    <th class="text-center">Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                        <td>${user.email}</td>
                        <td>${user.fullName}</td>
                        <td>
                            <%-- Hiển thị tên vai trò dựa trên roleid --%>
                            <c:choose>
                                <c:when test="${user.roleid == 1}">
                                    <span class="badge bg-danger">Admin</span>
                                </c:when>
                                <c:when test="${user.roleid == 2}">
                                    <span class="badge bg-success">Manager</span>
                                </c:when>
                                <c:when test="${user.roleid == 3}">
                                    <span class="badge bg-success">User</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-secondary">Khách</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                 
                        <td class="text-center">
                            <a href="${pageContext.request.contextPath}/admin/user/edit?id=${user.id}" class="btn btn-sm btn-primary" title="Chỉnh sửa">
                                <i class="bi bi-pencil-square">Sửa</i>
                            </a>
                            <%-- Nút Xóa có xác nhận bằng JavaScript --%>
                            <!--  
                            <a href="${pageContext.request.contextPath}/admin/user/delete?id=${user.id}" 
                               class="btn btn-sm btn-warning" 
                               title="Xóa"
                               onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng [${user.userName}] không?');">
                                <i class="bi bi-trash">Xóa</i>
                            </a>
                            -->
                            
                        </td>
                    </tr>
                </c:forEach>
                
                <c:if test="${empty users}">
                    <tr>
                        <td colspan="7" class="text-center text-muted">Không có người dùng nào được tìm thấy.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>