package ptt.vn.icaremobileapp.model.outclinic;

import android.os.Parcel;
import android.os.Parcelable;

public class OutServiceOrderDomain implements Parcelable {
    private String idline;
    private int idmedexa;
    private int idservice;
    private String codeservice;
    private int unit;
    private String nameunit;
    private String dateorder;
    private String ServiceName;
    private int qty;
    private long price;
    private long pricehi;
    private long pricebhi;
    private long serpayrate;
    private long amount;
    private long difference;
    private String docoder;
    private String dateapp;
    private int ispay;
    private int ishi;
    private int qtydone;
    private int status;
    private int active;
    private int sort;

    public OutServiceOrderDomain() {
    }

    protected OutServiceOrderDomain(Parcel in) {
        idline = in.readString();
        idmedexa = in.readInt();
        idservice = in.readInt();
        codeservice = in.readString();
        unit = in.readInt();
        nameunit = in.readString();
        dateorder = in.readString();
        ServiceName = in.readString();
        qty = in.readInt();
        price = in.readLong();
        pricehi = in.readLong();
        pricebhi = in.readLong();
        serpayrate = in.readLong();
        amount = in.readLong();
        difference = in.readLong();
        docoder = in.readString();
        dateapp = in.readString();
        ispay = in.readInt();
        ishi = in.readInt();
        qtydone = in.readInt();
        status = in.readInt();
        active = in.readInt();
        sort = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idline);
        dest.writeInt(idmedexa);
        dest.writeInt(idservice);
        dest.writeString(codeservice);
        dest.writeInt(unit);
        dest.writeString(nameunit);
        dest.writeString(dateorder);
        dest.writeString(ServiceName);
        dest.writeInt(qty);
        dest.writeLong(price);
        dest.writeLong(pricehi);
        dest.writeLong(pricebhi);
        dest.writeLong(serpayrate);
        dest.writeLong(amount);
        dest.writeLong(difference);
        dest.writeString(docoder);
        dest.writeString(dateapp);
        dest.writeInt(ispay);
        dest.writeInt(ishi);
        dest.writeInt(qtydone);
        dest.writeInt(status);
        dest.writeInt(active);
        dest.writeInt(sort);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OutServiceOrderDomain> CREATOR = new Creator<OutServiceOrderDomain>() {
        @Override
        public OutServiceOrderDomain createFromParcel(Parcel in) {
            return new OutServiceOrderDomain(in);
        }

        @Override
        public OutServiceOrderDomain[] newArray(int size) {
            return new OutServiceOrderDomain[size];
        }
    };

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
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

    public String getCodeservice() {
        return codeservice;
    }

    public void setCodeservice(String codeservice) {
        this.codeservice = codeservice;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getNameunit() {
        return nameunit;
    }

    public void setNameunit(String nameunit) {
        this.nameunit = nameunit;
    }

    public String getDateorder() {
        return dateorder;
    }

    public void setDateorder(String dateorder) {
        this.dateorder = dateorder;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String nameservice) {
        this.ServiceName = nameservice;
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

    public long getPricebhi() {
        return pricebhi;
    }

    public void setPricebhi(long pricebhi) {
        this.pricebhi = pricebhi;
    }

    public long getSerpayrate() {
        return serpayrate;
    }

    public void setSerpayrate(long serpayrate) {
        this.serpayrate = serpayrate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getDifference() {
        return difference;
    }

    public void setDifference(long difference) {
        this.difference = difference;
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

    public int getIspay() {
        return ispay;
    }

    public void setIspay(int ispay) {
        this.ispay = ispay;
    }

    public int getIshi() {
        return ishi;
    }

    public void setIshi(int ishi) {
        this.ishi = ishi;
    }

    public int getQtydone() {
        return qtydone;
    }

    public void setQtydone(int qtydone) {
        this.qtydone = qtydone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
