package model;

import java.sql.Date;
import java.util.Objects;


public class DonHang {
    private int maDH;        
    private int maKH;        
    private Date ngayLap;    
    private double tongTien; 

    public DonHang() {}

    public DonHang(int maDH, int maKH, Date ngayLap, double tongTien) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    public DonHang(int maKH, Date ngayLap, double tongTien) {
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    // ===== Getter & Setter =====

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    // ===== equals & hashCode theo maDH =====

    @Override
    public int hashCode() {
        return Objects.hash(maDH);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DonHang other = (DonHang) obj;
        return maDH == other.maDH;
    }

    // ===== toString =====

    @Override
    public String toString() {
        return "DonHang {" +
                "maDH=" + maDH +
                ", maKH=" + maKH +
                ", ngayLap=" + ngayLap +
                ", tongTien=" + tongTien +
                '}';
    }
}
