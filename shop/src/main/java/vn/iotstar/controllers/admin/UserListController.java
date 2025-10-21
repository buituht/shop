package vn.iotstar.controllers.admin;
// ... (imports) ...

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/user/list"})
public class UserListController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	 private UserService userService = new UserServiceImpl(); 
    // ... (Code) ...
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	 // GỌI PHƯƠNG THỨC BẰNG INSTANCE (userService)
        List<UserModel> users = userService.findAll(); 

        // Đặt danh sách vào request
        req.setAttribute("users", users);
        
        // Chuyển hướng đến trang JSP
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-user.jsp");
        dispatcher.forward(req, resp);
    }
}