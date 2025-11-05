<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dao.SanPhamDAO, model.SanPham" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa sản phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="p-4">
<%
    int maSP = Integer.parseInt(request.getParameter("maSP"));
    SanPham sp = new SanPhamDAO().selectById(new SanPham(maSP, null, 0, null, null, null, null));
%>
    <h3 class="text-center mb-3">Chỉnh sửa sản phẩm</h3>
    <form action="suaSanPham" method="post">
        <input type="hidden" name="maSP" value="<%= sp.getMaSP() %>">
        <div class="mb-3">
            <label class="form-label">Tên sản phẩm:</label>
            <input type="text" name="tenSP" class="form-control" value="<%= sp.getTenSP() %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Giá:</label>
            <input type="number" step="0.01" name="gia" class="form-control" value="<%= sp.getGia() %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Ảnh (URL):</label>
            <input type="text" name="anh" class="form-control" value="<%= sp.getAnh() %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Mô tả:</label>
            <textarea name="moTa" class="form-control"><%= sp.getMoTa() %></textarea>
        </div>
        <div class="mb-3">
            <label class="form-label">Mã thể loại:</label>
            <input type="number" name="maTL" class="form-control" value="<%= sp.getTheLoai().getMaTL() %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Mã tác giả:</label>
            <input type="number" name="maTG" class="form-control" value="<%= sp.getTacGia().getMaTG() %>" required>
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="quanLySanPham.jsp" class="btn btn-secondary">Quay lại</a>
    </form>
</body>
</html>
