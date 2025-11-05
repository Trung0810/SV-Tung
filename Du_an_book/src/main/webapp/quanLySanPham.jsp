<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, dao.SanPhamDAO, model.SanPham" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý sản phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="p-4">
    <h2 class="mb-3 text-center">Quản lý sản phẩm</h2>
    <div class="text-end mb-3">
        <a href="themSanPham.jsp" class="btn btn-success">+ Thêm sản phẩm</a>
    </div>

    <table class="table table-bordered table-striped text-center align-middle">
        <thead class="table-dark">
            <tr>
                <th>Mã SP</th>
                <th>Tên sản phẩm</th>
                <th>Giá</th>
                <th>Ảnh</th>
                <th>Mô tả</th>
                <th>Thể loại</th>
                <th>Tác giả</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
        <%
            SanPhamDAO dao = new SanPhamDAO();
            ArrayList<SanPham> ds = dao.selectAll();
            for (SanPham sp : ds) {
        %>
            <tr>
                <td><%= sp.getMaSP() %></td>
                <td><%= sp.getTenSP() %></td>
                <td><%= sp.getGia() %></td>
                <td><img src="<%= sp.getAnh() %>" width="70" height="70" style="object-fit:cover;"></td>
                <td><%= sp.getMoTa() %></td>
                <td><%= sp.getTheLoai().getTenTL() %></td>
                <td><%= sp.getTacGia().getTenTG() %></td>
                <td>
                    <a href="suaSanPham.jsp?maSP=<%= sp.getMaSP() %>" class="btn btn-primary btn-sm">Sửa</a>
                    <a href="xoaSanPham?maSP=<%= sp.getMaSP() %>" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc muốn xóa sản phẩm này không?')">Xóa</a>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</body>
</html>
