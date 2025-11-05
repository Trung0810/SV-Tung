<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*, model.SanPham" %>
<%@ page import="java.util.*, model.KhachHang" %>
<%@ page import="java.util.*, dao.SanPhamDAO" %>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!DOCTYPE html>
    <%
    String sort = request.getParameter("sort");
    SanPhamDAO spDAO = new SanPhamDAO();
    ArrayList<SanPham> dsSP = spDAO.selectAll();
    String keyword = request.getParameter("keyword");
    if (keyword != null && !keyword.trim().isEmpty()) {
        ArrayList<SanPham> ketQuaTimKiem = new ArrayList<>();
        for (SanPham sp : dsSP) {
            if (sp.getTenSP().toLowerCase().contains(keyword.toLowerCase())) {
                ketQuaTimKiem.add(sp);
            }
        }
        dsSP = ketQuaTimKiem;
    }

    if (sort != null && !sort.isEmpty()) {
        switch (sort) {
            case "nameAsc":
                Collections.sort(dsSP, new Comparator<SanPham>() {
                    public int compare(SanPham a, SanPham b) {
                        return a.getTenSP().compareToIgnoreCase(b.getTenSP());
                    }
                });
                break;
            case "nameDesc":
                Collections.sort(dsSP, new Comparator<SanPham>() {
                    public int compare(SanPham a, SanPham b) {
                        return b.getTenSP().compareToIgnoreCase(a.getTenSP());
                    }
                });
                break;
            case "priceAsc":
                Collections.sort(dsSP, new Comparator<SanPham>() {
                    public int compare(SanPham a, SanPham b) {
                        return Double.compare(a.getGia(), b.getGia());
                    }
                });
                break;
            case "priceDesc":
                Collections.sort(dsSP, new Comparator<SanPham>() {
                    public int compare(SanPham a, SanPham b) {
                        return Double.compare(b.getGia(), a.getGia());
                    }
                });
                break;
        }
    }
    request.setAttribute("dsSP", dsSP);
%>
    
<html>
<head>
<meta charset="UTF-8">
<title>BookStore</title>
<link rel="stylesheet" href="css/style.css">
	
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

</head>
<% if ("true".equals(request.getParameter("added"))) { %>
<div class="alert alert-success text-center" role="alert">
    🛒 Sản phẩm đã được thêm vào giỏ hàng!
</div>
<% } %>

<body>

<nav class="navbar navbar-expand-lg bg-light px-4">
		 <div class="container-fluid">
			   <nav class="navbar navbar-dark bg-dark">
				    <div class="container">
				        <a class="navbar-brand" href="index.jsp">📚 4 Chàng Hiệp Sĩ Mộng Mơ</a>
				    </div>
				</nav>

			
			    <div class="ms-auto d-flex align-items-center gap-3">
			      <a href="gioHang.jsp" class="btn btn-outline-secondary position-relative">
			        🛒 Giỏ hàng
			        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
			          <% 
			            Integer cartCount = (Integer) session.getAttribute("cartCount");
			            if (cartCount == null) cartCount = 0;
			          %>
			          <%= cartCount %>
			        </span>
			        <% 
			        Object obj = session.getAttribute("kh");
			        KhachHang khachHang = null;
			        if(obj != null){
			        khachHang = (KhachHang)obj;
			        }
			        if(khachHang == null){
			        	%>
			        	</a>
			      <a href="dangNhap.jsp" class="btn btn-primary">Đăng nhập</a>
			      <%} else {%>
			    	  </a>
			      <a style = "while-space: nowrap;" href="dang_xuat" class="btn btn-primary">Đăng xuất</a>			      	
			      <%} %>
			      
			    </div>
		  </div>
