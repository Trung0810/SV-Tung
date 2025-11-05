<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Đăng nhập</title>
<link rel="stylesheet" href="css/style.css">
	
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
<!-- Custom styles for this template -->
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<link href="<%=url%>/css/sign_in.css" rel="stylesheet">


</head>
<body>
<a href="<%=url %>/index.jsp" 
   class="btn btn-secondary position-fixed" 
   style="top: 20px; left: 20px; z-index: 1000;">
   ← Về trang chủ
</a>
	<main class="form-signin w-100 m-auto">
		<form class="text-center" action="dang_nhap" method="POST">
			<img class="mb-4" src="<%=url %>/img/product/BookStore (1).png"
				alt="" width="72">
			<h1 class="h3 mb-3 fw-normal">ĐĂNG NHẬP</h1>
			<%
				String baoLoi = request.getAttribute("baoLoi")+"";
				if(baoLoi.equals("null")){
					baoLoi = "";
				}
			%>
			<div class="text-center"><span class="red"><%=baoLoi %></span></div>
			<div class="form-floating">
				<input type="text" class="form-control" id="tenDangNhap"
					placeholder="Tên đăng nhập" name="tenDangNhap"> <label for="tenDangNhap">Tên đăng nhập</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="matKhau"
					placeholder="Mật khẩu" name="matKhau"> <label for="matKhau">Mật khẩu</label>
			</div>

			<div class="checkbox mb-3">
				<label> <input type="checkbox" value="remember-me">
					Ghi nhớ tài khoản này
				</label>
			</div>
			<button class="w-100 btn btn-lg btn-primary" type="submit">Đăng nhập</button>
			
			<a href="dangKy.jsp">Đăng ký tài khoản mới</a>
			<p class="mt-5 mb-3 text-muted">&copy; 2017–2025</p>
		</form>
	</main>
</body>
</html>