package model;

import java.sql.Date;
import java.util.Objects;

public class TacGia {
    private int maTG;
    private String tenTG;
    private Date ngaySinh;
    private String tieuSu;

    public TacGia() {}

    public TacGia(int maTG, String hoVaTen, Date ngaySinh, String tieuSu) {
        this.maTG = maTG;
        this.tenTG = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.tieuSu = tieuSu;
    }

    public int getMaTG() {
        return maTG;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setHoVaTen(String hoVaTen) {
        this.tenTG = hoVaTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTieuSu() {
        return tieuSu;
    }

    public void setTieuSu(String tieuSu) {
        this.tieuSu = tieuSu;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maTG, tenTG, ngaySinh, tieuSu);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        TacGia other = (TacGia) obj;
        return maTG == other.maTG;
    }

    @Override
    public String toString() {
        return "TacGia [maTG=" + maTG + ", hoVaTen=" + tenTG + ", ngaySinh=" + ngaySinh + ", tieuSu=" + tieuSu + "]";
    }
}
