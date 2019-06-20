package ptt.vn.icaremobileapp.model.outclinic;

import android.os.Parcel;
import android.os.Parcelable;

public class OutDrugOrderDomain implements Parcelable {
    private String idline;
    private String mmyy;
    private String year;
    private int iddrug;
    private int idmedexa;
    private int iddoctor;
    private String DrugName;
    private int idstore;
    private float qty;
    private float price;
    private float total;
    private String usage;
    private String desc;
    private String qtymor;
    private String qtydin;
    private String qtyaft;
    private String qtynig;
    private int qtyday;
    private int idroute;
    private int idunituse;
    private int type;
    private int active;
    private int idunit;
    private String nameunit;
    private int insurance;
    private boolean nocheck;
    private int sort;

    public OutDrugOrderDomain() {
    }

    protected OutDrugOrderDomain(Parcel in) {
        idline = in.readString();
        mmyy = in.readString();
        year = in.readString();
        iddrug = in.readInt();
        idmedexa = in.readInt();
        iddoctor = in.readInt();
        DrugName = in.readString();
        idstore = in.readInt();
        qty = in.readFloat();
        price = in.readFloat();
        total = in.readFloat();
        usage = in.readString();
        desc = in.readString();
        qtymor = in.readString();
        qtydin = in.readString();
        qtyaft = in.readString();
        qtynig = in.readString();
        qtyday = in.readInt();
        idroute = in.readInt();
        idunituse = in.readInt();
        type = in.readInt();
        active = in.readInt();
        idunit = in.readInt();
        nameunit = in.readString();
        insurance = in.readInt();
        nocheck = in.readByte() != 0;
        sort = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idline);
        dest.writeString(mmyy);
        dest.writeString(year);
        dest.writeInt(iddrug);
        dest.writeInt(idmedexa);
        dest.writeInt(iddoctor);
        dest.writeString(DrugName);
        dest.writeInt(idstore);
        dest.writeFloat(qty);
        dest.writeFloat(price);
        dest.writeFloat(total);
        dest.writeString(usage);
        dest.writeString(desc);
        dest.writeString(qtymor);
        dest.writeString(qtydin);
        dest.writeString(qtyaft);
        dest.writeString(qtynig);
        dest.writeInt(qtyday);
        dest.writeInt(idroute);
        dest.writeInt(idunituse);
        dest.writeInt(type);
        dest.writeInt(active);
        dest.writeInt(idunit);
        dest.writeString(nameunit);
        dest.writeInt(insurance);
        dest.writeByte((byte) (nocheck ? 1 : 0));
        dest.writeInt(sort);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OutDrugOrderDomain> CREATOR = new Creator<OutDrugOrderDomain>() {
        @Override
        public OutDrugOrderDomain createFromParcel(Parcel in) {
            return new OutDrugOrderDomain(in);
        }

        @Override
        public OutDrugOrderDomain[] newArray(int size) {
            return new OutDrugOrderDomain[size];
        }
    };

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public String getMmyy() {
        return mmyy;
    }

    public void setMmyy(String mmyy) {
        this.mmyy = mmyy;
    }

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

    public int getIdmedexa() {
        return idmedexa;
    }

    public void setIdmedexa(int idmedexa) {
        this.idmedexa = idmedexa;
    }

    public int getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(int iddoctor) {
        this.iddoctor = iddoctor;
    }

    public String getDrugName() {
        return DrugName;
    }

    public void setDrugName(String namedrug) {
        this.DrugName = namedrug;
    }

    public int getIdstore() {
        return idstore;
    }

    public void setIdstore(int idstore) {
        this.idstore = idstore;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getQtymor() {
        return qtymor;
    }

    public void setQtymor(String qtymor) {
        this.qtymor = qtymor;
    }

    public String getQtydin() {
        return qtydin;
    }

    public void setQtydin(String qtydin) {
        this.qtydin = qtydin;
    }

    public String getQtyaft() {
        return qtyaft;
    }

    public void setQtyaft(String qtyaft) {
        this.qtyaft = qtyaft;
    }

    public String getQtynig() {
        return qtynig;
    }

    public void setQtynig(String qtynig) {
        this.qtynig = qtynig;
    }

    public int getQtyday() {
        return qtyday;
    }

    public void setQtyday(int qtyday) {
        this.qtyday = qtyday;
    }

    public int getIdroute() {
        return idroute;
    }

    public void setIdroute(int idroute) {
        this.idroute = idroute;
    }

    public int getIdunituse() {
        return idunituse;
    }

    public void setIdunituse(int idunituse) {
        this.idunituse = idunituse;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getIdunit() {
        return idunit;
    }

    public void setIdunit(int idunit) {
        this.idunit = idunit;
    }

    public String getNameunit() {
        return nameunit;
    }

    public void setNameunit(String nameunit) {
        this.nameunit = nameunit;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    public boolean isNocheck() {
        return nocheck;
    }

    public void setNocheck(boolean nocheck) {
        this.nocheck = nocheck;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
