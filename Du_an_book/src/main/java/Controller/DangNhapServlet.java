package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.KhachHangDAO;
import model.KhachHang;

@WebServlet("/dang_nhap")
public class DangNhapServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DangNhapServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nếu người dùng truy cập trực tiếp bằng GET thì điều hướng về trang đăng nhập
        RequestDispatcher rd = request.getRequestDispatcher("/dangNhap.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String tenDangNhap = request.getParameter("tenDangNhap");
        String matKhau = request.getParameter("matKhau");

        // Gọi DAO kiểm tra đăng nhập
        KhachHangDAO khachHangDAO = new KhachHangDAO();
        KhachHang kh = khachHangDAO.selectByUsernameAndPassword(tenDangNhap, matKhau);

        String url;

        if (kh != null) {
            HttpSession session = request.getSession();
            session.setAttribute("kh", kh);

            if ("admin".equalsIgnoreCase(kh.getVaiTro())) {
                url = "/admin.jsp"; // ✅ chuyển sang admin
            } else {
                url = "/index.jsp"; // ✅ user thường
            }

        } else {
            request.setAttribute("baoLoi", "Tên đăng nhập hoặc mật khẩu không đúng");
            url = "/dangNhap.jsp";
        }

        // Chuyển tiếp đến trang tương ứng
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
