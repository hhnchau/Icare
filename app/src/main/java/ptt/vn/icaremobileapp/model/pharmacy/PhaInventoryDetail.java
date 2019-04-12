package ptt.vn.icaremobileapp.model.pharmacy;

import android.os.Parcel;
import android.os.Parcelable;

public class PhaInventoryDetail implements Parcelable {
    private String year;
    private int idstore;
    private String idfollow;
    private float qtyt;
    private float qtyimp;
    private float qtyexp;
    private float qtyrep;
    private float qty;
    private String mmyy;
    private int iddrug;
    private int idspecificat;
    private String lotnumber;
    private String expirydate;
    private float price;
    private float pricecost;
    private float priceold;
    private float total;
    private int vat;
    private int discount;
    private String code;
    private String name;
    private String namespecificat;
    private String nameunit;

    public PhaInventoryDetail() {
    }

    protected PhaInventoryDetail(Parcel in) {
        year = in.readString();
        idstore = in.readInt();
        idfollow = in.readString();
        qtyt = in.readFloat();
        qtyimp = in.readFloat();
        qtyexp = in.readFloat();
        qtyrep = in.readFloat();
        qty = in.readFloat();
        mmyy = in.readString();
        iddrug = in.readInt();
        idspecificat = in.readInt();
        lotnumber = in.readString();
        expirydate = in.readString();
        price = in.readFloat();
        pricecost = in.readFloat();
        priceold = in.readFloat();
        total = in.readFloat();
        vat = in.readInt();
        discount = in.readInt();
        code = in.readString();
        name = in.readString();
        namespecificat = in.readString();
        nameunit = in.readString();
    }

    public static final Creator<PhaInventoryDetail> CREATOR = new Creator<PhaInventoryDetail>() {
        @Override
        public PhaInventoryDetail createFromParcel(Parcel in) {
            return new PhaInventoryDetail(in);
        }

        @Override
        public PhaInventoryDetail[] newArray(int size) {
            return new PhaInventoryDetail[size];
        }
    };

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getIdstore() {
        return idstore;
    }

    public void setIdstore(int idstore) {
        this.idstore = idstore;
    }

    public String getIdfollow() {
        return idfollow;
    }

    public void setIdfollow(String idfollow) {
        this.idfollow = idfollow;
    }

    public float getQtyt() {
        return qtyt;
    }

    public void setQtyt(float qtyt) {
        this.qtyt = qtyt;
    }

    public float getQtyimp() {
        return qtyimp;
    }

    public void setQtyimp(float qtyimp) {
        this.qtyimp = qtyimp;
    }

    public float getQtyexp() {
        return qtyexp;
    }

    public void setQtyexp(float qtyexp) {
        this.qtyexp = qtyexp;
    }

    public float getQtyrep() {
        return qtyrep;
    }

    public void setQtyrep(float qtyrep) {
        this.qtyrep = qtyrep;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public String getMmyy() {
        return mmyy;
    }

    public void setMmyy(String mmyy) {
        this.mmyy = mmyy;
    }

    public int getIddrug() {
        return iddrug;
    }

    public void setIddrug(int iddrug) {
        this.iddrug = iddrug;
    }

    public int getIdspecificat() {
        return idspecificat;
    }

    public void setIdspecificat(int idspecificat) {
        this.idspecificat = idspecificat;
    }

    public String getLotnumber() {
        return lotnumber;
    }

    public void setLotnumber(String lotnumber) {
        this.lotnumber = lotnumber;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPricecost() {
        return pricecost;
    }

    public void setPricecost(float pricecost) {
        this.pricecost = pricecost;
    }

    public float getPriceold() {
        return priceold;
    }

    public void setPriceold(float priceold) {
        this.priceold = priceold;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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

    public String getNamespecificat() {
        return namespecificat;
    }

    public void setNamespecificat(String namespecificat) {
        this.namespecificat = namespecificat;
    }

    public String getNameunit() {
        return nameunit;
    }

    public void setNameunit(String nameunit) {
        this.nameunit = nameunit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(year);
        dest.writeInt(idstore);
        dest.writeString(idfollow);
        dest.writeFloat(qtyt);
        dest.writeFloat(qtyimp);
        dest.writeFloat(qtyexp);
        dest.writeFloat(qtyrep);
        dest.writeFloat(qty);
        dest.writeString(mmyy);
        dest.writeInt(iddrug);
        dest.writeInt(idspecificat);
        dest.writeString(lotnumber);
        dest.writeString(expirydate);
        dest.writeFloat(price);
        dest.writeFloat(pricecost);
        dest.writeFloat(priceold);
        dest.writeFloat(total);
        dest.writeInt(vat);
        dest.writeInt(discount);
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(namespecificat);
        dest.writeString(nameunit);
    }
}
