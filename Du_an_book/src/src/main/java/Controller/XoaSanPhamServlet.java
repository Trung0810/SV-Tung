package Controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import dao.SanPhamDAO;
import model.SanPham;

@WebServlet("/xoaSanPham")
public class XoaSanPhamServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int maSP = Integer.parseInt(request.getParameter("maSP"));
        SanPhamDAO dao = new SanPhamDAO();
        dao.delete(new SanPham(maSP, null, 0, null, null, null, null));
        response.sendRedirect("quanLySanPham.jsp");
    }
}
