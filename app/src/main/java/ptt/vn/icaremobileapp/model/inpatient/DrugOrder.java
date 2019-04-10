package ptt.vn.icaremobileapp.model.inpatient;

import android.os.Parcel;
import android.os.Parcelable;

public class DrugOrder implements Parcelable {
    private String idline;
    private String idhappening;
    private int iddrug;
    private String codedrug;
    private String namedrug;
    private String activename;
    private float qty;
    private int idunit;
    private String idunitname;
    private int idunituse;
    private String unitusename;
    private int idroute;
    private String routename;
    private float price;
    private float total;
    private String usage;
    private String desc;
    private float qtymor;
    private float qtydin;
    private float qtyaft;
    private float qtynig;
    private float qtyday;
    private int insurance;
    private int type;
    private String attributes;
    private String mmyy;
    private String year;
    private int idstore;
    private String namestore;
    private int active;
    private String date;
    private String code;
    private int siterf;
    private String usercr;
    private String timecr;
    private String userup;
    private String timeup;
    private String computer;

    public DrugOrder() {
    }

    protected DrugOrder(Parcel in) {
        idline = in.readString();
        idhappening = in.readString();
        iddrug = in.readInt();
        codedrug = in.readString();
        namedrug = in.readString();
        activename = in.readString();
        qty = in.readFloat();
        idunit = in.readInt();
        idunitname = in.readString();
        idunituse = in.readInt();
        unitusename = in.readString();
        idroute = in.readInt();
        routename = in.readString();
        price = in.readFloat();
        total = in.readFloat();
        usage = in.readString();
        desc = in.readString();
        qtymor = in.readFloat();
        qtydin = in.readFloat();
        qtyaft = in.readFloat();
        qtynig = in.readFloat();
        qtyday = in.readFloat();
        insurance = in.readInt();
        type = in.readInt();
        attributes = in.readString();
        mmyy = in.readString();
        year = in.readString();
        idstore = in.readInt();
        namestore = in.readString();
        active = in.readInt();
        date = in.readString();
        code = in.readString();
        siterf = in.readInt();
        usercr = in.readString();
        timecr = in.readString();
        userup = in.readString();
        timeup = in.readString();
        computer = in.readString();
    }

    public static final Creator<DrugOrder> CREATOR = new Creator<DrugOrder>() {
        @Override
        public DrugOrder createFromParcel(Parcel in) {
            return new DrugOrder(in);
        }

        @Override
        public DrugOrder[] newArray(int size) {
            return new DrugOrder[size];
        }
    };

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

    public int getIddrug() {
        return iddrug;
    }

    public void setIddrug(int iddrug) {
        this.iddrug = iddrug;
    }

    public String getCodedrug() {
        return codedrug;
    }

    public void setCodedrug(String codedrug) {
        this.codedrug = codedrug;
    }

    public String getNamedrug() {
        return namedrug;
    }

    public void setNamedrug(String namedrug) {
        this.namedrug = namedrug;
    }

    public String getActivename() {
        return activename;
    }

    public void setActivename(String activename) {
        this.activename = activename;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public int getIdunit() {
        return idunit;
    }

    public void setIdunit(int idunit) {
        this.idunit = idunit;
    }

    public String getIdunitname() {
        return idunitname;
    }

    public void setIdunitname(String idunitname) {
        this.idunitname = idunitname;
    }

    public int getIdunituse() {
        return idunituse;
    }

    public void setIdunituse(int idunituse) {
        this.idunituse = idunituse;
    }

    public String getUnitusename() {
        return unitusename;
    }

    public void setUnitusename(String unitusename) {
        this.unitusename = unitusename;
    }

    public int getIdroute() {
        return idroute;
    }

    public void setIdroute(int idroute) {
        this.idroute = idroute;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
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

    public float getQtymor() {
        return qtymor;
    }

    public void setQtymor(float qtymor) {
        this.qtymor = qtymor;
    }

    public float getQtydin() {
        return qtydin;
    }

    public void setQtydin(float qtydin) {
        this.qtydin = qtydin;
    }

    public float getQtyaft() {
        return qtyaft;
    }

    public void setQtyaft(float qtyaft) {
        this.qtyaft = qtyaft;
    }

    public float getQtynig() {
        return qtynig;
    }

    public void setQtynig(float qtynig) {
        this.qtynig = qtynig;
    }

    public float getQtyday() {
        return qtyday;
    }

    public void setQtyday(float qtyday) {
        this.qtyday = qtyday;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
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

    public int getIdstore() {
        return idstore;
    }

    public void setIdstore(int idstore) {
        this.idstore = idstore;
    }

    public String getNamestore() {
        return namestore;
    }

    public void setNamestore(String namestore) {
        this.namestore = namestore;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSiterf() {
        return siterf;
    }

    public void setSiterf(int siterf) {
        this.siterf = siterf;
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
        dest.writeString(idline);
        dest.writeString(idhappening);
        dest.writeInt(iddrug);
        dest.writeString(codedrug);
        dest.writeString(namedrug);
        dest.writeString(activename);
        dest.writeFloat(qty);
        dest.writeInt(idunit);
        dest.writeString(idunitname);
        dest.writeInt(idunituse);
        dest.writeString(unitusename);
        dest.writeInt(idroute);
        dest.writeString(routename);
        dest.writeFloat(price);
        dest.writeFloat(total);
        dest.writeString(usage);
        dest.writeString(desc);
        dest.writeFloat(qtymor);
        dest.writeFloat(qtydin);
        dest.writeFloat(qtyaft);
        dest.writeFloat(qtynig);
        dest.writeFloat(qtyday);
        dest.writeInt(insurance);
        dest.writeInt(type);
        dest.writeString(attributes);
        dest.writeString(mmyy);
        dest.writeString(year);
        dest.writeInt(idstore);
        dest.writeString(namestore);
        dest.writeInt(active);
        dest.writeString(date);
        dest.writeString(code);
        dest.writeInt(siterf);
        dest.writeString(usercr);
        dest.writeString(timecr);
        dest.writeString(userup);
        dest.writeString(timeup);
        dest.writeString(computer);
    }
}
