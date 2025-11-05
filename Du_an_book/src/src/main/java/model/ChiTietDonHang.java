package model;

public class ChiTietDonHang {
    private int maCT;
    private int maDH;
    private int maSP;
    private int soLuong;
    private double donGia;

    public ChiTietDonHang() {}

    public ChiTietDonHang(int maCT, int maDH, int maSP, int soLuong, double donGia) {
        this.maCT = maCT;
        this.maDH = maDH;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getMaCT() {
        return maCT;
    }

    public void setMaCT(int maCT) {
        this.maCT = maCT;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ChiTietDonHang{" +
                "maCT=" + maCT +
                ", maDH=" + maDH +
                ", maSP=" + maSP +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                '}';
    }
}
