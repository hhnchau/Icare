package ptt.vn.icaremobileapp.model.discount;

import android.os.Parcel;
import android.os.Parcelable;

public class DiscountLDomain implements Parcelable {
    private int idline;

    private int idh;

    private int idservice;

    private long discountcash;

    private int discountrate;

    private int idregobject;

    public DiscountLDomain() {
    }

    protected DiscountLDomain(Parcel in) {
        idline = in.readInt();
        idh = in.readInt();
        idservice = in.readInt();
        discountcash = in.readLong();
        discountrate = in.readInt();
        idregobject = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idline);
        dest.writeInt(idh);
        dest.writeInt(idservice);
        dest.writeLong(discountcash);
        dest.writeInt(discountrate);
        dest.writeInt(idregobject);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DiscountLDomain> CREATOR = new Creator<DiscountLDomain>() {
        @Override
        public DiscountLDomain createFromParcel(Parcel in) {
            return new DiscountLDomain(in);
        }

        @Override
        public DiscountLDomain[] newArray(int size) {
            return new DiscountLDomain[size];
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

    public int getIdservice() {
        return idservice;
    }

    public void setIdservice(int idservice) {
        this.idservice = idservice;
    }

    public long getDiscountcash() {
        return discountcash;
    }

    public void setDiscountcash(long discountcash) {
        this.discountcash = discountcash;
    }

    public int getDiscountrate() {
        return discountrate;
    }

    public void setDiscountrate(int discountrate) {
        this.discountrate = discountrate;
    }

    public int getIdregobject() {
        return idregobject;
    }

    public void setIdregobject(int idregobject) {
        this.idregobject = idregobject;
    }
}
