package ptt.vn.icaremobileapp.model.patient;

import android.os.Parcel;
import android.os.Parcelable;

public class PatientBHi implements Parcelable {
    private String idline;
    private String nobhi;
    private String strday;
    private String endday;
    private int idcombhi;
    private String combhiname;
    private int idcom;
    private String comname;
    private String addres;
    private String phone;
    private int status;
    private String image;

    public PatientBHi() {
    }


    protected PatientBHi(Parcel in) {
        idline = in.readString();
        nobhi = in.readString();
        strday = in.readString();
        endday = in.readString();
        idcombhi = in.readInt();
        combhiname = in.readString();
        idcom = in.readInt();
        comname = in.readString();
        addres = in.readString();
        phone = in.readString();
        status = in.readInt();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idline);
        dest.writeString(nobhi);
        dest.writeString(strday);
        dest.writeString(endday);
        dest.writeInt(idcombhi);
        dest.writeString(combhiname);
        dest.writeInt(idcom);
        dest.writeString(comname);
        dest.writeString(addres);
        dest.writeString(phone);
        dest.writeInt(status);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PatientBHi> CREATOR = new Creator<PatientBHi>() {
        @Override
        public PatientBHi createFromParcel(Parcel in) {
            return new PatientBHi(in);
        }

        @Override
        public PatientBHi[] newArray(int size) {
            return new PatientBHi[size];
        }
    };

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public String getNobhi() {
        return nobhi;
    }

    public void setNobhi(String nobhi) {
        this.nobhi = nobhi;
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

    public int getIdcombhi() {
        return idcombhi;
    }

    public void setIdcombhi(int idcombhi) {
        this.idcombhi = idcombhi;
    }

    public String getCombhiname() {
        return combhiname;
    }

    public void setCombhiname(String combhiname) {
        this.combhiname = combhiname;
    }

    public int getIdcom() {
        return idcom;
    }

    public void setIdcom(int idcom) {
        this.idcom = idcom;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
