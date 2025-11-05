<%@ page import="java.util.*, model.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Giỏ hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<% if ("1".equals(request.getParameter("success"))) { %>
	<div class="alert alert-success text-center" role="alert">
	    ✅ Thanh toán thành công! Cảm ơn bạn đã mua hàng.
	</div>
	<% } %>
	<% if ("empty".equals(request.getParameter("error"))) { %>
	<div class="alert alert-warning text-center" role="alert">
	    🛒 Giỏ hàng trống, không thể thanh toán.
	</div>
	<% } %>

<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">📚 4 Chàng Hiệp Sĩ Mộng Mơ</a>
    </div>
</nav>

<div class="container mt-4">
    <h3 class="text-center mb-4">🛒 Giỏ hàng của bạn</h3>

    <%
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        double tong = 0;
        if (gioHang != null && !gioHang.isEmpty()) {
    %>

    <table class="table table-bordered text-center align-middle">
        <thead class="table-dark">
            <tr>
                <th>Ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Thành tiền</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <% for (GioHangItem item : gioHang) {
                SanPham sp = item.getSanPham();
                double thanhTien = item.getThanhTien();
                tong += thanhTien;
            %>
            <tr>
                <td><img src="<%=sp.getAnh()%>" width="80" class="rounded"></td>
                <td><%=sp.getTenSP()%></td>
                <td><%=String.format("%,.0f", sp.getGia())%> VNĐ</td>
                <td>
                    <form action="add-to-cart" method="post" class="d-flex justify-content-center align-items-center gap-2">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="maSP" value="<%=sp.getMaSP()%>">
                        <input type="number" name="soLuong" value="<%=item.getSoLuong()%>" min="1" class="form-control form-control-sm" style="width:70px;">
                        <button type="submit" class="btn btn-sm btn-outline-success">🔄</button>
                    </form>
                </td>
                <td><%=String.format("%,.0f", thanhTien)%> VNĐ</td>
                <td>
                    <form action="add-to-cart" method="post" class="d-inline">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="maSP" value="<%=sp.getMaSP()%>">
                        <button type="submit" class="btn btn-sm btn-danger">❌ Xóa</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <div class="d-flex justify-content-between align-items-center mt-4">
        <h5 class="mb-0">Tổng cộng: <span class="text-danger fw-bold"><%=String.format("%,.0f", tong)%> VNĐ</span></h5>

        <div class="text-end">
            <form action="add-to-cart" method="post" class="d-inline">
                <input type="hidden" name="action" value="clear">
                <button class="btn btn-outline-danger btn-sm">🧹 Xóa tất cả</button>
            </form>

            <form action="thanh-toan" method="post" class="text-end mt-3">
			    <button type="submit" class="btn btn-success btn-sm">💰 Thanh toán</button>
			</form>

        </div>
    </div>

    <% } else { %>
        <p class="text-center">🛍️ Giỏ hàng của bạn đang trống.</p>
    <% } %>
</div>

</body>
</html>
