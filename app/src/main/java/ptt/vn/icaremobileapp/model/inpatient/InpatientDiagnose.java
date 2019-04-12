package ptt.vn.icaremobileapp.model.inpatient;

import android.os.Parcel;
import android.os.Parcelable;

public class InpatientDiagnose implements Parcelable {
    private int siterf;
    private String idline;
    private String idlink;
    private int idicd;
    private String nameicdvn;
    private String nameicdeng;
    private int type;
    private int primary;
    private String attributes;
    private int active;

    private String code;


    public InpatientDiagnose() {
    }

    protected InpatientDiagnose(Parcel in) {
        siterf = in.readInt();
        idline = in.readString();
        idlink = in.readString();
        idicd = in.readInt();
        nameicdvn = in.readString();
        nameicdeng = in.readString();
        type = in.readInt();
        primary = in.readInt();
        attributes = in.readString();
        active = in.readInt();
        code = in.readString();
    }

    public static final Creator<InpatientDiagnose> CREATOR = new Creator<InpatientDiagnose>() {
        @Override
        public InpatientDiagnose createFromParcel(Parcel in) {
            return new InpatientDiagnose(in);
        }

        @Override
        public InpatientDiagnose[] newArray(int size) {
            return new InpatientDiagnose[size];
        }
    };

    public int getSiterf() {
        return siterf;
    }

    public void setSiterf(int siterf) {
        this.siterf = siterf;
    }

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public String getIdlink() {
        return idlink;
    }

    public void setIdlink(String idlink) {
        this.idlink = idlink;
    }

    public int getIdicd() {
        return idicd;
    }

    public void setIdicd(int idicd) {
        this.idicd = idicd;
    }

    public String getNameicdvn() {
        return nameicdvn;
    }

    public void setNameicdvn(String nameicdvn) {
        this.nameicdvn = nameicdvn;
    }

    public String getNameicdeng() {
        return nameicdeng;
    }

    public void setNameicdeng(String nameicdeng) {
        this.nameicdeng = nameicdeng;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrimary() {
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeString(idline);
        dest.writeString(idlink);
        dest.writeInt(idicd);
        dest.writeString(nameicdvn);
        dest.writeString(nameicdeng);
        dest.writeInt(type);
        dest.writeInt(primary);
        dest.writeString(attributes);
        dest.writeInt(active);
        dest.writeString(code);
    }
}
