package dao;

import java.sql.*;
import java.util.ArrayList;
import model.TheLoai;

public class TheLoaiDAO implements DAOInterface<TheLoai> {

    @Override
    public ArrayList<TheLoai> selectAll() {
        ArrayList<TheLoai> ketQua = new ArrayList<>();
        String sql = "SELECT * FROM theloai";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int maTL = rs.getInt("maTL");
                String tenTL = rs.getString("tenTL");
                ketQua.add(new TheLoai(maTL, tenTL));
            }

            System.out.println("Đã thực thi: " + sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public TheLoai selectById(TheLoai t) {
        TheLoai ketQua = null;
        String sql = "SELECT * FROM theloai WHERE maTL = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaTL());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int maTL = rs.getInt("maTL");
                String tenTL = rs.getString("tenTL");
                ketQua = new TheLoai(maTL, tenTL);
            }

            System.out.println("Đã thực thi: " + sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int insert(TheLoai t) {
        int ketQua = 0;
        String sql = "INSERT INTO theloai(maTL, tenTL) VALUES (?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaTL());
            st.setString(2, t.getTenTL());
            ketQua = st.executeUpdate();

            System.out.println("Đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int insertAll(ArrayList<TheLoai> arr) {
        int dem = 0;
        for (TheLoai tl : arr) {
            dem += insert(tl);
        }
        return dem;
    }

    @Override
    public int delete(TheLoai t) {
        int ketQua = 0;
        String sql = "DELETE FROM theloai WHERE maTL = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaTL());
            ketQua = st.executeUpdate();

            System.out.println("Đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị xóa!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int deleteAll(ArrayList<TheLoai> arr) {
        int dem = 0;
        for (TheLoai tl : arr) {
            dem += delete(tl);
        }
        return dem;
    }

    @Override
    public int update(TheLoai t) {
        int ketQua = 0;
        String sql = "UPDATE theloai SET tenTL = ? WHERE maTL = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, t.getTenTL());
            st.setInt(2, t.getMaTL());
            ketQua = st.executeUpdate();

            System.out.println("Đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị cập nhật!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }
}
