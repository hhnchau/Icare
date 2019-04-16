package ptt.vn.icaremobileapp.model.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CateSharehDomain implements Parcelable {
    private int siterf;
    private int id;
    private String code;
    private String name;
    private String descrp;
    private int active;
    private List<CateSharelDomain> lstCateShareDetail;

    public CateSharehDomain() {
    }

    protected CateSharehDomain(Parcel in) {
        siterf = in.readInt();
        id = in.readInt();
        code = in.readString();
        name = in.readString();
        descrp = in.readString();
        active = in.readInt();
        lstCateShareDetail = in.createTypedArrayList(CateSharelDomain.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeInt(id);
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(descrp);
        dest.writeInt(active);
        dest.writeTypedList(lstCateShareDetail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CateSharehDomain> CREATOR = new Creator<CateSharehDomain>() {
        @Override
        public CateSharehDomain createFromParcel(Parcel in) {
            return new CateSharehDomain(in);
        }

        @Override
        public CateSharehDomain[] newArray(int size) {
            return new CateSharehDomain[size];
        }
    };

    public int getSiterf() {
        return siterf;
    }

    public void setSiterf(int siterf) {
        this.siterf = siterf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrp() {
        return descrp;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<CateSharelDomain> getLstCateShareDetail() {
        return lstCateShareDetail;
    }

    public void setLstCateShareDetail(List<CateSharelDomain> lstCateShareDetail) {
        this.lstCateShareDetail = lstCateShareDetail;
    }
}
