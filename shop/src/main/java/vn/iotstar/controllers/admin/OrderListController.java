package vn.iotstar.controllers.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import vn.iotstar.models.OrderModel;
import vn.iotstar.services.OrderService;
import vn.iotstar.services.impl.OrderServiceImpl;

@WebServlet(urlPatterns = {"/admin/order/list"})
public class OrderListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private OrderService orderService = new OrderServiceImpl();
    private static final int ITEMS_PER_PAGE = 10; 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 1. Xử lý tham số phân trang
        String pageStr = req.getParameter("page");
        int currentPage = 1;
        if (pageStr != null) {
            try {
                currentPage = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        
        int offset = (currentPage - 1) * ITEMS_PER_PAGE;
        
        // 2. Lấy dữ liệu
        List<OrderModel> orders = orderService.findAllOrdersPaging(offset, ITEMS_PER_PAGE);
        int totalOrders = orderService.countAllOrders();
        int totalPages = (int) Math.ceil((double) totalOrders / ITEMS_PER_PAGE);

        // 3. Đặt các biến vào request
        req.setAttribute("orders", orders);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("totalPages", totalPages);
        
        // 4. Chuyển hướng đến trang JSP
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-order.jsp");
        dispatcher.forward(req, resp);
    }
}