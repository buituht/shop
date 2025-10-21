package vn.iotstar.filters; 

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iotstar.models.UserModel; 


// Ánh xạ Filter đến TẤT CẢ các URL bắt đầu bằng /admin/
@WebFilter(urlPatterns="/admin/*")
public class AdminFilter implements Filter {

    // Giả định ROLE_ADMIN_ID là một hằng số được định nghĩa cho Admin (ví dụ: 1)
    private static final int ROLE_ADMIN_ID = 1;

    // ==========================================================
    // CÁC PHƯƠNG THỨC VÒNG ĐỜI (LIFECYCLE METHODS)
    // ==========================================================

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
       
        System.out.println("AdminFilter initialized!");
    }

    @Override
    public void destroy() {
        System.out.println("AdminFilter destroyed!");
    }

    // ==========================================================
    // PHƯƠNG THỨC LỌC CHÍNH (MAIN FILTER METHOD)
    // ==========================================================

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        // *Luồng 1: Kiểm tra Đăng nhập*
        // Lấy session hiện tại. Nếu chưa có, trả về null.
        HttpSession session = req.getSession(false); 
        UserModel user = null;
        
        if (session != null) {
             // Giả định bạn lưu UserModel dưới khóa "currentUser" hoặc "user"
             Object userObj = session.getAttribute("currentUser");
             if (userObj instanceof UserModel) {
                 user = (UserModel) userObj;
             }
        }
    
        
        // *Luồng 2: Kiểm tra Quyền hạn*
      
        if (user != null && user.getRoleid() == ROLE_ADMIN_ID) {
            
   
            chain.doFilter(request, response);
            return; // Dừng xử lý tại đây
            
        } else {
            // Không hợp lệ (Chưa đăng nhập hoặc không có quyền Admin)

            // Lấy Context Path để chuyển hướng chính xác: /shop
            String contextPath = req.getContextPath(); 
            
            // Chuyển hướng người dùng đến trang đăng nhập.
            resp.sendRedirect(contextPath + "/login"); 
            
           
        }
    }
}