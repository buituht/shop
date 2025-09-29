<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="core1" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
<style>

/* Thiết lập chung */
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #e9ebee;
    color: #333;
}

.container {
    width: 90%;
    max-width: 1200px;
    margin: auto;
    overflow: hidden;
    padding: 20px 0;
}

/* Kiểu cho header */
.admin-header {
    background: #34495e;
    color: #ecf0f1;
    padding: 20px 0;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.admin-header h1 {
    margin: 0;
    font-size: 1.8em;
    float: left;
}

.admin-nav ul {
    list-style: none;
    padding: 0;
    margin: 0;
    float: right;
}

.admin-nav ul li {
    display: inline-block;
    padding: 0 15px;
}

.admin-nav a {
    color: #ecf0f1;
    text-decoration: none;
    font-weight: 600;
    transition: color 0.3s;
}

.admin-nav a:hover {
    color: #3498db;
}

/* Kiểu cho nội dung chính */
.admin-main {
    padding: 20px 0;
}

.admin-content {
    background: #fff;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.admin-content h2 {
    color: #2c3e50;
    margin-top: 0;
    border-bottom: 2px solid #bdc3c7;
    padding-bottom: 10px;
    font-size: 2em;
}

/* Kiểu cho các widget trên dashboard */
.dashboard-widgets {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 20px;
    margin-top: 20px;
}

.widget {
    background: #f1f2f6;
    border: 1px solid #dfe4ea;
    padding: 20px;
    border-radius: 8px;
    text-align: center;
    transition: transform 0.3s ease;
}

.widget:hover {
    transform: translateY(-5px);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.widget h3 {
    margin: 0 0 10px;
    color: #3498db;
}

.widget p {
    font-size: 2.5em;
    font-weight: bold;
    color: #2c3e50;
    margin: 0;
}

/* Kiểu cho khu vực biểu đồ */
.dashboard-chart {
    background: #fff;
    padding: 20px;
    margin-top: 20px;
    border-radius: 8px;
    border: 1px solid #dfe4ea;
}

/* Kiểu cho footer */
.admin-footer {
    background: #34495e;
    color: #ecf0f1;
    text-align: center;
    padding: 15px 0;
    margin-top: 20px;
}
</style>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Quản Trị - Dashboard</title>
    <link rel="stylesheet" href="admin-style.css">
</head>
<body>

    <header class="admin-header">
        <div class="container">
            <h1>Bảng điều khiển Admin</h1>
            <nav class="admin-nav">
                <ul>
                    <li><a href="#">Dashboard</a></li>
                    <li><a href="#">Quản lý người dùng</a></li>
                    <li><a href="${pageContext.request.contextPath }/admin/product/list">Quản lý sản phẩm</a></li>
			        <li><a href="#">Cài đặt</a></li>
			         <core1:choose>           
			                    	<core1:when test="${sessionScope.account == null}">
					
							<li><a href="${pageContext.request.contextPath }/login">Đăng nhập</a>
							| <a href="${pageContext.request.contextPath }/register">Đăng ký</a></li>
							
							<li><i class="search fa fa-search search-button"></i></li>
							
							
							</core1:when>
							<core1:otherwise>
							
							
					                    <li><a href="${pageContext.request.contextPath
							}/member/myaccount">${sessionScope.account.fullName}</a> | <a
							href="${pageContext.request.contextPath }/logout">Đăng Xuất</a></li>
							</core1:otherwise>
					
					</core1:choose>
					
                </ul>
            </nav>
        </div>
    </header>

    <main class="admin-main">
        <div class="container">
            <section class="admin-content">
                <h2>Chào mừng, Admin ${sessionScope.account.fullName}!</h2>
                <p>Đây là bảng điều khiển chính. Tại đây, bạn có thể xem tổng quan về hệ thống và quản lý các chức năng.</p>
                
                <div class="dashboard-widgets">
                    <div class="widget">
                        <h3>Người dùng mới</h3>
                        <p><strong>150</strong></p>
                    </div>
                    <div class="widget">
                        <h3>Sản phẩm đã bán</h3>
                        <p><strong>2,100</strong></p>
                    </div>
                    <div class="widget">
                        <h3>Lượt truy cập</h3>
                        <p><strong>12,500</strong></p>
                    </div>
                </div>

                <div class="dashboard-chart">
                    <h3>Biểu đồ thống kê</h3>
                    <p>Biểu đồ doanh thu hàng tháng sẽ được hiển thị ở đây.</p>
                </div>
            </section>
        </div>
    </main>

    <footer class="admin-footer">
        <div class="container">
            <p>&copy; 2024 Admin Panel. Mọi quyền được bảo lưu.</p>
        </div>
    </footer>

</body>
</html>