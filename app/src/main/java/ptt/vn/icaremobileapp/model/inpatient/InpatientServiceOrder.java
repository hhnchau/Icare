package ptt.vn.icaremobileapp.model.inpatient;

import android.os.Parcel;
import android.os.Parcelable;

public class InpatientServiceOrder implements Parcelable {
    private int siterf;
    private String idline;
    private String idhappening;
    private int idmedexa;
    private int idservice;
    private int qty;
    private long price;
    private long pricehi;
    private String docoder;
    private String dateapp;
    private int ishi;
    private String attributes;
    private int active;

    private String namehosp;
    private String code;
    private int unitid;
    private String nameunit;
    private String descrp;
    private int status;
    private String namehi;


    public InpatientServiceOrder() {
    }

    protected InpatientServiceOrder(Parcel in) {
        siterf = in.readInt();
        idline = in.readString();
        idhappening = in.readString();
        idmedexa = in.readInt();
        idservice = in.readInt();
        qty = in.readInt();
        price = in.readLong();
        pricehi = in.readLong();
        docoder = in.readString();
        dateapp = in.readString();
        ishi = in.readInt();
        attributes = in.readString();
        active = in.readInt();
        namehosp = in.readString();
        code = in.readString();
        unitid = in.readInt();
        nameunit = in.readString();
        descrp = in.readString();
        status = in.readInt();
        namehi = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeString(idline);
        dest.writeString(idhappening);
        dest.writeInt(idmedexa);
        dest.writeInt(idservice);
        dest.writeInt(qty);
        dest.writeLong(price);
        dest.writeLong(pricehi);
        dest.writeString(docoder);
        dest.writeString(dateapp);
        dest.writeInt(ishi);
        dest.writeString(attributes);
        dest.writeInt(active);
        dest.writeString(namehosp);
        dest.writeString(code);
        dest.writeInt(unitid);
        dest.writeString(nameunit);
        dest.writeString(descrp);
        dest.writeInt(status);
        dest.writeString(namehi);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InpatientServiceOrder> CREATOR = new Creator<InpatientServiceOrder>() {
        @Override
        public InpatientServiceOrder createFromParcel(Parcel in) {
            return new InpatientServiceOrder(in);
        }

        @Override
        public InpatientServiceOrder[] newArray(int size) {
            return new InpatientServiceOrder[size];
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

    public String getIdhappening() {
        return idhappening;
    }

    public void setIdhappening(String idhappening) {
        this.idhappening = idhappening;
    }

    public int getIdmedexa() {
        return idmedexa;
    }

    public void setIdmedexa(int idmedexa) {
        this.idmedexa = idmedexa;
    }

    public int getIdservice() {
        return idservice;
    }

    public void setIdservice(int idservice) {
        this.idservice = idservice;
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

    public long getPricehi() {
        return pricehi;
    }

    public void setPricehi(long pricehi) {
        this.pricehi = pricehi;
    }

    public String getDocoder() {
        return docoder;
    }

    public void setDocoder(String docoder) {
        this.docoder = docoder;
    }

    public String getDateapp() {
        return dateapp;
    }

    public void setDateapp(String dateapp) {
        this.dateapp = dateapp;
    }

    public int getIshi() {
        return ishi;
    }

    public void setIshi(int ishi) {
        this.ishi = ishi;
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

    public String getNamehosp() {
        return namehosp;
    }

    public void setNamehosp(String namehosp) {
        this.namehosp = namehosp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public String getNameunit() {
        return nameunit;
    }

    public void setNameunit(String nameunit) {
        this.nameunit = nameunit;
    }

    public String getDescrp() {
        return descrp;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNamehi() {
        return namehi;
    }

    public void setNamehi(String namehi) {
        this.namehi = namehi;
    }
}
