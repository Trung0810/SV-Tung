package Controller;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.ChiTietDonHangDAO;
import dao.DonHangDAO;
import dao.JDBCUtil;
import dao.SanPhamDAO;
import model.ChiTietDonHang;
import model.DonHang;
import model.SanPham;


@WebServlet("/suaDonHang")
public class SuaDonHangServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int maDH = Integer.parseInt(request.getParameter("maDH"));
        int maKH = Integer.parseInt(request.getParameter("maKH"));
        java.sql.Date ngayLap = java.sql.Date.valueOf(request.getParameter("ngayLap"));

        String[] maSPs = request.getParameterValues("maSP");
        String[] soLuongs = request.getParameterValues("soLuong");

        DonHangDAO dhDAO = new DonHangDAO();
        ChiTietDonHangDAO ctdhDAO = new ChiTietDonHangDAO();
        SanPhamDAO spDAO = new SanPhamDAO();

        try {
            List<ChiTietDonHang> oldList = ctdhDAO.selectByDonHang(maDH);
            for (ChiTietDonHang ctdh : oldList) {
                ctdhDAO.delete(ctdh.getMaCT());
            }

            double tongTien = 0;
            for (int i = 0; i < maSPs.length; i++) {
                int maSP = Integer.parseInt(maSPs[i]);
                int soLuong = Integer.parseInt(soLuongs[i]);
                double donGia = spDAO.selectById(new SanPham(maSP, "", 0,"","", null, null)).getGia();

                ctdhDAO.insert(new ChiTietDonHang(0, maDH, maSP, soLuong, donGia));
                tongTien += donGia * soLuong;
            }

            dhDAO.update(new DonHang(maDH, maKH, ngayLap, tongTien));

            response.sendRedirect("quanLyDonHang.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi cập nhật đơn hàng: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("suaDonHang.jsp");
            rd.forward(request, response);
        }
    }
}
