<%@ page import="dao.DonHangDAO" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Thống kê doanh thu</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-4">
  <div class="container">
    <h3 class="mb-4 text-primary">📊 Thống kê doanh thu</h3>

    <%
        DonHangDAO dao = new DonHangDAO();
        double tongDoanhThu = dao.tinhTongDoanhThu();

        DecimalFormat df = new DecimalFormat("#,###.##");
    %>

    <div class="card p-4 shadow-sm">
      <h4>Tổng doanh thu hiện tại:</h4>
      <h2 class="text-success"><%= df.format(tongDoanhThu) %> ₫</h2>
    </div>

    <hr>

    <h5 class="mt-4">📅 Chi tiết từng đơn hàng</h5>

    <table class="table table-bordered table-sm mt-2">
      <thead class="table-light">
        <tr>
          <th>Mã đơn</th>
          <th>Mã khách hàng</th>
          <th>Ngày lập</th>
          <th>Tổng tiền</th>
        </tr>
      </thead>
      <tbody>
        <%
          java.util.ArrayList<model.DonHang> ds = dao.selectAll();
          for (model.DonHang dh : ds) {
        %>
          <tr>
            <td><%= dh.getMaDH() %></td>
            <td><%= dh.getMaKH() %></td>
            <td><%= dh.getNgayLap() %></td>
            <td><%= df.format(dh.getTongTien()) %> ₫</td>
          </tr>
        <%
          }
        %>
      </tbody>
    </table>

    <a href="quanLyDonHang.jsp" class="btn btn-secondary mt-3">⬅ Quay lại quản lý đơn hàng</a>
  </div>
</body>
</html>
