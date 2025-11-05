package Controller;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.*;
import model.*;

@WebServlet("/themDonHang")
public class ThemDonHangServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị form thêm đơn hàng
        RequestDispatcher rd = request.getRequestDispatcher("themDonHang.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int maKH = Integer.parseInt(request.getParameter("maKH"));
            Date ngayLap = Date.valueOf(request.getParameter("ngayLap"));

            // 1️⃣ Tạo đơn hàng với tổng tiền tạm thời 0
            DonHang dh = new DonHang(0, maKH, ngayLap, 0);
            DonHangDAO dhDAO = new DonHangDAO();
            int maDH = dhDAO.insertAndReturnId(dh);

            // 2️⃣ Thêm chi tiết đơn hàng và tính tổng tiền
            String[] maSPs = request.getParameterValues("maSP");
            String[] soLuongs = request.getParameterValues("soLuong");

            ChiTietDonHangDAO ctdhDAO = new ChiTietDonHangDAO();
            SanPhamDAO spDAO = new SanPhamDAO();
            double tongTien = 0;

            for (int i = 0; i < maSPs.length; i++) {
                int maSP = Integer.parseInt(maSPs[i]);
                int soLuong = Integer.parseInt(soLuongs[i]);
                double donGia = spDAO.selectById(new SanPham(maSP, "", 0,"","", null, null)).getGia();

                // Thêm chi tiết đơn hàng
                ChiTietDonHang ctdh = new ChiTietDonHang(0, maDH, maSP, soLuong, donGia);
                ctdhDAO.insert(ctdh);

                tongTien += donGia * soLuong;
            }

            // 3️⃣ Cập nhật tổng tiền
            dh.setMaDH(maDH);
            dh.setTongTien(tongTien);
            dhDAO.update(dh);

            // 4️⃣ Chuyển hướng về danh sách
            response.sendRedirect("quanLyDonHang.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi thêm đơn hàng: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("themDonHang.jsp");
            rd.forward(request, response);
        }
    }
}
