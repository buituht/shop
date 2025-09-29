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
    private static final String URL = "jdbc:mysql://localhost:3306/test";
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

/*
package vn.iotstar.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DBConnection {
private static String DB_URL = "jdbc:mysql://localhost:3306/test";
private static String USER_NAME = "root";
private static String PASSWORD = "";


public static void main(String args[]) {
try {
// connnect to database 'testdb'
Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("select * from student");
while (rs.next()) {
System.out.println(rs.getInt(1) + " " + rs.getString(2)
+ " " + rs.getString(3));
}
conn.close();
} catch (Exception ex) {
ex.printStackTrace();
}
}


public static Connection getConnection(String dbURL, String userName,
String password) {
Connection conn = null;
try {
Class.forName("com.mysql.jdbc.Driver");
conn = DriverManager.getConnection(dbURL, userName, password);
System.out.println("connect successfully!");
} catch (Exception ex) {
System.out.println("connect failure!");
ex.printStackTrace();
}
return conn;
}




}
*/