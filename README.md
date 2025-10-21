1. Chức năng chính (Quản lý và Dữ liệu)
Chương trình tập trung vào việc quản lý các đối tượng chính của một hệ thống eShop:

Quản lý Danh mục (Category):
Sử dụng CategoryModel.java để đại diện cho dữ liệu.
Hỗ trợ thao tác CRUD (Tạo, Đọc danh sách, Sửa, Xóa) thông qua tầng Service và DAO (dù code DAO chưa được cung cấp, Service đã được chuẩn bị).

Quản lý Sản phẩm (Product):
Sử dụng ProductModel.java để đại diện cho dữ liệu.
Có Controller chuyên biệt (ProductAddController.java) để xử lý việc Thêm sản phẩm và đặc biệt là xử lý Upload file hình ảnh (@MultipartConfig và lưu file vào thư mục cố định).
Quản lý Người dùng (User):
Sử dụng UserModel.java để lưu trữ thông tin người dùng (userName, passWord, roleid).
Giỏ hàng (Cart):
Sử dụng CartItemModel.java để quản lý các mục hàng trong giỏ, thường được lưu trữ trong Session.

2. Kiến trúc và Công nghệ (Servlet Core)
Chương trình được xây dựng trên kiến trúc MVC (Model-View-Controller) cơ bản với các thành phần:
Controller (Servlet): Xử lý yêu cầu HTTP và điều phối (ví dụ: ProductAddController, AdminHomeController).
Sử dụng Annotation @WebServlet để ánh xạ URL.
Model (POJO): Chứa dữ liệu (như đã liệt kê ở trên)
View (JSP): Dùng để hiển thị giao diện người dùng và Admin (ví dụ: add-product.jsp, list-product.jsp).
Web Container (Tomcat): Quản lý vòng đời của Servlet, xử lý đa luồng cho các yêu cầu.

3. Chức năng Bảo mật và Lọc (Filter)
Chức năng bảo mật được triển khai bằng Servlet Filter để bảo vệ khu vực quản trị:
Bộ lọc Admin (AdminFilter.java):
Được ánh xạ tới tất cả các URL bắt đầu bằng /admin/*.
Nhiệm vụ: Kiểm tra xem người dùng hiện tại trong Session có tồn tại (user != null) và có quyền Admin (user.getRoleid() == 1) hay không.
Hành động: Nếu không có quyền, người dùng sẽ bị chuyển hướng (sendRedirect) về trang đăng nhập (/login), đảm bảo rằng chỉ có Quản trị viên mới có thể truy cập các trang quản lý.

4. Các kỹ thuật Servlet nâng cao
Chương trình sử dụng các kỹ thuật Java Servlet cốt lõi:
Page Dispatching: Sử dụng RequestDispatcher.forward() để chuyển tiếp yêu cầu nội bộ đến các file JSP.
Page Redirect: Sử dụng response.sendRedirect() để chuyển hướng trình duyệt đến một URL khác (thường là sau khi xử lý POST form).
Session/Cookie: Dùng để lưu trữ thông tin trạng thái người dùng (ví dụ: UserModel trong Session).
