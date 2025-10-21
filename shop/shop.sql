-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Oct 21, 2025 at 05:35 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

CREATE TABLE `articles` (
  `article_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `summary` varchar(500) DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `views` int(11) DEFAULT 0,
  `status` enum('Draft','Published','Hidden') DEFAULT 'Draft',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `articles`
--

INSERT INTO `articles` (`article_id`, `category_id`, `user_id`, `title`, `slug`, `summary`, `content`, `image`, `views`, `status`, `created_at`, `updated_at`) VALUES
(1, 1, 1, 'Top 5 điện thoại thông minh đáng mua nhất năm', 'top-5-dien-thoai-thong-minh', 'Tổng hợp 5 mẫu điện thoại có hiệu năng và giá cả tốt nhất hiện nay.', 'Nội dung chi tiết về các mẫu điện thoại...', '/uploads/images/article/phone_review.jpg', 150, 'Published', '2025-10-06 04:03:24', '2025-10-06 04:03:24'),
(2, 2, 1, 'Ưu đãi Lễ Quốc khánh: Giảm giá 20% toàn bộ phụ kiện', 'uu-dai-le-quoc-khanh', 'Chương trình khuyến mãi lớn nhất trong tháng 9.', 'Chi tiết các sản phẩm áp dụng giảm giá...', '/uploads/images/article/sale_banner.jpg', 95, 'Published', '2025-10-06 04:03:24', '2025-10-06 04:03:24'),
(3, 3, 1, 'Cách tối ưu hóa pin laptop hiệu quả', 'toi-uu-hoa-pin-laptop', 'Các bước đơn giản giúp kéo dài tuổi thọ và thời lượng pin cho máy tính xách tay.', 'Hướng dẫn từng bước chi tiết...', '/uploads/images/article/battery_tip.jpg', 10, 'Draft', '2025-10-06 04:03:24', '2025-10-06 04:03:24');

-- --------------------------------------------------------

--
-- Table structure for table `article_categories`
--

CREATE TABLE `article_categories` (
  `category_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `article_categories`
--

INSERT INTO `article_categories` (`category_id`, `name`, `slug`, `description`, `created_at`) VALUES
(1, 'Tin tức công nghệ', 'tin-tuc-cong-nghe', 'Các tin tức mới nhất về công nghệ và sản phẩm', '2025-10-06 04:03:04'),
(2, 'Khuyến mãi', 'khuyen-mai', 'Thông báo các chương trình giảm giá và ưu đãi đặc biệt', '2025-10-06 04:03:04'),
(3, 'Hướng dẫn sử dụng', 'huong-dan-su-dung', 'Các bài viết chỉ dẫn, tips và tricks', '2025-10-06 04:03:04');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cart_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cart_items`
--

CREATE TABLE `cart_items` (
  `item_id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 1,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `catId` int(11) NOT NULL,
  `CateName` varchar(255) NOT NULL,
  `Image` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`catId`, `CateName`, `Image`, `status`) VALUES
