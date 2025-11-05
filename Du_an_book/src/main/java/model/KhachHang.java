package model;

import java.sql.Date;


public class KhachHang {
    private int maKhachHang;   
    private String tenDangNhap;   
    private String matKhau;       
    private String hoVaTen;       
    private String gioiTinh;     
    private String diaChi;        
    private Date ngaySinh;        
    private String soDienThoai;   
    private String email;
    private String vaiTro = "user";

    public KhachHang() {}

    public KhachHang(int maKhachHang, String tenDangNhap, String matKhau, String hoVaTen,
            String gioiTinh, String diaChi, Date ngaySinh,
            String soDienThoai, String email, String vaiTro) {
		this.maKhachHang = maKhachHang;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.hoVaTen = hoVaTen;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.vaiTro = vaiTro;
		}
    

    public KhachHang(String tenDangNhap, String matKhau, String hoVaTen,
                     String gioiTinh, String diaChi, Date ngaySinh,
                     String soDienThoai, String email) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKhachHang='" + maKhachHang + '\'' +
                ", tenDangNhap='" + tenDangNhap + '\'' +
                ", hoVaTen='" + hoVaTen + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
