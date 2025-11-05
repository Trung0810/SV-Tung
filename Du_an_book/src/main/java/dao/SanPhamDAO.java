package dao;

import java.sql.*;
import java.util.ArrayList;
import model.SanPham;
import model.TacGia;
import model.TheLoai;

public class SanPhamDAO implements DAOInterface<SanPham> {

    @Override
    public ArrayList<SanPham> selectAll() {
        ArrayList<SanPham> ds = new ArrayList<>();
        String sql = "SELECT * FROM sanpham";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                TheLoai theLoai = new TheLoaiDAO().selectById(new TheLoai(rs.getInt("maTL"), ""));
                TacGia tacGia = new TacGiaDAO().selectById(new TacGia(rs.getInt("maTG"), "",null,""));

                SanPham sp = new SanPham(
                    rs.getInt("maSP"),
                    rs.getString("tenSP"),
                    rs.getDouble("gia"),
                    rs.getString("anh"),
                    rs.getString("moTa"),
                    theLoai,
                    tacGia
                );
                ds.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ds;
    }



    @Override
    public SanPham selectById(SanPham t) {
        SanPham sp = null;
        String sql = "SELECT * FROM sanpham WHERE maSP = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaSP());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int maSP = rs.getInt("maSP");
                String tenSP = rs.getString("tenSP");
                double gia = rs.getDouble("gia");
                String anh = rs.getString("anh");
                String moTa = rs.getString("moTa");
                int maTL = rs.getInt("maTL");
                int maTG = rs.getInt("maTG");
                TheLoai theLoai = new TheLoaiDAO().selectById(new TheLoai(maTL, ""));
                TacGia tacGia = new TacGiaDAO().selectById(new TacGia(maTG, "",null,""));
                sp = new SanPham(maSP, tenSP, gia, anh, moTa, theLoai, tacGia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sp;
    }

    @Override
    public int insert(SanPham t) {
        int kq = 0;
        String sql = "INSERT INTO sanpham (tenSP, gia, anh, moTa, maTL, maTG) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, t.getTenSP());
            st.setDouble(2, t.getGia());
            st.setString(3, t.getAnh());
            st.setString(4, t.getMoTa());
            st.setInt(5, t.getTheLoai().getMaTL());
            st.setInt(6, t.getTacGia().getMaTG());

            kq = st.executeUpdate();
            System.out.println("Thực thi: " + sql + " -> " + kq + " dòng thêm mới");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kq;
    }

    @Override
    public int insertAll(ArrayList<SanPham> arr) {
        int dem = 0;
        for (SanPham sp : arr) {
            dem += insert(sp);
        }
        return dem;
    }

    @Override
    public int delete(SanPham t) {
        int kq = 0;
        String sql = "DELETE FROM sanpham WHERE maSP = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaSP());
            kq = st.executeUpdate();
            System.out.println("Thực thi: " + sql + " -> " + kq + " dòng bị xóa");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kq;
    }

    @Override
    public int deleteAll(ArrayList<SanPham> arr) {
        int dem = 0;
        for (SanPham sp : arr) {
            dem += delete(sp);
        }
        return dem;
    }

    @Override
    public int update(SanPham t) {
        int kq = 0;
        String sql = "UPDATE sanpham SET tenSP=?, gia=?, anh=?, moTa=?, maTL=?, maTG=? WHERE maSP=?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, t.getTenSP());
            st.setDouble(2, t.getGia());
            st.setString(3, t.getAnh());
            st.setString(4, t.getMoTa());
            st.setInt(5, t.getTheLoai().getMaTL());
            st.setInt(6, t.getTacGia().getMaTG());
            st.setInt(7, t.getMaSP());

            kq = st.executeUpdate();
            System.out.println("Thực thi: " + sql + " -> " + kq + " dòng cập nhật");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kq;
    }
    public ArrayList<SanPham> selectByTheLoai(int maTL) {
        ArrayList<SanPham> ds = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE maTL = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, maTL);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TheLoai theLoai = new TheLoai(rs.getInt("maTL"), "");
                TacGia tacGia = new TacGia(rs.getInt("maTG"), "", null, "");

                SanPham sp = new SanPham(
                    rs.getInt("maSP"),
                    rs.getString("tenSP"),
                    rs.getDouble("gia"),
                    rs.getString("anh"),
                    rs.getString("moTa"),
                    theLoai,
                    tacGia
                );

                ds.add(sp);
            }

            System.out.println(">>> Lấy được " + ds.size() + " sản phẩm từ thể loại có maTL = " + maTL);
            for (SanPham sp : ds) {
                System.out.println(" - " + sp.getTenSP() + " | Giá: " + sp.getGia());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ds;
    }


  

}
