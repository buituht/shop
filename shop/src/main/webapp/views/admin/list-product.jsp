<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="col-sm-9">
      <h1><small>Danh sách sản phẩm</small></h1>
        <hr/>
        <p>
        <a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-primary mb-3">
            <i class="bi bi-plus-circle"></i> Thêm Danh mục mới
        </a>
        </p>
        <br/>
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover shadow-sm">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Tên Sản phẩm</th>
                        <th>Giá</th>
                        <th>Hình ảnh</th>
                        <th>Mô tả</th>
                        <th class="text-center">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.price}</td>
                            <td>
                                <img src="/product_images/${product.image}" alt="${product.name}" width="80" class="img-fluid rounded border img-thumbnail">
                            </td>
                            <td>${product.description}</td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/admin/product/edit?id=${product.id}" class="btn btn-sm btn-primary">
                                    <i class="bi bi-pencil-square"></i> Sửa
                                </a>
                                <a href="${pageContext.request.contextPath}/admin/product/delete?id=${product.id}" class="btn btn-sm btn-warning">
                                    <i class="bi bi-trash"></i> Xóa
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
