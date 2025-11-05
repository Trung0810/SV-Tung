package Controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.JDBCUtil;

@WebServlet("/xoaDonHang")
public class XoaDonHangServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int maDH = Integer.parseInt(request.getParameter("maDH"));

        try (Connection conn = JDBCUtil.getConnection()) {

            String sqlCT = "DELETE FROM ChiTietDonHang WHERE maDH = ?";
            PreparedStatement psCT = conn.prepareStatement(sqlCT);
            psCT.setInt(1, maDH);
            psCT.executeUpdate();
            psCT.close();

            String sqlDH = "DELETE FROM DonHang WHERE maDH = ?";
            PreparedStatement psDH = conn.prepareStatement(sqlDH);
            psDH.setInt(1, maDH);
            psDH.executeUpdate();
            psDH.close();

            response.sendRedirect("quanLyDonHang.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi xóa đơn hàng: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("quanLyDonHang.jsp");
            rd.forward(request, response);
        }
    }
}
