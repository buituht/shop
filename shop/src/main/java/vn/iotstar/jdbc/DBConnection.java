package vn.iotstar.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // instance duy nhất của Connection
    private static Connection connection = null;

    // Tên driver JDBC cho MySQL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    // URL kết nối đến database
    private static final String URL = "jdbc:mysql://localhost:3307/shop";
    // Tên người dùng và mật khẩu
    private static final String USER = "root";
    private static final String PASS = "";

    // Phương thức private để ngăn việc tạo instance từ bên ngoài
    private DBConnection() {
        // Có thể thêm code khởi tạo nếu cần
    }

    // Phương thức công khai để lấy instance Connection duy nhất
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Tải driver JDBC
                Class.forName(DRIVER);
                // Tạo kết nối nếu chưa tồn tại
                connection = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Kết nối đến MySQL thành công!");
            } catch (ClassNotFoundException e) {
                System.err.println("Không tìm thấy Driver JDBC: " + DRIVER);
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Kết nối đến MySQL thất bại!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Phương thức để đóng kết nối
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Đã đóng kết nối MySQL.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
