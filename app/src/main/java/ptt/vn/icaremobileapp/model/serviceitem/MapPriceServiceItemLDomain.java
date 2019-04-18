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
    private float price;
    private float pricehi;
    private float difference;
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
        price = in.readFloat();
        pricehi = in.readFloat();
        difference = in.readFloat();
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
        dest.writeFloat(price);
        dest.writeFloat(pricehi);
        dest.writeFloat(difference);
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPricehi() {
        return pricehi;
    }

    public void setPricehi(float pricehi) {
        this.pricehi = pricehi;
    }

    public float getDifference() {
        return difference;
    }

    public void setDifference(float difference) {
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
