package vn.iotstar.controllers.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import vn.iotstar.models.UserModel;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin/user/add"})
public class UserAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserService userService = new UserServiceImpl();

    /**
     * Xử lý yêu cầu GET: Hiển thị form thêm người dùng mới
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Chỉ đơn giản chuyển hướng đến trang JSP chứa form
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-user.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Xử lý yêu cầu POST: Nhận dữ liệu từ form và thêm người dùng
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        // Biến lưu trữ thông báo lỗi/thành công
        String alertMsg = "";
        String urlForward = "/views/admin/add-user.jsp"; // URL để forward khi có lỗi

        try {
            // 1. Lấy dữ liệu từ form
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            String fullName = req.getParameter("fullName");
            String phone = req.getParameter("phone");
            
            // Xử lý RoleId (mặc định là User nếu không truyền hoặc lỗi)
            int roleId = 2; 
            try {
                roleId = Integer.parseInt(req.getParameter("roleId"));
            } catch (NumberFormatException e) {
                // Giữ roleId = 2 nếu có lỗi parse
            }
            
            String avatar = req.getParameter("avatar"); // Hoặc xử lý upload file

            // 2. Kiểm tra nghiệp vụ (Tồn tại Username/Email)
            if (userService.checkExistUsername(username)) {
                alertMsg = "Tên tài khoản này đã tồn tại!";
                req.setAttribute("alert", alertMsg);
                req.getRequestDispatcher(urlForward).forward(req, resp);
                return;
            }
            
            if (userService.checkExistEmail(email)) {
                alertMsg = "Email này đã được sử dụng cho tài khoản khác!";
                req.setAttribute("alert", alertMsg);
                req.getRequestDispatcher(urlForward).forward(req, resp);
                return;
            }

            // 3. Tạo đối tượng UserModel và gán giá trị
            UserModel newUser = new UserModel();
            newUser.setUserName(username);
            newUser.setEmail(email);
            newUser.setFullName(fullName);
            newUser.setPhone(phone);
            newUser.setRoleid(roleId);
            newUser.setAvatar(avatar != null ? avatar : "default.jpg");

            // NGHIỆP VỤ QUAN TRỌNG: Mã hóa mật khẩu trước khi lưu
            // Chúng ta gọi Service, và Service sẽ đảm nhận việc Hash mật khẩu
            newUser.setPassWord(password); 
            
            // 4. Gọi Service để chèn vào DB
            userService.insert(newUser);

            // 5. Chuyển hướng về trang danh sách với thông báo thành công
            resp.sendRedirect(req.getContextPath() + "/admin/user/list?msg=add_success");
            
        } catch (Exception e) {
            e.printStackTrace();
            // Lỗi hệ thống/DB
            alertMsg = "Đã xảy ra lỗi hệ thống khi thêm người dùng.";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(urlForward).forward(req, resp);
        }
    }
}