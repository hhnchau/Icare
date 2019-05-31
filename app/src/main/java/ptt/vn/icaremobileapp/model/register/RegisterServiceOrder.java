package ptt.vn.icaremobileapp.model.register;

import android.os.Parcel;
import android.os.Parcelable;

public class RegisterServiceOrder implements Parcelable {
    private String patid;
    private String idline;
    private String dateorder;
    private int idservitem;
    private String servcode;
    private String servname;
    private String nameunit;
    private int qty;
    private long price;
    private long pricehi;
    private long pricebhi;
    private long amount;
    private String docoder;
    private String dateapp;
    private int idmedexa;
    private int ispay;
    private int ishi;
    private int qtydone;
    private int status;
    private int idpriob;
    private String namehosp;

    public RegisterServiceOrder() {
    }

    protected RegisterServiceOrder(Parcel in) {
        patid = in.readString();
        idline = in.readString();
        dateorder = in.readString();
        idservitem = in.readInt();
        servcode = in.readString();
        servname = in.readString();
        nameunit = in.readString();
        qty = in.readInt();
        price = in.readLong();
        pricehi = in.readLong();
        pricebhi = in.readLong();
        amount = in.readLong();
        docoder = in.readString();
        dateapp = in.readString();
        idmedexa = in.readInt();
        ispay = in.readInt();
        ishi = in.readInt();
        qtydone = in.readInt();
        status = in.readInt();
        idpriob = in.readInt();
        namehosp = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(patid);
        dest.writeString(idline);
        dest.writeString(dateorder);
        dest.writeInt(idservitem);
        dest.writeString(servcode);
        dest.writeString(servname);
        dest.writeString(nameunit);
        dest.writeInt(qty);
        dest.writeLong(price);
        dest.writeLong(pricehi);
        dest.writeLong(pricebhi);
        dest.writeLong(amount);
        dest.writeString(docoder);
        dest.writeString(dateapp);
        dest.writeInt(idmedexa);
        dest.writeInt(ispay);
        dest.writeInt(ishi);
        dest.writeInt(qtydone);
        dest.writeInt(status);
        dest.writeInt(idpriob);
        dest.writeString(namehosp);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegisterServiceOrder> CREATOR = new Creator<RegisterServiceOrder>() {
        @Override
        public RegisterServiceOrder createFromParcel(Parcel in) {
            return new RegisterServiceOrder(in);
        }

        @Override
        public RegisterServiceOrder[] newArray(int size) {
            return new RegisterServiceOrder[size];
        }
    };

    public String getPatid() {
        return patid;
    }

    public void setPatid(String patid) {
        this.patid = patid;
    }

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public String getDateorder() {
        return dateorder;
    }

    public void setDateorder(String dateorder) {
        this.dateorder = dateorder;
    }

    public int getIdservitem() {
        return idservitem;
    }

    public void setIdservitem(int idservitem) {
        this.idservitem = idservitem;
    }

    public String getServcode() {
        return servcode;
    }

    public void setServcode(String servcode) {
        this.servcode = servcode;
    }

    public String getServname() {
        return servname;
    }

    public void setServname(String servname) {
        this.servname = servname;
    }

    public String getNameunit() {
        return nameunit;
    }

    public void setNameunit(String nameunit) {
        this.nameunit = nameunit;
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
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

    public int getIdmedexa() {
        return idmedexa;
    }

    public void setIdmedexa(int idmedexa) {
        this.idmedexa = idmedexa;
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

    public int getIdpriob() {
        return idpriob;
    }

    public void setIdpriob(int idpriob) {
        this.idpriob = idpriob;
    }

    public String getNamehosp() {
        return namehosp;
    }

    public void setNamehosp(String namehosp) {
        this.namehosp = namehosp;
    }
}
