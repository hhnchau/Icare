package ptt.vn.icaremobileapp.model.hi;

import android.os.Parcel;
import android.os.Parcelable;

public class HiRatioOtherDomain implements Parcelable {
    private int idline;
    private int idh;
    private String code;
    private String name;
    private int rateother;
    private String decrp;
    private int sort;
    private int active;

    public HiRatioOtherDomain() {
    }

    protected HiRatioOtherDomain(Parcel in) {
        idline = in.readInt();
        idh = in.readInt();
        code = in.readString();
        name = in.readString();
        rateother = in.readInt();
        decrp = in.readString();
        sort = in.readInt();
        active = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idline);
        dest.writeInt(idh);
        dest.writeString(code);
        dest.writeString(name);
        dest.writeInt(rateother);
        dest.writeString(decrp);
        dest.writeInt(sort);
        dest.writeInt(active);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HiRatioOtherDomain> CREATOR = new Creator<HiRatioOtherDomain>() {
        @Override
        public HiRatioOtherDomain createFromParcel(Parcel in) {
            return new HiRatioOtherDomain(in);
        }

        @Override
        public HiRatioOtherDomain[] newArray(int size) {
            return new HiRatioOtherDomain[size];
        }
    };

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRateother() {
        return rateother;
    }

    public void setRateother(int rateother) {
        this.rateother = rateother;
    }

    public String getDecrp() {
        return decrp;
    }

    public void setDecrp(String decrp) {
        this.decrp = decrp;
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
}
