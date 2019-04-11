package ptt.vn.icaremobileapp.model.pharmacy;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PhaInventoryDomain implements Parcelable {
    private String year;
    private int iddrug;
    private int idunit;
    private int idunituse;
    private int idroute;
    private int idstore;
    private float qtyt;
    private float qtyimp;
    private float qtyexp;
    private float qtyrep;
    private String mmyy;
    private String code;
    private String name;
    private String namespecificat;
    private String nameactiveingre;
    private String nameunit;
    private float price;
    private String lotnumber;
    private String expirydate;
    private int ishi;

    private List<PhaInventoryDetail> lstPHAInventoryDetail;

    public PhaInventoryDomain() {
    }

    protected PhaInventoryDomain(Parcel in) {
        year = in.readString();
        iddrug = in.readInt();
        idunit = in.readInt();
        idunituse = in.readInt();
        idroute = in.readInt();
        idstore = in.readInt();
        qtyt = in.readFloat();
        qtyimp = in.readFloat();
        qtyexp = in.readFloat();
        qtyrep = in.readFloat();
        mmyy = in.readString();
        code = in.readString();
        name = in.readString();
        namespecificat = in.readString();
        nameactiveingre = in.readString();
        nameunit = in.readString();
        price = in.readFloat();
        lotnumber = in.readString();
        expirydate = in.readString();
        ishi = in.readInt();
    }

    public static final Creator<PhaInventoryDomain> CREATOR = new Creator<PhaInventoryDomain>() {
        @Override
        public PhaInventoryDomain createFromParcel(Parcel in) {
            return new PhaInventoryDomain(in);
        }

        @Override
        public PhaInventoryDomain[] newArray(int size) {
            return new PhaInventoryDomain[size];
        }
    };

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getIddrug() {
        return iddrug;
    }

    public void setIddrug(int iddrug) {
        this.iddrug = iddrug;
    }

    public int getIdunit() {
        return idunit;
    }

    public void setIdunit(int idunit) {
        this.idunit = idunit;
    }

    public int getIdunituse() {
        return idunituse;
    }

    public void setIdunituse(int idunituse) {
        this.idunituse = idunituse;
    }

    public int getIdroute() {
        return idroute;
    }

    public void setIdroute(int idroute) {
        this.idroute = idroute;
    }

    public int getIdstore() {
        return idstore;
    }

    public void setIdstore(int idstore) {
        this.idstore = idstore;
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

    public String getMmyy() {
        return mmyy;
    }

    public void setMmyy(String mmyy) {
        this.mmyy = mmyy;
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

    public String getNameactiveingre() {
        return nameactiveingre;
    }

    public void setNameactiveingre(String nameactiveingre) {
        this.nameactiveingre = nameactiveingre;
    }

    public String getNameunit() {
        return nameunit;
    }

    public void setNameunit(String nameunit) {
        this.nameunit = nameunit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public int getIshi() {
        return ishi;
    }

    public void setIshi(int ishi) {
        this.ishi = ishi;
    }

    public List<PhaInventoryDetail> getLstPHAInventoryDetail() {
        return lstPHAInventoryDetail;
    }

    public void setLstPHAInventoryDetail(List<PhaInventoryDetail> lstPHAInventoryDetail) {
        this.lstPHAInventoryDetail = lstPHAInventoryDetail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(year);
        dest.writeInt(iddrug);
        dest.writeInt(idunit);
        dest.writeInt(idunituse);
        dest.writeInt(idroute);
        dest.writeInt(idstore);
        dest.writeFloat(qtyt);
        dest.writeFloat(qtyimp);
        dest.writeFloat(qtyexp);
        dest.writeFloat(qtyrep);
        dest.writeString(mmyy);
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(namespecificat);
        dest.writeString(nameactiveingre);
        dest.writeString(nameunit);
        dest.writeFloat(price);
        dest.writeString(lotnumber);
        dest.writeString(expirydate);
        dest.writeInt(ishi);
    }
}
