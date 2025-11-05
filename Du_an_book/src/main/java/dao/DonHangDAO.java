package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DonHang;

public class DonHangDAO {

    public int insert(DonHang t) {
        int ketQua = 0;
        String sql = "INSERT INTO donhang(maKH, ngayLap, tongTien) VALUES (?, ?, ?)";
        try (
            Connection con = JDBCUtil.getConnection();
            PreparedStatement st = con.prepareStatement(sql)
        ) {
            st.setInt(1, t.getMaKH());
            st.setDate(2, t.getNgayLap());
            st.setDouble(3, t.getTongTien());
            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int insertAndReturnId(DonHang t) {
        int maDH = 0;
        String sql = "INSERT INTO donhang(maKH, ngayLap, tongTien) VALUES (?, ?, ?)";
        try (
            Connection con = JDBCUtil.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            st.setInt(1, t.getMaKH());
            st.setDate(2, t.getNgayLap());
            st.setDouble(3, t.getTongTien());

            st.executeUpdate();

            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    maDH = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maDH;
    }

    public int update(DonHang t) {
        int ketQua = 0;
        String sql = "UPDATE donhang SET maKH=?, ngayLap=?, tongTien=? WHERE maDH=?";
        try (
            Connection con = JDBCUtil.getConnection();
            PreparedStatement st = con.prepareStatement(sql)
        ) {
            st.setInt(1, t.getMaKH());
            st.setDate(2, t.getNgayLap());
            st.setDouble(3, t.getTongTien());
            st.setInt(4, t.getMaDH());

            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int delete(int maDH) {
        int ketQua = 0;
        String sql = "DELETE FROM donhang WHERE maDH=?";
        try (
            Connection con = JDBCUtil.getConnection();
            PreparedStatement st = con.prepareStatement(sql)
        ) {
            st.setInt(1, maDH);
            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<DonHang> selectAll() {
        ArrayList<DonHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM donhang ORDER BY maDH DESC";
        try (
            Connection con = JDBCUtil.getConnection();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                DonHang dh = new DonHang(
                    rs.getInt("maDH"),
                    rs.getInt("maKH"),
                    rs.getDate("ngayLap"),
                    rs.getDouble("tongTien")
                );
                ds.add(dh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public DonHang selectById(int maDH) {
        DonHang dh = null;
        String sql = "SELECT * FROM donhang WHERE maDH=?";
        try (
            Connection con = JDBCUtil.getConnection();
            PreparedStatement st = con.prepareStatement(sql)
        ) {
            st.setInt(1, maDH);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    dh = new DonHang(
                        rs.getInt("maDH"),
                        rs.getInt("maKH"),
                        rs.getDate("ngayLap"),
                        rs.getDouble("tongTien")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dh;
    }
    public double tinhTongDoanhThu() {
        double sum = 0;
        String sql = "SELECT SUM(tongTien) AS total FROM donhang";
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) sum = rs.getDouble("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

}
