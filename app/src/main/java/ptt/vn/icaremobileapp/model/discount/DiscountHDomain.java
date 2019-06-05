package ptt.vn.icaremobileapp.model.discount;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DiscountHDomain implements Parcelable {
    private int idline;

    private String namediscount;

    private String condition;

    private String timestart;

    private String timeend;

    private List<DiscountLDomain> lstDiscountl;

    public DiscountHDomain() {
    }

    protected DiscountHDomain(Parcel in) {
        idline = in.readInt();
        namediscount = in.readString();
        condition = in.readString();
        timestart = in.readString();
        timeend = in.readString();
        lstDiscountl = in.createTypedArrayList(DiscountLDomain.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idline);
        dest.writeString(namediscount);
        dest.writeString(condition);
        dest.writeString(timestart);
        dest.writeString(timeend);
        dest.writeTypedList(lstDiscountl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DiscountHDomain> CREATOR = new Creator<DiscountHDomain>() {
        @Override
        public DiscountHDomain createFromParcel(Parcel in) {
            return new DiscountHDomain(in);
        }

        @Override
        public DiscountHDomain[] newArray(int size) {
            return new DiscountHDomain[size];
        }
    };

    public int getIdline() {
        return idline;
    }

    public void setIdline(int idline) {
        this.idline = idline;
    }

    public String getNamediscount() {
        return namediscount;
    }

    public void setNamediscount(String namediscount) {
        this.namediscount = namediscount;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTimestart() {
        return timestart;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }

    public List<DiscountLDomain> getLstDiscountl() {
        return lstDiscountl;
    }

    public void setLstDiscountl(List<DiscountLDomain> lstDiscountl) {
        this.lstDiscountl = lstDiscountl;
    }
}