(3, 'Laptop', '1759150043229_6-quy-tac-can-nam-ro-khi-setup-background-chup-anh-san-pham-2024-1.jpeg', 1),
(4, 'Máy bàn', '1759150068074_Chup-anh-san-pham-la-gi.jpeg', 0),
(6, 'category 2', '1759150058047_Product-Kis_00260.jpg', 1),
(8, 'Điện thoại', '1759150074152_6-quy-tac-can-nam-ro-khi-setup-background-chup-anh-san-pham-2024-1.jpeg', 0),
(15, 'ten category 2', '1759150051934_Product-Kis_00170-1.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `full_name` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `shipping_address` varchar(500) NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `payment_method` varchar(50) DEFAULT NULL,
  `order_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `status` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `full_name`, `phone`, `email`, `shipping_address`, `total_amount`, `payment_method`, `order_date`, `status`) VALUES
(1, NULL, 'Tú Bùi', '0917607634', '', 'giao cho toi ngay bay gio', 61765000.00, 'COD', '2025-09-28 11:41:13', 1),
(2, 20, 'Bùi Thanh Tú', '0917607635', '', 'o dau cung duoc', 1030000.00, 'COD', '2025-09-28 11:47:02', 1),
(3, NULL, 'Bùi Thanh Tú', '0917607635', '', '940a tổ 15 ấp bình thung 1', 1780000.00, 'COD', '2025-09-28 12:04:58', 1),
(4, 57, 'Bùi Thanh Tú', '0917607635', '', '940a tổ 15 ấp bình thung 1', 470000.00, 'COD', '2025-09-28 12:06:33', 1),
(5, 51, 'Bùi Thanh Tú', '0917607635', 'buituht@gmail.com', '940a tổ 15 ấp bình thung 1', 750000.00, 'COD', '2025-09-28 12:37:16', 1),
(6, NULL, 'Bùi Thanh Tú', '0917607635', 'buituht@gmail.com', '940a tổ 15 ấp bình thung 1', 750000.00, 'COD', '2025-09-28 12:46:29', 1),
(7, NULL, 'Bùi Thanh Tú', '0917607635', 'buituht@gmail.com', '940a tổ 15 ấp bình thung 1', 730000.00, 'COD', '2025-09-28 12:47:42', 1),
(8, NULL, 'Bùi Thanh Tú', '0917607635', 'buituht@gmail.com', '940a tổ 15 ấp bình thung 1', 1170000.00, 'COD', '2025-09-28 12:54:19', 1),
(9, NULL, 'Bùi Thanh Tú', '0917607635', 'buituht@gmail.com', '940a tổ 15 ấp bình thung 1', 280000.00, 'COD', '2025-09-28 13:20:45', 1),
(10, NULL, 'Bùi Thanh Tú', '0917607635', 'buituht@gmail.com', '940a tổ 15 ấp bình thung 1', 280000.00, 'COD', '2025-09-28 13:31:20', 1),
(11, NULL, 'Bùi Thanh Tú', '0917607635', 'buituht@gmail.com', 'dia chi giao hang', 2960000.00, 'COD', '2025-09-28 13:33:49', 1);

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `detail_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`detail_id`, `order_id`, `product_id`, `product_name`, `quantity`, `price`) VALUES
(1, 1, 3, 'Váy Hoa Nhí', 1, 280000.00),
(2, 1, 6, 'Đồng Hồ Nữ Dây Kim Loại', 1, 1200000.00),
(3, 1, 9, 'Mũ Lưỡi Trai Unisex', 3, 95000.00),
(4, 1, 16, 'Giám sát chấp hành luật giao thông', 6, 10000000.00),
(5, 2, 4, 'Túi Xách Da Đeo Chéo', 1, 750000.00),
(6, 2, 3, 'Váy Hoa Nhí', 1, 280000.00),
(7, 3, 3, 'Váy Hoa Nhí', 1, 280000.00),
(8, 3, 4, 'Túi Xách Da Đeo Chéo', 2, 750000.00),
(9, 4, 3, 'Váy Hoa Nhí', 1, 280000.00),
(10, 4, 8, 'Chân Váy Chữ A', 1, 190000.00),
(11, 5, 4, 'Túi Xách Da Đeo Chéo', 1, 750000.00),
(12, 6, 4, 'Túi Xách Da Đeo Chéo', 1, 750000.00),
(13, 7, 3, 'Váy Hoa Nhí', 1, 280000.00),
(14, 7, 2, 'Quần Jeans Slim Fit', 1, 450000.00),
(15, 8, 3, 'Váy Hoa Nhí', 1, 280000.00),
(16, 8, 5, 'Giày Thể Thao Sneaker', 1, 890000.00),
(17, 9, 3, 'Váy Hoa Nhí', 1, 280000.00),
(18, 10, 3, 'Váy Hoa Nhí', 1, 280000.00),
(19, 11, 3, 'Váy Hoa Nhí', 1, 280000.00),
(20, 11, 2, 'Quần Jeans Slim Fit', 2, 450000.00),
(21, 11, 5, 'Giày Thể Thao Sneaker', 2, 890000.00);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `cate_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` mediumtext DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `cate_id`, `name`, `price`, `image`, `description`, `status`, `created_at`, `updated_at`) VALUES
(2, 3, 'Quần Jeans Slim Fit', 450000.00, '1758895403343.png', 'Quần jeans co giãn tốt, thiết kế ôm vừa vặn, tôn dáng. Màu xanh đậm cá tính.', 1, '2025-09-24 00:54:36', '2025-09-26 14:03:23'),
(3, 4, 'Váy Hoa Nhí', 280000.00, '1758895412981.png', 'Váy hoa nhí phong cách vintage, chất liệu voan nhẹ nhàng, bay bổng. Thích hợp đi chơi, dạo phố.', 1, '2025-09-24 00:54:36', '2025-09-26 14:03:32'),
(4, 4, 'Túi Xách Da Đeo Chéo', 750000.00, '1758895425583.png', 'Túi xách nhỏ gọn, làm từ da tổng hợp cao cấp, có nhiều ngăn tiện dụng. Dây đeo điều chỉnh được.', 1, '2025-09-24 00:54:36', '2025-09-26 14:03:45'),
(5, 3, 'Giày Thể Thao Sneaker', 890000.00, '1758895541668.png', 'Giày sneaker thiết kế năng động, đế cao su chống trượt. Phù hợp cho các hoạt động thể thao nhẹ.', 1, '2025-09-24 00:54:36', '2025-09-26 14:05:41'),
(6, 4, 'Đồng Hồ Nữ Dây Kim Loại', 1200000.00, '1758896309764.jfif', 'Đồng hồ mặt tròn nhỏ nhắn, dây kim loại sang trọng. Chống nước cơ bản.', 1, '2025-09-24 00:54:36', '2025-09-26 14:18:29'),
(7, 3, 'Áo Khoác Bomber', 550000.00, '1758896278712.png', 'Áo khoác bomber vải dù chống thấm nhẹ, giữ ấm tốt. Phong cách trẻ trung, năng động.', 1, '2025-09-24 00:54:36', '2025-09-26 14:17:58'),
(8, 4, 'Chân Váy Chữ A', 190000.00, '1758896286240.png', 'Chân váy dáng chữ A, chất liệu kaki dày dặn, có nhiều màu sắc để lựa chọn.', 1, '2025-09-24 00:54:36', '2025-09-26 14:18:06'),
(9, 3, 'Mũ Lưỡi Trai Unisex', 95000.00, '1758896319653.jfif', 'Mũ lưỡi trai thiết kế đơn giản, thêu logo nổi bật. Vải thoáng khí.', 1, '2025-09-24 00:54:36', '2025-09-26 14:18:39'),
(10, 8, 'Kính Mát Thời Trang', 320000.00, '1758896326235.jfif', 'Kính mát gọng nhựa cao cấp, tròng kính chống tia UV. Bảo vệ mắt hiệu quả.', 1, '2025-09-24 00:54:36', '2025-09-26 14:18:46'),
(16, 4, 'Giám sát chấp hành luật giao thông', 10000000.00, '1758895342946.png', 'mô tả giảm thiểu vi phạm tai nạn giao thông', 1, '2025-09-26 14:02:22', '2025-09-26 14:02:22');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `ID` int(11) NOT NULL,
  `Code` text NOT NULL,
  `Name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `fullName` varchar(100) DEFAULT NULL,
  `passWord` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `createdDate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `userName`, `fullName`, `passWord`, `avatar`, `roleId`, `phone`, `createdDate`) VALUES
