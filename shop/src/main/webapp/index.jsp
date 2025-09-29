<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="core1" uri="jakarta.tags.core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
/* Thiết lập chung */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    line-height: 1.6;
    background-color: #f4f4f4;
    color: #333;
}

.container {
    width: 80%;
    margin: auto;
    overflow: hidden;
}

/* Kiểu cho phần header (đầu trang) */
header {
    background: #333;
    color: #fff;
    padding-top: 30px;
    min-height: 70px;
    border-bottom: #77aaff 3px solid;
    text-align: center;
}

header h1 {
    margin: 0;
    font-size: 2em;
}

header nav ul {
    padding: 0;
    margin: 0;
    list-style: none;
    display: flex;
    justify-content: center;
}

header nav ul li {
    padding: 0 15px 0 15px;
}

header nav a {
    color: #fff;
    text-decoration: none;
    text-transform: uppercase;
    font-size: 16px;
    font-weight: bold;
}

header a:hover {
    color: #77aaff;
}

/* Kiểu cho phần main content (nội dung chính) */
main {
    padding: 20px 0;
}

.hero-section {
    background: #fff;
    padding: 40px;
    border-radius: 8px;
    text-align: center;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.hero-section h2 {
    font-size: 2.5em;
    margin-bottom: 10px;
    color: #007bff;
}

.hero-section p {
    font-size: 1.2em;
    margin-bottom: 20px;
}

.btn {
    display: inline-block;
    padding: 10px 20px;
    background: #007bff;
    color: #fff;
    text-decoration: none;
    border-radius: 5px;
    font-weight: bold;
    transition: background-color 0.3s;
}

.btn:hover {
    background: #0056b3;
}

/* Kiểu cho phần footer (chân trang) */
footer {
    background: #333;
    color: #fff;
    text-align: center;
    padding: 20px 0;
    margin-top: 20px;
}
</style>

</head>
<body>

    <header>
    
         <core1:choose>

		<core1:when test="${sessionScope.account == null}">
		<div class="col-sm-4">
		<ul class="list-inline right-topbar pull-right">
		<li><a href="${pageContext.request.contextPath }/login">Đăng nhập</a>
		| <a href="${pageContext.request.contextPath }/register">Đăng ký</a></li>
		
		<li><i class="search fa fa-search search-button"></i></li>
		</ul>
		</div>
		</core1:when>
		
		<core1:otherwise>
		<div class="col-sm-4">
		<ul class="list-inline right-topbar pull-right">
		<li><a href="${pageContext.request.contextPath
		}/member/myaccount">${sessionScope.account.fullName}</a> | <a
		href="${pageContext.request.contextPath }/logout">Đăng Xuất</a></li>
		<li><i class="search fa fa-search search-button"></i></li>
		</ul>
		</div>
		</core1:otherwise>
		
		</core1:choose>
        <div class="container">
   

            <h1>Trang Web Của Tôi</h1>
            <nav>
                <ul>
                    <li><a href="#">Trang Chủ</a></li>
                    <li><a href="#">Giới Thiệu</a></li>
                    <li><a href="#">Dịch Vụ</a></li>
                    <li><a href="#">Liên Hệ</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <main>
        <div class="container">
            <section class="hero-section">
                <h2>Chào mừng bạn đến với trang web</h2>
                <p>Đây là một trang web đơn giản được tạo bằng HTML và CSS. Bạn có thể tùy chỉnh nội dung này để phù hợp với mục đích của mình.</p>
                <a href="#" class="btn">Tìm hiểu thêm</a>
            </section>
        </div>
    </main>

    <footer>
        <div class="container">
            <p>&copy; 2024 Trang Web Của Tôi. Mọi quyền được bảo lưu.</p>
        </div>
    </footer>

</body>
</html>