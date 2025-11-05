CREATE DATABASE bookstore1 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bookstore1;

-- Bảng thể loại
CREATE TABLE TheLoai (
    maTL INT AUTO_INCREMENT PRIMARY KEY,
    tenTL VARCHAR(100) NOT NULL
);

-- Bảng tác giả
CREATE TABLE TacGia (
    maTG INT AUTO_INCREMENT PRIMARY KEY,
    tenTG VARCHAR(100) NOT NULL,
    ngaySinh DATE,
    tieuSu VARCHAR(500)
);

-- Bảng sản phẩm (sách)
CREATE TABLE SanPham (
    maSP INT AUTO_INCREMENT PRIMARY KEY,
    tenSP VARCHAR(255) NOT NULL,
    gia DECIMAL(10,2) NOT NULL,
    anh VARCHAR(255),
    moTa TEXT,
    maTL INT,
    maTG INT,
    FOREIGN KEY (maTL) REFERENCES TheLoai(maTL),
    FOREIGN KEY (maTG) REFERENCES TacGia(maTG)
);

-- Bảng khách hàng
CREATE TABLE KhachHang (
    maKH INT AUTO_INCREMENT PRIMARY KEY,
    tenDangNhap VARCHAR(50) NOT NULL,
    matKhau  VARCHAR(50) NOT NULL,
    tenKH VARCHAR(100),
    gioiTinh VARCHAR(20),
    diaChi VARCHAR(255),
    ngaySinh DATE,
    sdt VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    vaiTro VARCHAR(25) DEFAULT 'user'
    
);

-- Bảng đơn hàng
CREATE TABLE DonHang (
    maDH INT AUTO_INCREMENT PRIMARY KEY,
    maKH INT,
    ngayLap DATE,
    tongTien DECIMAL(10,2),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);

-- Bảng chi tiết đơn hàng
CREATE TABLE ChiTietDonHang (
    maCT INT AUTO_INCREMENT PRIMARY KEY,
    maDH INT,
    maSP INT,
    soLuong INT,
    donGia DECIMAL(10,2),
    FOREIGN KEY (maDH) REFERENCES DonHang(maDH),
    FOREIGN KEY (maSP) REFERENCES SanPham(maSP)
);

-- Dữ liệu mẫu
INSERT INTO TheLoai (tenTL) VALUES 
('Khoa học - Viễn tưởng'), 
('Tâm lý - Kỹ năng sống'),
('Kiến thức - Học thuật');

-- ✅ Cần thêm dấu nháy đơn quanh chuỗi
INSERT INTO TacGia (tenTG, tieuSu) VALUES 
('Dale Carnegie', 'Dale Carnegie là nhà văn, diễn giả người Mỹ, tác giả cuốn “Đắc Nhân Tâm”. Ông được xem là người tiên phong trong lĩnh vực giao tiếp, nghệ thuật ứng xử và phát triển bản thân, truyền cảm hứng cho hàng triệu người trên thế giới.'),
('Daniel Kahneman', 'Daniel Kahneman là nhà tâm lý học đoạt giải Nobel Kinh tế năm 2002. Ông nổi tiếng với các nghiên cứu về tư duy, ra quyết định và “phi lý trí” trong hành vi con người, là tác giả của cuốn “Thinking, Fast and Slow”.'),
('Haruki Murakami', 'Haruki Murakami là tiểu thuyết gia Nhật Bản nổi tiếng với phong cách siêu thực và triết lý. Các tác phẩm như “Rừng Na Uy” hay “Kafka bên bờ biển” đã giúp ông trở thành một trong những cây bút đương đại được yêu thích nhất thế giới.'),
('Marie Kondo', 'Marie Kondo là chuyên gia người Nhật nổi tiếng toàn cầu về nghệ thuật sắp xếp và lối sống tối giản. Bà là tác giả của cuốn “The Life-Changing Magic of Tidying Up”, khuyến khích con người chỉ giữ lại những gì mang lại niềm vui thật sự.');

