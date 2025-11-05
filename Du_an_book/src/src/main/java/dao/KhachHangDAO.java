package dao;

import java.sql.*;
import java.util.ArrayList;
import model.KhachHang;

public class KhachHangDAO implements DAOInterface<KhachHang> {

    @Override
    public ArrayList<KhachHang> selectAll() {
        ArrayList<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khachhang";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        rs.getInt("maKH"),
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getString("tenKH"),
                        rs.getString("gioiTinh"),
                        rs.getString("diaChi"),
                        rs.getDate("ngaySinh"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("vaiTro") // ✅ đọc vai trò
                );
                list.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public KhachHang selectById(KhachHang t) {
        KhachHang kh = null;
        String sql = "SELECT * FROM khachhang WHERE maKH = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaKhachHang());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                kh = new KhachHang(
                        rs.getInt("maKH"),
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getString("tenKH"),
                        rs.getString("gioiTinh"),
                        rs.getString("diaChi"),
                        rs.getDate("ngaySinh"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("vaiTro")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }

    // ✅ Đăng nhập (tìm theo tên đăng nhập và mật khẩu)
    public KhachHang selectByUsernameAndPassword(String tenDangNhap, String matKhau) {
        KhachHang kh = null;
        String sql = "SELECT * FROM khachhang WHERE tenDangNhap = ? AND matKhau = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, tenDangNhap);
            st.setString(2, matKhau);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                kh = new KhachHang(
                        rs.getInt("maKH"),
                        rs.getString("tenDangNhap"),
                        rs.getString("matKhau"),
                        rs.getString("tenKH"),
                        rs.getString("gioiTinh"),
                        rs.getString("diaChi"),
                        rs.getDate("ngaySinh"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("vaiTro") // ✅ Lấy vai trò
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }

    // ✅ Kiểm tra tên đăng nhập tồn tại
    public boolean kiemTraTenDangNhap(String tenDangNhap) {
        boolean tonTai = false;
        String sql = "SELECT 1 FROM khachhang WHERE tenDangNhap = ? LIMIT 1";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, tenDangNhap);
            ResultSet rs = st.executeQuery();
            tonTai = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tonTai;
    }

    // ✅ Thêm khách hàng mới
    @Override
    public int insert(KhachHang t) {
        int rows = 0;
        String sql = "INSERT INTO khachhang (maKH, tenDangNhap, matKhau, tenKH, gioiTinh, diaChi, ngaySinh, sdt, email, vaiTro) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaKhachHang());
            st.setString(2, t.getTenDangNhap());
            st.setString(3, t.getMatKhau());
            st.setString(4, t.getHoVaTen());
            st.setString(5, t.getGioiTinh());
            st.setString(6, t.getDiaChi());
            st.setDate(7, t.getNgaySinh());
            st.setString(8, t.getSoDienThoai());
            st.setString(9, t.getEmail());
            st.setString(10, t.getVaiTro()); // ✅ thêm vai trò

            rows = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int update(KhachHang t) {
        int rows = 0;
        String sql = "UPDATE khachhang SET tenDangNhap=?, matKhau=?, tenKH=?, gioiTinh=?, diaChi=?, ngaySinh=?, sdt=?, email=?, vaiTro=? WHERE maKH=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, t.getTenDangNhap());
            st.setString(2, t.getMatKhau());
            st.setString(3, t.getHoVaTen());
            st.setString(4, t.getGioiTinh());
            st.setString(5, t.getDiaChi());
            st.setDate(6, t.getNgaySinh());
            st.setString(7, t.getSoDienThoai());
            st.setString(8, t.getEmail());
            st.setString(9, t.getVaiTro());
            st.setInt(10, t.getMaKhachHang());

            rows = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(KhachHang t) {
        int rows = 0;
        String sql = "DELETE FROM khachhang WHERE maKH=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaKhachHang());
            rows = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int insertAll(ArrayList<KhachHang> arr) {
        int count = 0;
        for (KhachHang k : arr) count += insert(k);
        return count;
    }

    @Override
    public int deleteAll(ArrayList<KhachHang> arr) {
        int count = 0;
        for (KhachHang k : arr) count += delete(k);
        return count;
    }
}
