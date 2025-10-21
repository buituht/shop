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


@WebFilter(urlPatterns="/admin/*")
public class AdminFilter implements Filter {

    private static final int ROLE_ADMIN_ID = 1; // Admin role = 1

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
        HttpSession session = req.getSession(false); 
        UserModel user = null;
        
        if (session != null) {
            
            
            Object userObj = session.getAttribute("account"); 
            
            if (userObj instanceof UserModel) {
                user = (UserModel) userObj;
            }
        }
        
        // *Luồng 2: Kiểm tra Quyền hạn*
        // Nếu user tồn tại VÀ roleid khớp với ROLE_ADMIN_ID (1)
        if (user != null && user.getRoleid() == ROLE_ADMIN_ID) {
            
            // Hợp lệ -> Cho phép đi tiếp
            chain.doFilter(request, response);
            return; 
            
        } else {
            // Không hợp lệ (Chưa đăng nhập, Session hết hạn, hoặc không có quyền Admin)

            String contextPath = req.getContextPath(); 
            
            // Chuyển hướng người dùng đến trang đăng nhập.
            resp.sendRedirect(contextPath + "/login"); 
        }
    }
}