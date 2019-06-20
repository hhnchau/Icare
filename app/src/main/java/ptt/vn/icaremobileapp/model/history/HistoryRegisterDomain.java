package ptt.vn.icaremobileapp.model.history;

import android.os.Parcel;
import android.os.Parcelable;

import ptt.vn.icaremobileapp.model.Parent;

public class HistoryRegisterDomain extends Parent<HistoryRegisterServiceOrderDomain> implements Parcelable {
    private String patid;
    private String idlink;
    private String codeicdx;
    private String nameicdx;
    private String regdate;
    private int idobject;
    private String name;
    private int promotions;
    private String namediscount;
    private String hosnum;
    private int siterf;

    public HistoryRegisterDomain() {
    }

    protected HistoryRegisterDomain(Parcel in) {
        patid = in.readString();
        idlink = in.readString();
        codeicdx = in.readString();
        nameicdx = in.readString();
        regdate = in.readString();
        idobject = in.readInt();
        name = in.readString();
        promotions = in.readInt();
        namediscount = in.readString();
        hosnum = in.readString();
        siterf = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(patid);
        dest.writeString(idlink);
        dest.writeString(codeicdx);
        dest.writeString(nameicdx);
        dest.writeString(regdate);
        dest.writeInt(idobject);
        dest.writeString(name);
        dest.writeInt(promotions);
        dest.writeString(namediscount);
        dest.writeString(hosnum);
        dest.writeInt(siterf);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HistoryRegisterDomain> CREATOR = new Creator<HistoryRegisterDomain>() {
        @Override
        public HistoryRegisterDomain createFromParcel(Parcel in) {
            return new HistoryRegisterDomain(in);
        }

        @Override
        public HistoryRegisterDomain[] newArray(int size) {
            return new HistoryRegisterDomain[size];
        }
    };

    public String getPatid() {
        return patid;
    }

    public void setPatid(String patid) {
        this.patid = patid;
    }

    public String getIdlink() {
        return idlink;
    }

    public void setIdlink(String idlink) {
        this.idlink = idlink;
    }

    public String getCodeicdx() {
        return codeicdx;
    }

    public void setCodeicdx(String codeicdx) {
        this.codeicdx = codeicdx;
    }

    public String getNameicdx() {
        return nameicdx;
    }

    public void setNameicdx(String nameicdx) {
        this.nameicdx = nameicdx;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public int getIdobject() {
        return idobject;
    }

    public void setIdobject(int idobject) {
        this.idobject = idobject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPromotions() {
        return promotions;
    }

    public void setPromotions(int promotions) {
        this.promotions = promotions;
    }

    public String getNamediscount() {
        return namediscount;
    }

    public void setNamediscount(String namediscount) {
        this.namediscount = namediscount;
    }

    public String getHosnum() {
        return hosnum;
    }

    public void setHosnum(String hosnum) {
        this.hosnum = hosnum;
    }

    public int getSiterf() {
        return siterf;
    }

    public void setSiterf(int siterf) {
        this.siterf = siterf;
    }
}
