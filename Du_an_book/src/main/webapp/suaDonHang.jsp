<%@ page import="java.sql.*" %>
<%@ page import="dao.JDBCUtil" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Sửa đơn hàng</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script>
    function addRow() {
      const tbody = document.getElementById('itemsBody');
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td><input name="maSP" class="form-control form-control-sm" required /></td>
        <td><input name="soLuong" type="number" min="1" value="1" class="form-control form-control-sm" required /></td>
        <td><input name="donGia" type="number" min="0" step="0.01" value="0" class="form-control form-control-sm" required /></td>
      `;
      tbody.appendChild(tr);
    }
    function removeRow() {
      const tbody = document.getElementById('itemsBody');
      if (tbody.rows.length > 1) tbody.deleteRow(-1);
    }
  </script>
</head>
<body class="p-4">
<%
    int maDH = Integer.parseInt(request.getParameter("maDH"));
    Connection conn = JDBCUtil.getConnection();

    PreparedStatement ps = conn.prepareStatement("SELECT * FROM DonHang WHERE maDH = ?");
    ps.setInt(1, maDH);
    ResultSet rs = ps.executeQuery();

    int maKH = 0;
    java.sql.Date ngayLap = null;

    if (rs.next()) {
        maKH = rs.getInt("maKH");
        ngayLap = rs.getDate("ngayLap");
    }
    rs.close();
    ps.close();

    PreparedStatement psCT = conn.prepareStatement("SELECT * FROM ChiTietDonHang WHERE maDH = ?");
    psCT.setInt(1, maDH);
    ResultSet rsCT = psCT.executeQuery();

    String ngayLapStr = (ngayLap != null) ? ngayLap.toString() : "";
%>

<div class="container">
    <h3 class="mb-3">✏️ Sửa đơn hàng #<%= maDH %></h3>

    <form action="suaDonHang" method="post">
      <input type="hidden" name="maDH" value="<%= maDH %>">

      <div class="row mb-2">
        <div class="col-md-4">
          <label class="form-label">Mã khách hàng</label>
          <input type="number" name="maKH" class="form-control" value="<%= maKH %>" required>
        </div>
        <div class="col-md-4">
          <label class="form-label">Ngày lập</label>
          <input type="date" name="ngayLap" class="form-control" value="<%= ngayLapStr %>" required>
        </div>
      </div>

      <h5>Sản phẩm trong đơn</h5>
      <table class="table table-sm table-bordered">
        <thead>
          <tr>
            <th>Mã sản phẩm</th>
            <th>Số lượng</th>
          </tr>
        </thead>
        <tbody id="itemsBody">
          <%
            while (rsCT.next()) {
          %>
            <tr>
              <td><input name="maSP" class="form-control form-control-sm" value="<%= rsCT.getInt("maSP") %>" required /></td>
              <td><input name="soLuong" type="number" min="1" value="<%= rsCT.getInt("soLuong") %>" class="form-control form-control-sm" required /></td>
            </tr>
          <% } %>
        </tbody>
      </table>

      <div class="mb-3">
        <button type="button" class="btn btn-outline-primary btn-sm" onclick="addRow()">+ Thêm dòng</button>
        <button type="button" class="btn btn-outline-secondary btn-sm" onclick="removeRow()">- Xóa dòng</button>
      </div>

      <div>
        <button type="submit" class="btn btn-success">💾 Cập nhật đơn hàng</button>
        <a href="quanLyDonHang.jsp" class="btn btn-secondary">⬅ Quay lại</a>
      </div>
    </form>
</div>

<%
    rsCT.close();
    psCT.close();
    conn.close();
%>
</body>
</html>
