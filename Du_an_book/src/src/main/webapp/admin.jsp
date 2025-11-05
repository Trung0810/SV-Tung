<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Quản Trị Admin</title>
<style>
body { font-family: Arial; margin: 0; background: #f4f4f4; }
.sidebar {
    width: 220px; background: #333; color: #fff; height: 100vh; float: left; padding-top: 20px;
}
.sidebar a {
    display: block; color: white; padding: 12px; text-decoration: none;
}
.sidebar a:hover { background: #575757; }
.main { margin-left: 220px; padding: 20px; }
iframe { width: 100%; height: 90vh; border: none; background: white; border-radius: 8px; }
</style>
</head>
<body>
    <div class="sidebar">
        <h3 style="text-align:center;">Admin</h3>
        <a href="quanLySanPham.jsp" target="contentFrame">Quản lý sản phẩm</a>
        <a href="quanLyDonHang.jsp" target="contentFrame">Quản lý đơn hàng</a>
        <a href="doanhThu.jsp" target="contentFrame">Thống kê doanh thu</a>
        <a href="dangNhap.jsp" style="color:red;">Đăng xuất</a>
    </div>

    <div class="main">
        <iframe name="contentFrame" src="quanLySanPham.jsp"></iframe>
    </div>
</body>
</html>
