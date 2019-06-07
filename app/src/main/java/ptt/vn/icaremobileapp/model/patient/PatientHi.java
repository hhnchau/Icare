package ptt.vn.icaremobileapp.model.patient;

import android.os.Parcel;
import android.os.Parcelable;

public class PatientHi implements Parcelable {

    private String idline;

    private String nohi;

    private String strday;

    private String endday;

    private int idhospital;

    private String hospitalname;

    private String hospitalcode;

    private String addres;

    private int idlivpla;

    private String namelivpla;

    private String time5y;

    private String imgpth;

    private int ratepay;

    private int status;

    private String image;

    public PatientHi() {
    }

    protected PatientHi(Parcel in) {
        idline = in.readString();
        nohi = in.readString();
        strday = in.readString();
        endday = in.readString();
        idhospital = in.readInt();
        hospitalname = in.readString();
        hospitalcode = in.readString();
        addres = in.readString();
        idlivpla = in.readInt();
        namelivpla = in.readString();
        time5y = in.readString();
        imgpth = in.readString();
        ratepay = in.readInt();
        status = in.readInt();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idline);
        dest.writeString(nohi);
        dest.writeString(strday);
        dest.writeString(endday);
        dest.writeInt(idhospital);
        dest.writeString(hospitalname);
        dest.writeString(hospitalcode);
        dest.writeString(addres);
        dest.writeInt(idlivpla);
        dest.writeString(namelivpla);
        dest.writeString(time5y);
        dest.writeString(imgpth);
        dest.writeInt(ratepay);
        dest.writeInt(status);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PatientHi> CREATOR = new Creator<PatientHi>() {
        @Override
        public PatientHi createFromParcel(Parcel in) {
            return new PatientHi(in);
        }

        @Override
        public PatientHi[] newArray(int size) {
            return new PatientHi[size];
        }
    };

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public String getNohi() {
        return nohi;
    }

    public void setNohi(String nohi) {
        this.nohi = nohi;
    }

    public String getStrday() {
        return strday;
    }

    public void setStrday(String strday) {
        this.strday = strday;
    }

    public String getEndday() {
        return endday;
    }

    public void setEndday(String endday) {
        this.endday = endday;
    }

    public int getIdhospital() {
        return idhospital;
    }

    public void setIdhospital(int idhospital) {
        this.idhospital = idhospital;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getHospitalcode() {
        return hospitalcode;
    }

    public void setHospitalcode(String hospitalcode) {
        this.hospitalcode = hospitalcode;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public int getIdlivpla() {
        return idlivpla;
    }

    public void setIdlivpla(int idlivpla) {
        this.idlivpla = idlivpla;
    }

    public String getNamelivpla() {
        return namelivpla;
    }

    public void setNamelivpla(String namelivpla) {
        this.namelivpla = namelivpla;
    }

    public String getTime5y() {
        return time5y;
    }

    public void setTime5y(String time5y) {
        this.time5y = time5y;
    }

    public String getImgpth() {
        return imgpth;
    }

    public void setImgpth(String imgpth) {
        this.imgpth = imgpth;
    }

    public int getRatepay() {
        return ratepay;
    }

    public void setRatepay(int ratepay) {
        this.ratepay = ratepay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
