<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="core1" uri="jakarta.tags.core" %>
 
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

    <style>
        /* Thiết lập chung cho body và form container */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.register-container {
    background-color: #fff;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px;
    box-sizing: border-box;
}

h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
}

/* Kiểu cho mỗi group form */
.form-group {
    margin-bottom: 15px;
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #555;
}

input[type="text"],
input[type="email"],
input[type="password"],
input[type="tel"],
input[type="file"],
select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 16px;
    transition: border-color 0.3s;
}

input:focus,
select:focus {
    border-color: #007bff;
    outline: none;
}

/* Kiểu cho nút Đăng Ký */
button[type="submit"] {
    width: 100%;
    padding: 12px;
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    color: white;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s
    </style>
</head>
<body>




<core1:if test="${alert !=null}">
<h3 class="alert alertdanger">${alert}</h3>
</core1:if>



<div class="register-container">
    <h2>Tạo tài khoản mới</h2>
    <form action="register" method="post">
       
<div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    </div>

    <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
    </div>

    <div class="form-group">
        <label for="fullname">Full Name:</label>
        <input type="text" id="fullname" name="fullname" required>
    </div>

    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>


    <div class="form-group">
	    <label for="roleid">Role:</label>
	    <select id="roleid" name="roleid" required>
	        <option value="">Select a role</option>
	        <option value="1">Admin</option>
	        <option value="2">Editor</option>
	        <option value="3">User</option>
	        <option value="4">Guest</option>
	    </select>
	</div>

 <div class="form-group">
    <label for="avatar">Upload Avatar:</label>
    <input type="file" id="avatar" name="avatar" accept="image/*">
</div>
    <div class="form-group">
        <label for="phone">Phone Number:</label>
        <input type="tel" id="phone" name="phone">
    </div>
        
        <button type="submit">Đăng Ký</button>
    </form>
</div>



</body>
</html>