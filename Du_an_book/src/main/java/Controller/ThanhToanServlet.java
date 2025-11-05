package Controller;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.*;
import model.*;

@WebServlet("/thanh-toan")
public class ThanhToanServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
        KhachHang kh = (KhachHang) session.getAttribute("kh");
        
        if (kh == null) {
            response.sendRedirect("dangNhap.jsp?error=login_required");
            return;
        }

        if (gioHang == null || gioHang.isEmpty()) {
            response.sendRedirect("gioHang.jsp?error=empty");
            return;
        }

        double tongTien = 0;
        for (GioHangItem item : gioHang) {
            tongTien += item.getThanhTien();
        }

        DonHangDAO dhDAO = new DonHangDAO();
        DonHang donHang = new DonHang();
        donHang.setMaKH(kh.getMaKhachHang());
        donHang.setNgayLap(Date.valueOf(LocalDate.now()));
        donHang.setTongTien(tongTien);

        int maDH = dhDAO.insertAndReturnId(donHang);

        ChiTietDonHangDAO ctDAO = new ChiTietDonHangDAO();
        for (GioHangItem item : gioHang) {
            ChiTietDonHang ct = new ChiTietDonHang();
            ct.setMaDH(maDH);
            ct.setMaSP(item.getSanPham().getMaSP());
            ct.setSoLuong(item.getSoLuong());
            ct.setDonGia(item.getSanPham().getGia());
            ctDAO.insert(ct);
        }

        session.removeAttribute("gioHang");

        response.sendRedirect("gioHang.jsp?success=1");
    }
}