-- Dữ liệu sản phẩm
INSERT INTO SanPham (tenSP, gia, anh, moTa, maTL, maTG) VALUES
('Phi lí trí', 120000, 'img/product/anh_sach_1.png', 'Cuốn sách khám phá những hành vi tưởng chừng vô lý nhưng lại rất con người của chúng ta trong các quyết định thường ngày. Tác phẩm giúp bạn hiểu rõ hơn về tâm lý, từ đó đưa ra lựa chọn thông minh hơn trong công việc và cuộc sống.', 2, 1),
('Lối sống tối giản của người nhật', 95000, 'img/product/anh_sach_2.png', 'Cuốn sách chia sẻ triết lý sống tối giản của người Nhật, giúp bạn dọn bỏ những thứ không cần thiết, sắp xếp cuộc sống gọn gàng hơn và tìm lại sự cân bằng, hạnh phúc giữa nhịp sống bận rộn.', 2, 2),
('Vũ trụ', 150000, 'img/product/anh_sach_3.png', 'Cuốn sách của Carl Sagan dẫn dắt người đọc khám phá vũ trụ từ hạt bụi nhỏ bé đến những thiên hà kỳ vĩ, khơi dậy trí tưởng tượng, niềm đam mê khoa học và suy ngẫm về vị trí của con người trong vũ trụ.', 1, 3),
('Siêu kinh tế học hài hước', 80000, 'img/product/anh_sach_4.png', 'Cuốn sách của Steven D. Levitt mang đến góc nhìn hài hước và sắc sảo về kinh tế học, phân tích những hiện tượng đời thường như tội phạm hay hôn nhân bằng cách tiếp cận độc đáo, vừa thú vị vừa giàu tri thức.', 3, 4),
('Sự thật về Donald Trump', 130000.0, 'img/product/anh_sach_5.png', 'Tác phẩm của David Cay Johnston hé lộ sự thật về đế chế kinh doanh và cuộc đời đầy tranh cãi của Donald Trump, phơi bày bí mật tài chính, mánh khóe kinh doanh và những tuyên bố sai lệch.', 3, 2),
('Bí quyết thuyết trình của Steve Job', 140000.00, 'img/product/anh_sach_6.png', 'Cuốn sách của Carmine Gallo tiết lộ bí quyết thuyết trình đỉnh cao của Steve Jobs, hướng dẫn bạn tạo nên bài thuyết trình ấn tượng qua cách kể chuyện hấp dẫn, thiết kế slide tinh gọn và sử dụng ngôn ngữ cơ thể hiệu quả.', 3, 4),
('Thế giới song song', 150000.00, 'img/product/anh_sach_7.png', 'Cuốn sách của Michio Kaku mở ra hành trình khám phá vũ trụ đa chiều và những khả năng vô tận như thực tại song song, du hành thời gian hay bí ẩn lượng tử, thách thức giới hạn hiểu biết của con người.', 1, 3),
('Bá tước Monte Cristo', 110000.00, 'img/product/anh_sach_8.png', 'Câu chuyện về Edmond Dantès, chàng thủy thủ bị phản bội, trở lại với thân phận Bá tước Monte Cristo để trả thù và giành lại công lý.', 1, 2),
('Võ lâm ngũ bá', 100000.00, 'img/product/anh_sach_9.png', 'Cuốn tiểu thuyết kiếm hiệp kinh điển của Kim Dung kể về cuộc tranh hùng giữa năm cao thủ võ lâm, mỗi người sở hữu tuyệt kỹ riêng, cùng hướng tới ngôi vị bá chủ giang hồ.', 1, 1),
('Đời ngắn lắm đừng ngủ dài', 85000.00, 'img/product/anh_sach_10.png', 'Cuốn sách của Robin Sharma giúp bạn khai phá tiềm năng, làm chủ bản thân và sống trọn vẹn từng khoảnh khắc để đạt được thành công và hạnh phúc bền vững.', 3, 4),
('Tư duy phản biện', 95000.00, 'img/product/anh_sach_11.png', 'Cuốn sách giúp bạn rèn luyện tư duy phản biện, từ phân tích thông tin đến nhận diện ngụy biện, để đưa ra quyết định sáng suốt và giải quyết vấn đề hiệu quả.', 3, 3),
('Người về từ sao hỏa', 115000.00, 'img/product/anh_sach_12.png', 'TBị mắc kẹt một mình trên Sao Hỏa, Mark Watney phải tận dụng mọi kiến thức khoa học để sinh tồn giữa hiểm nguy và cô độc, chiến đấu từng ngày với hy vọng mong manh được trở về Trái Đất.', 1, 4);
