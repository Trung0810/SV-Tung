<!DOCTYPE html>
<html lang="vi">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, model.SanPham, dao.SanPhamDAO" %>

<%
    request.setCharacterEncoding("UTF-8");

    int maTL = 0;
    try {
        maTL = Integer.parseInt(request.getParameter("maTL"));
    } catch (Exception e) {
        out.print("<h3>Thể loại không hợp lệ!</h3>");
        return;
    }

    SanPhamDAO spDAO = new SanPhamDAO();
    List<SanPham> dsSP = spDAO.selectByTheLoai(maTL);
%>
<head>
    <meta charset="UTF-8">
    <title>Sách theo thể loại</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<!-- Logo nhóm -->
<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">📚 4 Chàng Hiệp Sĩ Mộng Mơ</a>
    </div>
</nav>

<h3 class="text-center mb-4">📚 Danh sách sách theo thể loại</h3>

<!-- phần danh sách sản phẩm của bạn ở dưới -->
<div class="row">
    <% if (dsSP != null && !dsSP.isEmpty()) {
           for (SanPham sp : dsSP) { %>
        <div class="col-md-3 mb-4">
            <div class="card h-100 shadow-sm">
                <img src="<%= sp.getAnh() %>" class="card-img-top" alt="<%= sp.getTenSP() %>">
                <div class="card-body text-center">
                    <h6 class="card-title"><%= sp.getTenSP() %></h6>
                    <p class="fw-bold text-danger mb-2"><%= String.format("%,.0f", sp.getGia()) %> VNĐ</p>
                    <div class="d-flex justify-content-center gap-2">
                        <button class="btn btn-success btn-sm add-to-cart"
                                data-id="<%= sp.getMaSP() %>"
                                data-name="<%= sp.getTenSP() %>"
                                data-price="<%= sp.getGia() %>"
                                data-img="<%= sp.getAnh() %>">
                            🛒 Thêm vào giỏ
                        </button>
                        <a href="chi-tiet?maSP=<%= sp.getMaSP() %>" class="btn btn-outline-primary btn-sm">
                            👁 Xem chi tiết
                        </a>
                    </div>
                </div>
            </div>
        </div>
    <% } } else { %>
        <p class="text-center text-muted">Không có sản phẩm nào trong thể loại này.</p>
    <% } %>
</div>

</body>
</html>
