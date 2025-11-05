package dao;

import java.sql.*;
import java.util.ArrayList;
import model.TacGia;

public class TacGiaDAO implements DAOInterface<TacGia> {

    @Override
    public ArrayList<TacGia> selectAll() {
        ArrayList<TacGia> ketQua = new ArrayList<>();
        String sql = "SELECT * FROM tacgia";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int maTG = rs.getInt("maTG");
                String hoVaTen = rs.getString("tenTG");
                Date ngaySinh = rs.getDate("ngaySinh");
                String tieuSu = rs.getString("tieuSu");

                TacGia tg = new TacGia(maTG, hoVaTen, ngaySinh, tieuSu);
                ketQua.add(tg);
            }

            System.out.println("Đã thực thi: " + sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public TacGia selectById(TacGia t) {
        TacGia ketQua = null;
        String sql = "SELECT * FROM tacgia WHERE maTG = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaTG());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int maTG = rs.getInt("maTG");
                String hoVaTen = rs.getString("tenTG");
                Date ngaySinh = rs.getDate("ngaySinh");
                String tieuSu = rs.getString("tieuSu");

                ketQua = new TacGia(maTG, hoVaTen, ngaySinh, tieuSu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int insert(TacGia t) {
        int ketQua = 0;
        String sql = "INSERT INTO tacgia (hoVaTen, ngaySinh, tieuSu) VALUES (?, ?, ?)";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, t.getTenTG());
            st.setDate(2, t.getNgaySinh());
            st.setString(3, t.getTieuSu());

            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int insertAll(ArrayList<TacGia> arr) {
        int dem = 0;
        for (TacGia tg : arr) {
            dem += insert(tg);
        }
        return dem;
    }

    @Override
    public int delete(TacGia t) {
        int ketQua = 0;
        String sql = "DELETE FROM tacgia WHERE maTG = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, t.getMaTG());
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    @Override
    public int deleteAll(ArrayList<TacGia> arr) {
        int dem = 0;
        for (TacGia tg : arr) {
            dem += delete(tg);
        }
        return dem;
    }

    @Override
    public int update(TacGia t) {
        int ketQua = 0;
        String sql = "UPDATE tacgia SET hoVaTen = ?, ngaySinh = ?, tieuSu = ? WHERE maTG = ?";

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, t.getTenTG());
            st.setDate(2, t.getNgaySinh());
            st.setString(3, t.getTieuSu());
            st.setInt(4, t.getMaTG());

            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    // --- Test DAO ---
    public static void main(String[] args) {
        TacGiaDAO dao = new TacGiaDAO();

        // Thêm tác giả mới (test)
        /*
        TacGia tgMoi = new TacGia(0, "Marie Kondo", Date.valueOf("1984-10-09"),
                "Chuyên gia người Nhật nổi tiếng với nghệ thuật sống tối giản.");
        dao.insert(tgMoi);
        */

        // Lấy danh sách tất cả tác giả
        ArrayList<TacGia> list = dao.selectAll();
        list.forEach(System.out::println);

        // Lấy 1 tác giả theo mã
        // TacGia tg = dao.selectById(new TacGia(1, "", null, ""));
        // System.out.println(tg);

        // Cập nhật
        // tg.setTieuSu("Tác giả khoa học viễn tưởng nổi tiếng thế giới");
        // dao.update(tg);

        // Xóa
        // dao.delete(new TacGia(4, "", null, ""));
    }
}
