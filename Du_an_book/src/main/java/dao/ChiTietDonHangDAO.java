package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ChiTietDonHang;

public class ChiTietDonHangDAO {

    public int insert(ChiTietDonHang t) {
        String sql = "INSERT INTO chitietdonhang(maCT, maDH, maSP, soLuong, donGia) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaCT());
            st.setInt(2, t.getMaDH());
            st.setInt(3, t.getMaSP());
            st.setInt(4, t.getSoLuong());
            st.setDouble(5, t.getDonGia());
            return st.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm chi tiết đơn hàng: " + e.getMessage());
            return 0;
        }
    }

    public int update(ChiTietDonHang t) {
        String sql = "UPDATE chitietdonhang SET maDH=?, maSP=?, soLuong=?, donGia=? WHERE maCT=?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaDH());
            st.setInt(2, t.getMaSP());
            st.setInt(3, t.getSoLuong());
            st.setDouble(4, t.getDonGia());
            st.setInt(5, t.getMaCT());
            return st.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật chi tiết đơn hàng: " + e.getMessage());
            return 0;
        }
    }

    public int delete(int maCT) {
        String sql = "DELETE FROM chitietdonhang WHERE maCT=?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, maCT);
            return st.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa chi tiết đơn hàng: " + e.getMessage());
            return 0;
        }
    }

    public List<ChiTietDonHang> selectAll() {
        List<ChiTietDonHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM chitietdonhang";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                ds.add(new ChiTietDonHang(
                    rs.getInt("maCT"),
                    rs.getInt("maDH"),
                    rs.getInt("maSP"),
                    rs.getInt("soLuong"),
                    rs.getDouble("donGia")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn danh sách chi tiết đơn hàng: " + e.getMessage());
        }
        return ds;
    }

    public ChiTietDonHang selectById(int maCT) {
        String sql = "SELECT * FROM chitietdonhang WHERE maCT=?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, maCT);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new ChiTietDonHang(
                        rs.getInt("maCT"),
                        rs.getInt("maDH"),
                        rs.getInt("maSP"),
                        rs.getInt("soLuong"),
                        rs.getDouble("donGia")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm chi tiết đơn hàng: " + e.getMessage());
        }
        return null;
    }

    public int insertMany(List<ChiTietDonHang> list) {
        int count = 0;
        for (ChiTietDonHang ctdh : list) {
            count += insert(ctdh);
        }
        return count;
    }

    public List<ChiTietDonHang> selectByDonHang(int maDH) {
        List<ChiTietDonHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM chitietdonhang WHERE maDH = ?";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, maDH);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ds.add(new ChiTietDonHang(
                        rs.getInt("maCT"),
                        rs.getInt("maDH"),
                        rs.getInt("maSP"),
                        rs.getInt("soLuong"),
                        rs.getDouble("donGia")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy chi tiết đơn hàng theo mã: " + e.getMessage());
        }
        return ds;
    }

}
