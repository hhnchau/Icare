package ptt.vn.icaremobileapp.model.hi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class HiDomain implements Parcelable {
    private String maKetQua;
    private String ghiChu;
    private String maThe;
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String diaChi;
    private String maDKBD;
    private String cqBHXH;
    private String gtTheTu;
    private String gtTheDen;
    private String maKV;
    private String ngayDu5Nam;
    private String maSoBHXH;
    private String maTheCu;
    private String maTheMoi;
    private String gtTheTuMoi;
    private String gtTheDenMoi;
    private String maDKBDMoi;
    private String tenDKBDMoi;
    private List<Object> dsLichSuKCB2018;
    private List<Object> dsLichSuKT2018;

    public HiDomain() {
    }

    protected HiDomain(Parcel in) {
        maKetQua = in.readString();
        ghiChu = in.readString();
        maThe = in.readString();
        hoTen = in.readString();
        ngaySinh = in.readString();
        gioiTinh = in.readString();
        diaChi = in.readString();
        maDKBD = in.readString();
        cqBHXH = in.readString();
        gtTheTu = in.readString();
        gtTheDen = in.readString();
        maKV = in.readString();
        ngayDu5Nam = in.readString();
        maSoBHXH = in.readString();
        maTheCu = in.readString();
        maTheMoi = in.readString();
        gtTheTuMoi = in.readString();
        gtTheDenMoi = in.readString();
        maDKBDMoi = in.readString();
        tenDKBDMoi = in.readString();
    }

    public static final Creator<HiDomain> CREATOR = new Creator<HiDomain>() {
        @Override
        public HiDomain createFromParcel(Parcel in) {
            return new HiDomain(in);
        }

        @Override
        public HiDomain[] newArray(int size) {
            return new HiDomain[size];
        }
    };

    public String getMaKetQua() {
        return maKetQua;
    }

    public void setMaKetQua(String maKetQua) {
        this.maKetQua = maKetQua;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    public String getMaDKBD() {
        return maDKBD;
    }

    public void setMaDKBD(String maDKBD) {
        this.maDKBD = maDKBD;
    }

    public String getCqBHXH() {
        return cqBHXH;
    }

    public void setCqBHXH(String cqBHXH) {
        this.cqBHXH = cqBHXH;
    }

    public String getGtTheTu() {
        return gtTheTu;
    }

    public void setGtTheTu(String gtTheTu) {
        this.gtTheTu = gtTheTu;
    }

    public String getGtTheDen() {
        return gtTheDen;
    }

    public void setGtTheDen(String gtTheDen) {
        this.gtTheDen = gtTheDen;
    }

    public String getMaKV() {
        return maKV;
    }

    public void setMaKV(String maKV) {
        this.maKV = maKV;
    }

    public String getNgayDu5Nam() {
        return ngayDu5Nam;
    }

    public void setNgayDu5Nam(String ngayDu5Nam) {
        this.ngayDu5Nam = ngayDu5Nam;
    }

    public String getMaSoBHXH() {
        return maSoBHXH;
    }

    public void setMaSoBHXH(String maSoBHXH) {
        this.maSoBHXH = maSoBHXH;
    }

    public String getMaTheCu() {
        return maTheCu;
    }

    public void setMaTheCu(String maTheCu) {
        this.maTheCu = maTheCu;
    }

    public String getMaTheMoi() {
        return maTheMoi;
    }

    public void setMaTheMoi(String maTheMoi) {
        this.maTheMoi = maTheMoi;
    }

    public String getGtTheTuMoi() {
        return gtTheTuMoi;
    }

    public void setGtTheTuMoi(String gtTheTuMoi) {
        this.gtTheTuMoi = gtTheTuMoi;
    }

    public String getGtTheDenMoi() {
        return gtTheDenMoi;
    }

    public void setGtTheDenMoi(String gtTheDenMoi) {
        this.gtTheDenMoi = gtTheDenMoi;
    }

    public String getMaDKBDMoi() {
        return maDKBDMoi;
    }

    public void setMaDKBDMoi(String maDKBDMoi) {
        this.maDKBDMoi = maDKBDMoi;
    }

    public String getTenDKBDMoi() {
        return tenDKBDMoi;
    }

    public void setTenDKBDMoi(String tenDKBDMoi) {
        this.tenDKBDMoi = tenDKBDMoi;
    }

    public List<Object> getDsLichSuKCB2018() {
        return dsLichSuKCB2018;
    }

    public void setDsLichSuKCB2018(List<Object> dsLichSuKCB2018) {
        this.dsLichSuKCB2018 = dsLichSuKCB2018;
    }

    public List<Object> getDsLichSuKT2018() {
        return dsLichSuKT2018;
    }

    public void setDsLichSuKT2018(List<Object> dsLichSuKT2018) {
        this.dsLichSuKT2018 = dsLichSuKT2018;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(maKetQua);
        dest.writeString(ghiChu);
        dest.writeString(maThe);
        dest.writeString(hoTen);
        dest.writeString(ngaySinh);
        dest.writeString(gioiTinh);
        dest.writeString(diaChi);
        dest.writeString(maDKBD);
        dest.writeString(cqBHXH);
        dest.writeString(gtTheTu);
        dest.writeString(gtTheDen);
        dest.writeString(maKV);
        dest.writeString(ngayDu5Nam);
        dest.writeString(maSoBHXH);
        dest.writeString(maTheCu);
        dest.writeString(maTheMoi);
        dest.writeString(gtTheTuMoi);
        dest.writeString(gtTheDenMoi);
        dest.writeString(maDKBDMoi);
        dest.writeString(tenDKBDMoi);
    }
}
