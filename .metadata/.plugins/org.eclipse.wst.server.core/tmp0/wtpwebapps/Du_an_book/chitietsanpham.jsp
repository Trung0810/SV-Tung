<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.SanPham"%>
<%
    SanPham sp = (SanPham) request.getAttribute("sp");
    if (sp == null) {
        response.sendRedirect("index");
        return;
    }
%>

<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sản phẩm</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">
	
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
	integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
	integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
	crossorigin="anonymous"></script>
	<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
<style>
  body {
    font-family: 'Roboto', sans-serif;
  }
</style>
</head>

			<div class="container mt-4">
			    <h2>Chi tiết sản phẩm</h2>
			    <div class="row">
			        <div class="col-md-4">
			            <img src="<%=sp.getAnh()%>" alt="<%=sp.getTenSP()%>" class="img-fluid">
			        </div>
			        <div class="col-md-8">
			            <h3><%=sp.getTenSP()%></h3>
			            <p><strong>Giá:</strong> <%= String.format("%,.0f", sp.getGia()) %> VNĐ</p>
			            <p><strong>Thể loại:</strong> <%= sp.getTheLoai() != null ? sp.getTheLoai().getTenTL() : "Không xác định" %></p>
			            <p><strong>Tác giả:</strong> <%= sp.getTacGia() != null ? sp.getTacGia().getTenTG() : "Không xác định" %></p>
			            <p><strong>Mô tả:</strong> <%= sp.getMoTa() %></p>
			            <a href="index.jsp" class="btn btn-primary mt-2">Quay về trang chủ</a>
			        </div>
			    </div>
			</div>

</body>
</html>