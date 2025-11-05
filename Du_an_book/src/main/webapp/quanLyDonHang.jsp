<%@ page import="java.util.*, model.*, dao.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý đơn hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="admin.jsp">📦 Quản lý đơn hàng</a>
    </div>
</nav>

<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>📋 Danh sách đơn hàng</h3>
        <a href="themDonHang.jsp" class="btn btn-success">➕ Thêm đơn hàng</a>
    </div>

    <%
        DonHangDAO dao = new DonHangDAO();
        ArrayList<DonHang> ds = dao.selectAll();

        if (ds != null && !ds.isEmpty()) {
    %>
    <table class="table table-bordered text-center align-middle">
        <thead class="table-dark">
            <tr>
                <th>Mã đơn hàng</th>
                <th>Mã khách hàng</th>
                <th>Ngày lập</th>
                <th>Tổng tiền</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <% for (DonHang dh : ds) { %>
            <tr>
                <td><%= dh.getMaDH() %></td>
                <td><%= dh.getMaKH() %></td>
                <td><%= dh.getNgayLap() %></td>
                <td><%= String.format("%,.0f", dh.getTongTien()) %> VNĐ</td>
                <td>
                    <a href="suaDonHang.jsp?maDH=<%=dh.getMaDH()%>" class="btn btn-warning btn-sm">✏️ Sửa</a>
                    <a href="xoaDonHang?maDH=<%=dh.getMaDH()%>" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc muốn xóa đơn hàng này không?');">🗑️ Xóa</a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <% } else { %>
        <p class="text-center text-muted">Không có đơn hàng nào.</p>
    <% } %>
</div>
</body>
</html>
