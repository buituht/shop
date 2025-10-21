package vn.iotstar.controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;


@WebServlet(urlPatterns="/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 7; 

    // Logic doGet giữ nguyên để kiểm tra Session/Cookie
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            // Chuyển thẳng đến logic điều hướng mới (từ doPost)
            redirectBasedOnRole(request, response);
            return;
        }

        Cookie[] cookies = request.getCookies();
        String usernameFromCookie = null;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    usernameFromCookie = cookie.getValue();
                    break;
                }
            }
        }
        
        if (usernameFromCookie != null) {
            UserService service = new UserServiceImpl();
            // Giả định service.findByUserName(username) tồn tại
            UserModel user = service.findByUserName(usernameFromCookie); 
            
            if (user != null) {
                session = request.getSession(true);
                session.setAttribute("account", user);
                // Chuyển thẳng đến logic điều hướng mới
                redirectBasedOnRole(request, response);
                return;
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
        dispatcher.forward(request, response);
    }

    // =================================================================
    // XỬ LÝ POST REQUEST (Xác thực Form và Điều hướng)
    // =================================================================

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        boolean isRememberMe = "on".equals(request.getParameter("remember"));
        String alertMsg = "";

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
            request.setAttribute("alert", alertMsg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        UserService service = new UserServiceImpl(); 
        UserModel user = service.login(username, password);

        if (user != null) {
            // 1. Đăng nhập thành công -> Tạo Session
            HttpSession session = request.getSession(true);
            session.setAttribute("account", user);

            // 2. Xử lý Remember Me
            if (isRememberMe) {
                saveRememberMe(response, user.getUserName());
            } else {
                clearRememberMe(response);
            }

            // 3. Điều hướng dựa trên Vai trò (Logic cũ của WaitingController)
            redirectBasedOnRole(request, response);

        } else {
            // Đăng nhập thất bại
            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
            request.setAttribute("alert", alertMsg);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    // =================================================================
    // PHƯƠNG THỨC HỖ TRỢ: Điều hướng theo Vai trò
    // =================================================================
    
    private void redirectBasedOnRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        UserModel u = (UserModel) session.getAttribute("account");
        
        if (u.getRoleid() == 1) {
            // Admin: Chuyển hướng đến Admin Dashboard
            resp.sendRedirect(req.getContextPath() + "/admin/home"); 
        } else if (u.getRoleid() == 2) {
            // Manager: Chuyển hướng đến Manager Home
            resp.sendRedirect(req.getContextPath() + "/manager/home");
        } else {
            // User thường: Chuyển hướng đến Home Page
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
    
    // =================================================================
    // PHƯƠNG THỨC HỖ TRỢ: Xử lý Cookie
    // =================================================================

    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie usernameCookie = new Cookie("username", username);
        usernameCookie.setMaxAge(COOKIE_MAX_AGE); 
        usernameCookie.setPath("/");
        response.addCookie(usernameCookie);
    }
    
    private void clearRememberMe(HttpServletResponse response) {
        Cookie usernameCookie = new Cookie("username", "");
        usernameCookie.setMaxAge(0); 
        usernameCookie.setPath("/");
        response.addCookie(usernameCookie);
    }
}