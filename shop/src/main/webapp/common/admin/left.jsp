<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div class="col-sm-3 sidenav">
      <h4>Trang Admin</h4>
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="${pageContext.request.contextPath}/admin/home">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/category/list">Danh mục</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/product/list">Sản phẩm</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/order/list">Đơn hàng</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/user/list">Người dùng</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/article/list">Bài viết</a></li>
		<li><a href="#">Cài đặt hệ thống</a></li>
      </ul><br>
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Tìm kiếm..">
        <span class="input-group-btn">
          <button class="btn btn-default" type="button">
            <span class="glyphicon glyphicon-search"></span>
          </button>
        </span>
      </div>
</div>