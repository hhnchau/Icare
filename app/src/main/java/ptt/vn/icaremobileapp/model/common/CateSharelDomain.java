package ptt.vn.icaremobileapp.model.common;

import android.os.Parcel;
import android.os.Parcelable;

public class CateSharelDomain implements Parcelable {
    private int siterf;
    private int idline;
    private int idh;
    private String code;
    private String codeline;
    private String name;
    private int parent;
    private int sort;
    private int active;
    private String decrp1;
    private String decrp2;
    private String decrp3;
    private String decrp4;
    private String decrp5;
    private String usercr;

    public CateSharelDomain() {
    }

    protected CateSharelDomain(Parcel in) {
        siterf = in.readInt();
        idline = in.readInt();
        idh = in.readInt();
        code = in.readString();
        codeline = in.readString();
        name = in.readString();
        parent = in.readInt();
        sort = in.readInt();
        active = in.readInt();
        decrp1 = in.readString();
        decrp2 = in.readString();
        decrp3 = in.readString();
        decrp4 = in.readString();
        decrp5 = in.readString();
        usercr = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeInt(idline);
        dest.writeInt(idh);
        dest.writeString(code);
        dest.writeString(codeline);
        dest.writeString(name);
        dest.writeInt(parent);
        dest.writeInt(sort);
        dest.writeInt(active);
        dest.writeString(decrp1);
        dest.writeString(decrp2);
        dest.writeString(decrp3);
        dest.writeString(decrp4);
        dest.writeString(decrp5);
        dest.writeString(usercr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CateSharelDomain> CREATOR = new Creator<CateSharelDomain>() {
        @Override
        public CateSharelDomain createFromParcel(Parcel in) {
            return new CateSharelDomain(in);
        }

        @Override
        public CateSharelDomain[] newArray(int size) {
            return new CateSharelDomain[size];
        }
    };

    public int getSiterf() {
        return siterf;
    }

    public void setSiterf(int siterf) {
        this.siterf = siterf;
    }

    public int getIdline() {
        return idline;
    }

    public void setIdline(int idline) {
        this.idline = idline;
    }

    public int getIdh() {
        return idh;
    }

    public void setIdh(int idh) {
        this.idh = idh;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeline() {
        return codeline;
    }

    public void setCodeline(String codeline) {
        this.codeline = codeline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDecrp1() {
        return decrp1;
    }

    public void setDecrp1(String decrp1) {
        this.decrp1 = decrp1;
    }

    public String getDecrp2() {
        return decrp2;
    }

    public void setDecrp2(String decrp2) {
        this.decrp2 = decrp2;
    }

    public String getDecrp3() {
        return decrp3;
    }

    public void setDecrp3(String decrp3) {
        this.decrp3 = decrp3;
    }

    public String getDecrp4() {
        return decrp4;
    }

    public void setDecrp4(String decrp4) {
        this.decrp4 = decrp4;
    }

    public String getDecrp5() {
        return decrp5;
    }

    public void setDecrp5(String decrp5) {
        this.decrp5 = decrp5;
    }

    public String getUsercr() {
        return usercr;
    }

    public void setUsercr(String usercr) {
        this.usercr = usercr;
    }
}
