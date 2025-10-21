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

@WebServlet(urlPatterns = {"/admin/user/edit"})
public class UserEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private UserService userService = new UserServiceImpl();

    /**
     * Xử lý yêu cầu GET: Hiển thị form chỉnh sửa người dùng
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            // Lấy ID người dùng từ tham số URL
            String userIdStr = req.getParameter("id");
            if (userIdStr == null || userIdStr.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/admin/user/list?error=id_missing");
                return;
            }
            
            int userId = Integer.parseInt(userIdStr);
            
            // Tìm người dùng trong DB
            UserModel user = userService.findById(userId);
            
            if (user == null) {
                // Nếu không tìm thấy, chuyển hướng về trang list với thông báo lỗi
                resp.sendRedirect(req.getContextPath() + "/admin/user/list?error=not_found");
                return;
            }
            
            // Đặt đối tượng user vào request để truyền sang JSP
            req.setAttribute("user", user);
            
            // Chuyển hướng đến trang JSP chỉnh sửa
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-user.jsp");
            dispatcher.forward(req, resp);
            
        } catch (NumberFormatException e) {
            // Xử lý lỗi nếu ID không phải là số
            resp.sendRedirect(req.getContextPath() + "/admin/user/list?error=invalid_id");
        }
    }

    /**
     * Xử lý yêu cầu POST: Cập nhật thông tin người dùng
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            // 1. Lấy ID và thông tin cần cập nhật từ form
            int id = Integer.parseInt(req.getParameter("id"));
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String fullName = req.getParameter("fullName");
            String phone = req.getParameter("phone");
            int roleId = Integer.parseInt(req.getParameter("roleId"));
            // String avatar = req.getParameter("avatar"); // Nếu có chức năng upload file

            // 2. Lấy đối tượng user hiện tại từ DB (để giữ lại các thông tin khác như password hash, createdDate...)
            UserModel user = userService.findById(id); 

            if (user == null) {
                resp.sendRedirect(req.getContextPath() + "/admin/user/list?error=not_found_update");
                return;
            }

            // 3. Cập nhật các trường thông tin mới
            user.setUserName(username);
            user.setEmail(email);
            user.setFullName(fullName);
            user.setPhone(phone);
            user.setRoleid(roleId);
            // user.setAvatar(avatar); // Cập nhật nếu có file upload

            // 4. Gọi Service để cập nhật vào DB
            userService.update(user);

            // 5. Chuyển hướng về trang danh sách với thông báo thành công
            resp.sendRedirect(req.getContextPath() + "/admin/user/list?msg=edit_success");
            
        } catch (NumberFormatException e) {
            // Lỗi khi chuyển đổi ID hoặc RoleID sang số
            req.setAttribute("errorMessage", "Dữ liệu ID hoặc Vai trò không hợp lệ.");
            // Giữ nguyên tham số ID và forward lại form để người dùng sửa
            req.getRequestDispatcher("/views/admin/edit-user.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            // Lỗi hệ thống/DB
            req.setAttribute("errorMessage", "Đã xảy ra lỗi hệ thống khi cập nhật.");
            req.getRequestDispatcher("/views/admin/edit-user.jsp").forward(req, resp);
        }
    }
}