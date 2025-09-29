/*
 * 

package vn.iotstar.controllers;

import vn.iotstar.models.User;
import vn.iotstar.daos.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login-cookie")
public class LoginCookie extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Gọi Model để xác thực
        User user = userDAO.authenticate(username, password);

        if (user != null) {
            // Tạo cookie và thêm vào response
            Cookie userCookie = new Cookie("user", user.getUsername());
            userCookie.setMaxAge(60 * 60); // Lưu cookie 1 giờ
            response.addCookie(userCookie);

            // Chuyển hướng đến View thành công
            //response.sendRedirect("views/welcome_cookie.jsp");
            request.setAttribute("username", username);
            request.getRequestDispatcher("views/welcome_cookie.jsp").forward(request, response);
        } else {
            // Chuyển hướng về View đăng hập thất bại
        	request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
        	request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            //response.sendRedirect("views/login.jsp?error=invalid");
        }
    }
}

 */