</nav>
				<div class="container mt-4">
					<div class="row">
						<!-- Menu left -->
						<div class="col-lg-3">
			  <div class="list-group">

				<div class="mb-3">
				  <form action="index.jsp" method="get" class="d-flex">
				    <input type="text" name="keyword" class="form-control me-2" 
				           placeholder="🔍 Tìm sản phẩm..." 
				           value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "" %>">
				    <button type="submit" class="btn btn-primary btn-sm">Tìm</button>
				  </form>
				</div>
			    <div class="list-group-item active fw-bold text-center">
			      THỂ LOẠI
			    </div>
			    <a href="theLoai.jsp?maTL=1"  class="list-group-item list-group-item-action">
			      Sách Khoa học - Viễn tưởng
			    </a>
			    <a href="theLoai.jsp?maTL=3" class="list-group-item list-group-item-action">
			      Sách Kiến thức - Học thuật
			    </a>
			    <a href="theLoai.jsp?maTL=2" class="list-group-item list-group-item-action">
			      Sách Tâm lí - Kỹ năng sống
			    </a>
			    <!-- Thanh sắp xếp -->
					<div class="bg-light py-2 px-4 border-top border-bottom d-flex align-items-center justify-content-between">
					  <div class="fw-bold">Sắp xếp sản phẩm:</div>
					  <form action="index.jsp" method="get" class="d-flex align-items-center gap-2">
					    <select name="sort" class="form-select form-select-sm" onchange="this.form.submit()">
					      <option value="">-- Chọn cách sắp xếp --</option>
					      <option value="nameAsc" <%= "nameAsc".equals(request.getParameter("sort")) ? "selected" : "" %>>Theo tên (A → Z)</option>
					      <option value="nameDesc" <%= "nameDesc".equals(request.getParameter("sort")) ? "selected" : "" %>>Theo tên (Z → A)</option>
					      <option value="priceAsc" <%= "priceAsc".equals(request.getParameter("sort")) ? "selected" : "" %>>Giá tăng dần</option>
					      <option value="priceDesc" <%= "priceDesc".equals(request.getParameter("sort")) ? "selected" : "" %>>Giá giảm dần</option>
					    </select>
					  </form>
					</div>
			    
			  </div>
			</div>
			
			<div class="col-lg-9">
				<!-- Slider -->
				<div id="carouselExampleIndicators" class="carousel slide mb-4"
					data-bs-ride="true">
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="0" class="active" aria-current="true"
							aria-label="Slide 1"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="1" aria-label="Slide 2"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="2" aria-label="Slide 3"></button>
					</div>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="img/slider/anhbia1.png" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="img/slider/anhbia2.png" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="img/slider/anhbia3.png" class="d-block w-100" alt="...">
						</div>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>

				<div class="row">	
						<div class="row">
							 <% if (dsSP != null && !dsSP.isEmpty()) {
							           for (SanPham sp : dsSP) { %>
							        <div class="col-lg-4 col-md-6 mb-4">
							            <div class="card h-100 shadow-sm">
							                <img src="<%= sp.getAnh() %>" class="card-img-top" alt="<%= sp.getTenSP() %>">
							                <div class="card-body text-center">
							                    <h6 class="card-title"><%= sp.getTenSP() %></h6>
							                    <p class="fw-bold text-danger mb-2"><%= String.format("%,.0f", sp.getGia()) %> VNĐ</p>
							                    <div class="d-flex justify-content-center gap-2">
							                       <form action="add-to-cart" method="post" style="display:inline;">
													    <input type="hidden" name="action" value="add">
													    <input type="hidden" name="maSP" value="<%= sp.getMaSP() %>">
													    <button type="submit" class="btn btn-success btn-sm">
													        🛒 Thêm vào giỏ
													    </button>
													</form>


							                        <a href="chi-tiet?maSP=<%= sp.getMaSP() %>" class="btn btn-outline-primary btn-sm">
							                            👁 Xem chi tiết
							                        </a>
							                    </div>
							                </div>
							            </div>
							        </div>
							    <% } } else { %>
							<p>Không có sản phẩm nào.</p>
							<% } %>
						</div>
			
				</div>

			</div>

		</div>
	</div>

	<footer class="py-3 my-4">
		<ul class="nav justify-content-center border-bottom pb-3 mb-3">
			<li class="nav-item"><a href="index.jsp"
				class="nav-link px-2 text-muted">Trang chủ</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Quy định giao hàng</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Quy định trả hàng</a></li>
			<li class="nav-item"><a href="#"	
				class="nav-link px-2 text-muted">Liên hệ</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Blogs</a></li>
		</ul>
		<p class="text-center text-muted">© 2022 4changhiepsimongmo.vn, Inc</p>
	</footer>

	</div>
</body>
</html>

