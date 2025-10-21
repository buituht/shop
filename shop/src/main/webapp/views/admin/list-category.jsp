<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="col-sm-9">
      <h1><small>Danh sách danh mục</small></h1>
        <hr/>
        <p>
        <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-primary mb-3">
            <i class="bi bi-plus-circle"></i> Thêm Danh mục mới
        </a>
        </p>
        <br/>
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
                                <img src="/product_images/${category.image}" alt="${category.cateName}" width="60" class="img-fluid rounded img-thumbnail">
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${category.status == 1}">
                                        <span class="btn btn-success">Hoạt động</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="btn btn-danger">Ngừng hoạt động</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/admin/category/edit?id=${category.catId}" class="btn btn-primary">Sửa</a>
                                <a href="${pageContext.request.contextPath}/admin/category/delete?id=${category.catId}" class="btn btn-warning">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
 
