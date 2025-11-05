package Controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import model.*;
import dao.SanPhamDAO;

@WebServlet("/add-to-cart")
public class GioHangServlet extends HttpServlet {
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws IOException, ServletException {

	        HttpSession session = request.getSession();
	        List<GioHangItem> gioHang = (List<GioHangItem>) session.getAttribute("gioHang");
	        if (gioHang == null) gioHang = new ArrayList<>();

	        String action = request.getParameter("action");
	        int maSP = request.getParameter("maSP") != null ? Integer.parseInt(request.getParameter("maSP")) : -1;

	        SanPhamDAO dao = new SanPhamDAO();
	        SanPham sp = dao.selectById(new SanPham(maSP,"",0,"","",null,null));
	        boolean check = false;

	        if ("add".equals(action) && sp != null) {
	            boolean exists = false;
	            for (GioHangItem item : gioHang) {
	                if (item.getSanPham().getMaSP() == maSP) {
	                    item.setSoLuong(item.getSoLuong() + 1);
	                    exists = true;
	                    break;
	                }
	            }
	            if (!exists) gioHang.add(new GioHangItem(sp, 1));
	        } else if ("update".equals(action)) {
	        	check = true;
	            int soLuong = Integer.parseInt(request.getParameter("soLuong"));
	            for (GioHangItem item : gioHang) {
	                if (item.getSanPham().getMaSP() == maSP) {
	                    item.setSoLuong(soLuong);
	                    break;
	                }
	            }
	        } else if ("remove".equals(action)) {
	            gioHang.removeIf(item -> item.getSanPham().getMaSP() == maSP);
	            check = true;
	        } else if ("clear".equals(action)) {
	            gioHang.clear();
	            check = true;
	        }

	        session.setAttribute("gioHang", gioHang);
	        if(check) response.sendRedirect("gioHang.jsp");
	        else response.sendRedirect("index.jsp?added=true");

	    }
}
