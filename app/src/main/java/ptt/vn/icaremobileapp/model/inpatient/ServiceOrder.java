package ptt.vn.icaremobileapp.model.inpatient;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceOrder implements Parcelable {
    private int siterf;
    private String idline;
    private String idhappening;
    private int idmedexa;
    private int idservice;
    private int qty;
    private float price;
    private float pricehi;
    private String docoder;
    private String dateapp;
    private int ishi;
    private String attributes;
    private int active;
    private String usercr;
    private String timecr;
    private String userup;
    private String timeup;
    private String computer;

    public ServiceOrder() {
    }

    protected ServiceOrder(Parcel in) {
        siterf = in.readInt();
        idline = in.readString();
        idhappening = in.readString();
        idmedexa = in.readInt();
        idservice = in.readInt();
        qty = in.readInt();
        price = in.readFloat();
        pricehi = in.readFloat();
        docoder = in.readString();
        dateapp = in.readString();
        ishi = in.readInt();
        attributes = in.readString();
        active = in.readInt();
        usercr = in.readString();
        timecr = in.readString();
        userup = in.readString();
        timeup = in.readString();
        computer = in.readString();
    }

    public static final Creator<ServiceOrder> CREATOR = new Creator<ServiceOrder>() {
        @Override
        public ServiceOrder createFromParcel(Parcel in) {
            return new ServiceOrder(in);
        }

        @Override
        public ServiceOrder[] newArray(int size) {
            return new ServiceOrder[size];
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

    public String getUsercr() {
        return usercr;
    }

    public void setUsercr(String usercr) {
        this.usercr = usercr;
    }

    public String getTimecr() {
        return timecr;
    }

    public void setTimecr(String timecr) {
        this.timecr = timecr;
    }

    public String getUserup() {
        return userup;
    }

    public void setUserup(String userup) {
        this.userup = userup;
    }

    public String getTimeup() {
        return timeup;
    }

    public void setTimeup(String timeup) {
        this.timeup = timeup;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeString(idline);
        dest.writeString(idhappening);
        dest.writeInt(idmedexa);
        dest.writeInt(idservice);
        dest.writeInt(qty);
        dest.writeFloat(price);
        dest.writeFloat(pricehi);
        dest.writeString(docoder);
        dest.writeString(dateapp);
        dest.writeInt(ishi);
        dest.writeString(attributes);
        dest.writeInt(active);
        dest.writeString(usercr);
        dest.writeString(timecr);
        dest.writeString(userup);
        dest.writeString(timeup);
        dest.writeString(computer);
    }
}
