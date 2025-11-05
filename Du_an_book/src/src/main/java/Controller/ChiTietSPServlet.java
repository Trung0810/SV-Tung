package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.SanPhamDAO;
import model.SanPham;

@WebServlet("/chi-tiet")
public class ChiTietSPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SanPhamDAO spDAO = new SanPhamDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String maSPStr = request.getParameter("maSP");
        if (maSPStr != null) {
            try {
                int maSP = Integer.parseInt(maSPStr);
                SanPham sp = spDAO.selectById(new SanPham(maSP, null, 0, null, null, null, null));

                if (sp != null) {
                    request.setAttribute("sp", sp);
                    request.getRequestDispatcher("chitietsanpham.jsp").forward(request, response);
                } else {
                    response.sendRedirect("index.jsp"); // nếu không tìm thấy sản phẩm
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("index.jsp"); // nếu maSP không hợp lệ
            }
        } else {
            response.sendRedirect("index.jsp"); // nếu không có tham số maSP
        }
    }
}
