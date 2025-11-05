package Controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import dao.SanPhamDAO;
import model.*;

@WebServlet("/suaSanPham")
public class SuaSanPhamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int maSP = Integer.parseInt(request.getParameter("maSP"));
        String tenSP = request.getParameter("tenSP");
        double gia = Double.parseDouble(request.getParameter("gia"));
        String anh = request.getParameter("anh");
        String moTa = request.getParameter("moTa");
        int maTL = Integer.parseInt(request.getParameter("maTL"));
        int maTG = Integer.parseInt(request.getParameter("maTG"));

        SanPham sp = new SanPham(maSP, tenSP, gia, anh, moTa, new TheLoai(maTL, ""), new TacGia(maTG, "", null, ""));
        new SanPhamDAO().update(sp);

        response.sendRedirect("quanLySanPham.jsp");
    }
}
