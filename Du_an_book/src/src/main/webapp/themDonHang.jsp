<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"/>
  <title>Thêm đơn hàng</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script>
    function addRow() {
      const tbody = document.getElementById('itemsBody');
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td><input name="maSP" class="form-control form-control-sm" required /></td>
        <td><input name="soLuong" type="number" min="1" value="1" class="form-control form-control-sm" required /></td>
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
  <div class="container">
    <h3 class="mb-3">➕ Thêm đơn hàng mới</h3>

    <form action="themDonHang" method="post">
      <input type="hidden" name="action" value="create">
      <div class="row mb-2">
        <div class="col-md-4">
          <label class="form-label">Mã khách hàng</label>
          <input type="number" name="maKH" class="form-control" required>
        </div>
        <div class="col-md-4">
          <label class="form-label">Ngày lập</label>
          <input type="date" name="ngayLap" class="form-control" required>
        </div>
      </div>

      <h5>Sản phẩm trong đơn</h5>
      <table class="table table-sm table-bordered">
        <thead>
          <tr><th>Mã sản phẩm</th><th>Số lượng</th></tr>
        </thead>
        <tbody id="itemsBody">
          <tr>
            <td><input name="maSP" class="form-control form-control-sm" required /></td>
            <td><input name="soLuong" type="number" min="1" value="1" class="form-control form-control-sm" required /></td>
          </tr>
        </tbody>
      </table>

      <div class="mb-3">
        <button type="button" class="btn btn-outline-primary btn-sm" onclick="addRow()">+ Thêm dòng</button>
        <button type="button" class="btn btn-outline-secondary btn-sm" onclick="removeRow()">- Xóa dòng</button>
      </div>

      <div>
        <button type="submit" class="btn btn-success">💾 Thêm đơn hàng</button>
        <a href="quanLyDonHang" class="btn btn-secondary">⬅ Quay lại</a>
      </div>
    </form>
  </div>
</body>
</html>
