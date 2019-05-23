package ptt.vn.icaremobileapp.model.serviceitem;

import android.os.Parcel;
import android.os.Parcelable;

public class MapPriceServiceItemLDomain implements Parcelable {
    private int siterf;
    private int idprice;
    private int idservice;
    private String code;
    private String namehi;
    private String namehosp;
    private int unitid;
    private long price;
    private long pricehi;
    private long difference;
    private String descrp;
    private int active;

    public MapPriceServiceItemLDomain() {
    }

    protected MapPriceServiceItemLDomain(Parcel in) {
        siterf = in.readInt();
        idprice = in.readInt();
        idservice = in.readInt();
        code = in.readString();
        namehi = in.readString();
        namehosp = in.readString();
        unitid = in.readInt();
        price = in.readLong();
        pricehi = in.readLong();
        difference = in.readLong();
        descrp = in.readString();
        active = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeInt(idprice);
        dest.writeInt(idservice);
        dest.writeString(code);
        dest.writeString(namehi);
        dest.writeString(namehosp);
        dest.writeInt(unitid);
        dest.writeLong(price);
        dest.writeLong(pricehi);
        dest.writeLong(difference);
        dest.writeString(descrp);
        dest.writeInt(active);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MapPriceServiceItemLDomain> CREATOR = new Creator<MapPriceServiceItemLDomain>() {
        @Override
        public MapPriceServiceItemLDomain createFromParcel(Parcel in) {
            return new MapPriceServiceItemLDomain(in);
        }

        @Override
        public MapPriceServiceItemLDomain[] newArray(int size) {
            return new MapPriceServiceItemLDomain[size];
        }
    };

    public int getSiterf() {
        return siterf;
    }

    public void setSiterf(int siterf) {
        this.siterf = siterf;
    }

    public int getIdprice() {
        return idprice;
    }

    public void setIdprice(int idprice) {
        this.idprice = idprice;
    }

    public int getIdservice() {
        return idservice;
    }

    public void setIdservice(int idservice) {
        this.idservice = idservice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNamehi() {
        return namehi;
    }

    public void setNamehi(String namehi) {
        this.namehi = namehi;
    }

    public String getNamehosp() {
        return namehosp;
    }

    public void setNamehosp(String namehosp) {
        this.namehosp = namehosp;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPricehi() {
        return pricehi;
    }

    public void setPricehi(long pricehi) {
        this.pricehi = pricehi;
    }

    public long getDifference() {
        return difference;
    }

    public void setDifference(long difference) {
        this.difference = difference;
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
}
