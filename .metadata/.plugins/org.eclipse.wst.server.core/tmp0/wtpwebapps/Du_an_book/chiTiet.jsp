<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.SanPham" %>
<%@ page import="model.TacGia" %>
<%@ page import="model.TheLoai" %>

<%
    SanPham sp = (SanPham) request.getAttribute("sp");
    if (sp == null) {
        out.print("<h3>Không tìm thấy sản phẩm!</h3>");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title><%= sp.getTenSP() %></title>

</head>
<body class="container mt-5">

    <a href="index.jsp" class="btn btn-secondary mb-4">⬅ Quay lại</a>

    <div class="row">
        <div class="col-md-5">
        	<div class = "text-center">
            <img src="<%= sp.getAnh() %>" alt="<%= sp.getTenSP() %>" class="img-fluid rounded shadow-sm">
           	 </div>
        </div>

        <div class="col-md-7">
            <h2><%= sp.getTenSP() %></h2>
            <p><strong>Giá:</strong> <%= String.format("%,.0f", sp.getGia()) %> VNĐ</p>
            <p><strong>Thể loại:</strong> <%= sp.getTheLoai() != null ? sp.getTheLoai().getTenTL() : "Không rõ" %></p>
            <p><strong>Tác giả:</strong> <%= sp.getTacGia() != null ? sp.getTacGia().getTenTG() : "Không rõ" %></p>
            <hr>
            <p><%= sp.getMoTa() %></p>

            <form action="GioHangServlet" method="post">
                <input type="hidden" name="maSP" value="<%= sp.getMaSP() %>">
                <button type="submit" class="btn btn-primary">🛒 Thêm vào giỏ hàng</button>
            </form>
        </div>
    </div>

</body>
</html>
