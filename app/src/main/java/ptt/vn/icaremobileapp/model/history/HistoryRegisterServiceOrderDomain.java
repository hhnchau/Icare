package ptt.vn.icaremobileapp.model.history;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryRegisterServiceOrderDomain implements Parcelable {
    private String patid;
    private String idlink;
    private int iditem;
    private int qty;
    private long price;
    private long amount;
    private String nameunit;
    private int ishi;
    private String nameitem;
    private String fullname;
    private int siterf;

    public HistoryRegisterServiceOrderDomain() {
    }

    protected HistoryRegisterServiceOrderDomain(Parcel in) {
        patid = in.readString();
        idlink = in.readString();
        iditem = in.readInt();
        qty = in.readInt();
        price = in.readLong();
        amount = in.readLong();
        nameunit = in.readString();
        ishi = in.readInt();
        nameitem = in.readString();
        fullname = in.readString();
        siterf = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(patid);
        dest.writeString(idlink);
        dest.writeInt(iditem);
        dest.writeInt(qty);
        dest.writeLong(price);
        dest.writeLong(amount);
        dest.writeString(nameunit);
        dest.writeInt(ishi);
        dest.writeString(nameitem);
        dest.writeString(fullname);
        dest.writeInt(siterf);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HistoryRegisterServiceOrderDomain> CREATOR = new Creator<HistoryRegisterServiceOrderDomain>() {
        @Override
        public HistoryRegisterServiceOrderDomain createFromParcel(Parcel in) {
            return new HistoryRegisterServiceOrderDomain(in);
        }

        @Override
        public HistoryRegisterServiceOrderDomain[] newArray(int size) {
            return new HistoryRegisterServiceOrderDomain[size];
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

    public int getIditem() {
        return iditem;
    }

    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getNameunit() {
        return nameunit;
    }

    public void setNameunit(String nameunit) {
        this.nameunit = nameunit;
    }

    public int getIshi() {
        return ishi;
    }

    public void setIshi(int ishi) {
        this.ishi = ishi;
    }

    public String getNameitem() {
        return nameitem;
    }

    public void setNameitem(String nameitem) {
        this.nameitem = nameitem;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getSiterf() {
        return siterf;
    }

    public void setSiterf(int siterf) {
        this.siterf = siterf;
    }
}