(1, 'john.doe@example.com', 'john_doe', 'John Doe', 'hashed_password_1', 'avatar1.jpg', 1, '123-456-7890', '2025-09-16 01:00:00'),
(2, 'jane.smith@example.com', 'jane_smith', 'Jane Smith', 'hashed_password_2', 'avatar2.jpg', 1, '123-456-7891', '2025-09-16 01:05:00'),
(3, 'peter.jones@example.com', 'peter_j', 'Peter Jones', 'hashed_password_3', 'avatar3.jpg', 2, '123-456-7892', '2025-09-16 01:10:00'),
(4, 'mary.brown@example.com', 'mary_b', 'Mary Brown', 'hashed_password_4', 'avatar4.jpg', 1, '123-456-7893', '2025-09-16 01:15:00'),
(5, 'robert.davis@example.com', 'robert_d', 'Robert Davis', 'hashed_password_5', 'avatar5.jpg', 2, '123-456-7894', '2025-09-16 01:20:00'),
(6, 'linda.wilson@example.com', 'linda_w', 'Linda Wilson', 'hashed_password_6', 'avatar6.jpg', 1, '123-456-7895', '2025-09-16 01:25:00'),
(7, 'michael.moore@example.com', 'mike_m', 'Michael Moore', 'hashed_password_7', 'avatar7.jpg', 1, '123-456-7896', '2025-09-16 01:30:00'),
(8, 'patricia.taylor@example.com', 'pat_t', 'Patricia Taylor', 'hashed_password_8', 'avatar8.jpg', 2, '123-456-7897', '2025-09-16 01:35:00'),
(9, 'david.anderson@example.com', 'dave_a', 'David Anderson', 'hashed_password_9', 'avatar9.jpg', 1, '123-456-7898', '2025-09-16 01:40:00'),
(10, 'jennifer.thomas@example.com', 'jen_t', 'Jennifer Thomas', 'hashed_password_10', 'avatar10.jpg', 1, '123-456-7899', '2025-09-16 01:45:00'),
(11, 'james.jackson@example.com', 'jim_j', 'James Jackson', 'hashed_password_11', 'avatar11.jpg', 2, '123-456-7900', '2025-09-16 01:50:00'),
(12, 'susan.white@example.com', 'susan_w', 'Susan White', 'hashed_password_12', 'avatar12.jpg', 1, '123-456-7901', '2025-09-16 01:55:00'),
(13, 'charles.harris@example.com', 'charlie_h', 'Charles Harris', 'hashed_password_13', 'avatar13.jpg', 1, '123-456-7902', '2025-09-16 02:00:00'),
(14, 'elizabeth.martin@example.com', 'liz_m', 'Elizabeth Martin', 'hashed_password_14', 'avatar14.jpg', 2, '123-456-7903', '2025-09-16 02:05:00'),
(15, 'thomas.thompson@example.com', 'tom_t', 'Thomas Thompson', 'hashed_password_15', 'avatar15.jpg', 1, '123-456-7904', '2025-09-16 02:10:00'),
(16, 'nancy.garcia@example.com', 'nancy_g', 'Nancy Garcia', 'hashed_password_16', 'avatar16.jpg', 1, '123-456-7905', '2025-09-16 02:15:00'),
(17, 'daniel.rodriguez@example.com', 'dan_r', 'Daniel Rodriguez', 'hashed_password_17', 'avatar17.jpg', 2, '123-456-7906', '2025-09-16 02:20:00'),
(18, 'sarah.martinez@example.com', 'sarah_m', 'Sarah Martinez', 'hashed_password_18', 'avatar18.jpg', 1, '123-456-7907', '2025-09-16 02:25:00'),
(19, 'christopher.lee@example.com', 'chris_l', 'Christopher Lee', 'hashed_password_19', 'avatar19.jpg', 1, '123-456-7908', '2025-09-16 02:30:00'),
(20, 'betty.hall@example.com', 'betty_h', 'Betty Hall', 'hashed_password_20', 'avatar20.jpg', 2, '123-456-7909', '2025-09-16 02:35:00'),
(24, 'john.do1e@example.com', 'tubui', 'Tu Bui', '123456', 'avatar1.jpg', 1, '123-456-7890', '2025-09-16 01:00:00'),
(25, 'Email@gmail.com', 'buituht1', 'Fullname', 'Fullname', 'Avatar', 0, '0917607635', '2025-09-18 15:43:08'),
(30, 'BUITUHT3@GMAIL.COM', 'buituht3', 'Fullname', '1234567', 'Avatar', 0, 'Phone', '2025-09-18 15:50:29'),
(31, 'BUITU4@GMAIL.COM', 'tubui4', 'Bui Thanh Tu', '1', 'https://i1-vnexpress.vnecdn.net/2025/09/18/202505050753501308z818433-1746-9327-6241-1758194435.jpg?w=1020&h=0&q=100&dpr=1&fit=crop&s=VImNFPgeWOAPgGnGr4HrqQ', NULL, '0917607635', '2025-09-19 01:16:34'),
(32, 'BUITU5@GMAIL.COM', 'tubui5', 'Bui Thanh Tu', '1', 'https://i1-vnexpress.vnecdn.net/2025/09/18/202505050753501308z818433-1746-9327-6241-1758194435.jpg?w=1020&h=0&q=100&dpr=1&fit=crop&s=VImNFPgeWOAPgGnGr4HrqQ', 1, '0917607635', '2025-09-19 01:29:05'),
(33, 'BUITU6@GMAIL.COM', 'tubui6', 'Bui Thanh Tu', '1', 'https://i1-vnexpress.vnecdn.net/2025/09/18/202505050753501308z818433-1746-9327-6241-1758194435.jpg?w=1020&h=0&q=100&dpr=1&fit=crop&s=VImNFPgeWOAPgGnGr4HrqQ', 1, '0917607635', '2025-09-19 01:38:27'),
(35, 'BUITU7@GMAIL.COM', 'tubui7', 'Bui Thanh Tu', '1', 'https://i1-vnexpress.vnecdn.net/2025/09/18/202505050753501308z818433-1746-9327-6241-1758194435.jpg?w=1020&h=0&q=100&dpr=1&fit=crop&s=VImNFPgeWOAPgGnGr4HrqQ', 1, '0917607635', '2025-09-19 01:41:41'),
(36, 'BUITU8@GMAIL.COM', 'tubui8', 'Bui Thanh Tu', '1', 'https://i1-vnexpress.vnecdn.net/2025/09/18/202505050753501308z818433-1746-9327-6241-1758194435.jpg?w=1020&h=0&q=100&dpr=1&fit=crop&s=VImNFPgeWOAPgGnGr4HrqQ', 2, '0917607635', '2025-09-19 01:42:38'),
(37, 'BUITU9@GMAIL.COM', 'tubui9', 'Bui Thanh Tu', '1', '0e524f88619ed4c08d8f.jpg', 3, '0917607635', '2025-09-19 01:44:40'),
(39, 'BUITU10@GMAIL.COM', 'BUITU10', 'Bui Thanh Tu', '1', '0e524f88619ed4c08d8f.jpg', 3, '0917607635', '2025-09-19 02:19:15'),
(41, 'BUITU11@GMAIL.COM', 'tubui11', 'Bui Thanh Tu', '1', '0e524f88619ed4c08d8f.jpg', 2, '0917607635', '2025-09-19 02:28:24'),
(43, 'BUITU12@GMAIL.COM', 'tubui12', 'Bui Thanh Tu', '1', '0e524f88619ed4c08d8f.jpg', 1, '0917607635', '2025-09-19 02:40:07'),
(44, 'BUITUHT@GMAIL.COM', 'buituht', 'Bui Thanh Tu', '1', '', 2, '', '2025-09-19 02:45:57'),
(48, 'BUITUHT13@GMAIL.COM', 'tubui13', 'Bui Thanh Tu', '1', '0e524f88619ed4c08d8f.jpg', 1, '0917607635', '2025-09-19 02:55:41'),
(51, 'BUITUHT14@GMAIL.COM', 'tubui14', 'Bui Thanh Tu', '1', '', 1, '0917607635', '2025-09-19 03:12:01'),
(52, 'BUITUHT15@GMAIL.COM', 'tubui15', 'Bui Thanh Tu', '1', '', 3, '0917607635', '2025-09-19 04:48:26'),
(56, 'tubui@gmail.com', 'buitu1', 'bui thanh tu', '123', '', 2, '0917607635', '2025-09-29 12:50:34'),
(57, 'buituht2@gmail.com', 'buitu2', 'bui thanh tu', '123', '', 2, '0917607635', '2025-09-29 12:53:00'),
(59, 'tubui4@gmail.com', 'buitu4', 'bui thanh tu', '123', '', 1, '0917607635', '2025-09-29 12:54:40'),
(60, 'admin@gmail.com', 'admin1', 'Tran Van Admin', '123', '', 1, '0917607635', '2025-09-29 13:02:30'),
(61, 'manager1@gmail.com', 'manager1', 'Nguyen Van Manager', '123', '', 2, '0917607635', '2025-09-29 13:03:39'),
(62, 'admin2@gmail.com', 'admin2', 'Nguyen Van Admin 2', '123', '', 1, '0917607635', '2025-09-29 13:14:46'),
(63, 'admin3@gmail.com', 'admin3', 'Nguyen Van Admin 3', '123', '', 1, '0917607635', '2025-09-29 14:05:40');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`article_id`),
  ADD UNIQUE KEY `slug` (`slug`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `article_categories`
--
ALTER TABLE `article_categories`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `slug` (`slug`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD PRIMARY KEY (`item_id`),
  ADD KEY `cart_id` (`cart_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`catId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`detail_id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cate_id` (`cate_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `userName` (`userName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articles`
--
ALTER TABLE `articles`
  MODIFY `article_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `article_categories`
--
ALTER TABLE `article_categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cart_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cart_items`
--
ALTER TABLE `cart_items`
  MODIFY `item_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `catId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `articles`
--
ALTER TABLE `articles`
  ADD CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `article_categories` (`category_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `articles_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL;

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `cart_items`
--
ALTER TABLE `cart_items`
  ADD CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
  ADD CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`cate_id`) REFERENCES `category` (`catId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
