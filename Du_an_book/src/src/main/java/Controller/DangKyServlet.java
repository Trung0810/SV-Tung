package Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Random;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KhachHangDAO;
import model.KhachHang;

/**
 * Servlet implementation class Register
 */
@WebServlet("/do_register")
public class DangKyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangKyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("text/html;charset=UTF-8");
		String tenDangNhap = request.getParameter("tenDangNhap");
		String matKhau = request.getParameter("matKhau");
		String matKhauNhapLai = request.getParameter("matKhauNhapLai");
		String hoVaTen = request.getParameter("hoVaTen");
		String gioiTinh = request.getParameter("gioiTinh");
		String ngaySinh = request.getParameter("ngaySinh");
		String diaChi = request.getParameter("diaChi");
		String soDienThoai = request.getParameter("soDienThoai");
		String email = request.getParameter("email");
		
		request.setAttribute("tenDangNhap", tenDangNhap);
		request.setAttribute("matKhau", matKhau);
		request.setAttribute("hoVaTen", hoVaTen);
		request.setAttribute("gioiTinh", gioiTinh);
		request.setAttribute("ngaySinh", ngaySinh);
		request.setAttribute("diaChi", diaChi);
		request.setAttribute("soDienThoai", soDienThoai);
		request.setAttribute("email", email);

		KhachHangDAO khachHangDAO = new KhachHangDAO();
		String baoLoi = "";
		String url = "";
		if(khachHangDAO.kiemTraTenDangNhap(tenDangNhap)) {
			baoLoi += " Tên đang nhập đã tồn tại!";
		}
		if(!matKhau.equals(matKhauNhapLai)) {
			baoLoi += "Mật khẩu không khớp!</br>";
		}
		request.setAttribute("baoLoi", baoLoi);
		if(baoLoi.length() > 0) {
			url = "/dangKy.jsp";
		} else {
			Random random = new Random();
			int maKH = random.nextInt(1000);
			KhachHang khachHang = new KhachHang(maKH,tenDangNhap,matKhau, hoVaTen ,gioiTinh, diaChi,Date.valueOf(ngaySinh),soDienThoai,email,"");
			khachHangDAO.insert(khachHang);
			url = "/dangNhap.jsp";
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
