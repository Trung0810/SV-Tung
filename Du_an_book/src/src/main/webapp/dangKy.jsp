<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<link rel="stylesheet" href="css/style.css">
	
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">


<style>
	.red{
		color : red;
	}
</style>
</head>
<body>

	<%
		String baoLoi = (request.getAttribute("baoLoi")) + "";
		baoLoi = (baoLoi.equals("null")) ? "": baoLoi;
		
		String tenDangNhap = request.getAttribute("tenDangNhap") + "";
		tenDangNhap = (tenDangNhap.equals("null")) ? "": tenDangNhap;
		
		String matKhau = request.getAttribute("matKhau") + "";
		matKhau = (matKhau.equals("null")) ? "": matKhau;
		
		String hoVaTen = request.getAttribute("hoVaTen")+ "";
		hoVaTen = (hoVaTen.equals("null")) ? "": hoVaTen;
		
		String gioiTinh = request.getAttribute("gioiTinh")+ "";
		gioiTinh = (gioiTinh.equals("null")) ? "": gioiTinh;
		
		String ngaySinh = request.getAttribute("ngaySinh")+ "";
		ngaySinh = (ngaySinh.equals("null")) ? "": ngaySinh;
		
		String diaChi = request.getAttribute("diaChi")+ "";
		diaChi = (diaChi.equals("null")) ? "": diaChi;
		
		String soDienThoai = request.getAttribute("soDienThoai")+ "";
		soDienThoai = (soDienThoai.equals("null")) ? "": soDienThoai;
		
		String email = request.getAttribute("email")+ "";
		email = (email.equals("null")) ? "": email;

		
	%>

	<div class = "container">
	<h1 class = "text-center">ĐĂNG KÝ TÀI KHOẢN</h1>
	<div class = "red" id = "baoLoi"><%= baoLoi %></div>
	<form class = "form" action = "do_register" method = "post">
		<h3>Tài khoản</h3>
		<div class = "mb-3">
			<label for="tenDangNhap" class = "form_label">Tên đăng nhập<span class = "red">*</span></label>
			<input type = "text" class = "form-control" id = "tenDangNhap" name = "tenDangNhap" required="required" value = "<%= tenDangNhap%>">
		</div>
		<div class = "mb-3">
			<label for="matKhau" class = "form_label">Mật khẩu<span class = "red">*</span></label>
			<input type = "password" class = "form-control" id = "matKhau" name = "matKhau" required="required" onkeyup = "kiemTraMatKhau()">
		</div>
		<div class = "mb-3">
			<label for="matKhauNhapLai" class = "form_label">Nhập lại mật khẩu<span class = "red">*</span><span id = "msg" class = "red"></span>
			</label>
			<input type = "password" class = "form-control" id = "matKhauNhapLai" name = "matKhauNhapLai" required="required" onkeyup = "kiemTraMatKhau()">
		</div>
		<h3>Thông tin khách hàng</h3>
		<div class = "mb-3">
			<label for="hoVaTen" class = "form_label">Họ và tên</label>
			<input type = "text" class = "form-control" id = "hoVaTen" name = "hoVaTen" value = "<%= hoVaTen%>">
		</div>
		<div class = "mb-3">
			<label for="gioiTinh" class = "form_label">Giới tính</label>
			<select class = "form-control" id = "gioiTinh" name = "gioiTinh">
				<option></option>
				<option value = "Nam" <%=(gioiTinh.equals("Nam"))? "selected = 'selected'":"" %>>Nam</option>
				<option value = "Nữ" <%=(gioiTinh.equals("Nữ"))? "selected = 'selected'":"" %> >Nữ</option>
				<option value = "Khác" <%=(gioiTinh.equals("Khác"))? "selected = 'selected'":"" %>>Khác</option>
			</select>
		</div>
		<div class = "mb-3">
			<label for="ngaySinh" class = "form_label">Ngày sinh</label>
			<input type = "date" class = "form-control" id = "ngaySinh" name = "ngaySinh" value = "<%= ngaySinh%>">
		</div>
		<h3>Địa chỉ</h3>
		<div class = "mb-3">
			<label for="diaChi" class = "form_label">Địa chỉ nhận hàng</label>
			<input type = "text" class = "form-control" id = "diaChi" name = "diaChi" value = "<%= diaChi%>">
		</div>
		<div class = "mb-3">
			<label for="soDienThoai" class = "form_label">Số điện thoại</label>
			<input type = "tel" class = "form-control" id = "soDienThoai" name = "soDienThoai" value = "<%= soDienThoai%>"> 
		</div>
		<div class = "mb-3">
			<label for="email" class = "form_label">Email</label>
			<input type = "email" class = "form-control" id = "email" name = "email" value = "<%= email%>">
		</div>
		<div class = "mb-3">
			<label for="dongYDieuKhoan" class = "form_label">Đồng ý với <a>điều khoản</a></label>
			<input type = "checkbox" class = "form-check-input" id = "dongYDieuKhoan" name = "dongYDieuKhoan">
		</div>
		<input class = "btn btn-primary form-control" type = "submit" value = "Đăng Ký"/>
		
	</form>
	</div>
</body>

<script>

	function kiemTraMatKhau(){
		matKhau = document.getElementById("matKhau").value;
		matKhauNhapLai = document.getElementById("matKhauNhapLai").value;
		if(matKhau != matKhauNhapLai){
			 document.getElementById("msg").innerHTML = "Mật khẩu không khớp!";
			 return false;
		} else{
			 document.getElementById("msg").innerHTML = "";
			 return true;
		}
	}
</script>

</html>