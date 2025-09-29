package vn.iotstar.utils;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {

    // THÔNG TIN EMAIL CẤU HÌNH (Sử dụng Gmail SMTP)
    private static final String SENDER_EMAIL = "pummenvietnam@gmail.com"; // Thay bằng email của bạn
    private static final String SENDER_PASSWORD = "hbwo kfov qcnp tzsj";  // Thay bằng MẬT KHẨU ỨNG DỤNG CỦA GMAIL

    /**
     * Gửi email xác nhận đơn hàng
     * @param recipientEmail Địa chỉ người nhận
     * @param subject Chủ đề email
     * @param content Nội dung email (có thể là HTML)
     * @return true nếu gửi thành công, false nếu thất bại
     */
    public static boolean sendEmail(String recipientEmail, String subject, String content) {
        
        // 1. Cấu hình các thuộc tính của SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");  // Host của Gmail
        props.put("mail.smtp.port", "587");             // Port của TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Bắt buộc cho Gmail

        // 2. Tạo đối tượng Session và Authenticator
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);

        try {
            // 3. Tạo đối tượng Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL, "Tên Cửa Hàng Của Bạn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            
            // 4. Thiết lập nội dung (HTML)
            message.setContent(content, "text/html; charset=UTF-8");

            // 5. Gửi email
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            System.err.println("Lỗi khi gửi email đến " + recipientEmail + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}