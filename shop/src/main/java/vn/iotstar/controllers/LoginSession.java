/*

package vn.iotstar.controllers;
import vn.iotstar.daos.UserDao;
import vn.iotstar.models.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login-session")
public class LoginSession extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDAO = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Gọi Model để xác thực
        User user = userDAO.authenticate(username, password);

        if (user != null) {
            // Lấy session và lưu thông tin
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user.getUsername());

            // Chuyển hướng đến View thành công
            //response.sendRedirect("welcome_session.jsp");
            request.getRequestDispatcher("views/welcome_session.jsp").forward(request, response);
        } else {
            // Chuyển hướng về View lỗi
        	request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
        	request.getRequestDispatcher("/views/login.jsp").forward(request, response);
           // response.sendRedirect("login.jsp?error=invalid");
        }
    }
}

*/