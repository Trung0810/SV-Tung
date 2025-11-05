package model;

public class SanPham {
    private int maSP; 
    private String tenSP;
    private double gia;
    private String anh;
    private String moTa;
    private TheLoai theLoai;
    private TacGia tacGia;

    public SanPham(int maSP, String tenSP, double gia, String anh, String moTa, TheLoai theLoai, TacGia tacGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.anh = anh;
        this.moTa = moTa;
        this.theLoai = theLoai;
        this.tacGia = tacGia;
    }

    public int getMaSP() { return maSP; }
    public void setMaSP(int maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }

    public String getAnh() { return anh; }
    public void setAnh(String anh) { this.anh = anh; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public TheLoai getTheLoai() { return theLoai; }
    public void setTheLoai(TheLoai theLoai) { this.theLoai = theLoai; }

    public TacGia getTacGia() { return tacGia; }
    public void setTacGia(TacGia tacGia) { this.tacGia = tacGia; }
}
