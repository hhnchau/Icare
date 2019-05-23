package ptt.vn.icaremobileapp.model.inpatient;

import android.os.Parcel;
import android.os.Parcelable;

public class InpatientDrugOrder implements Parcelable {
    private int siterf;
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
    private long price;
    private long total;
    private String usage;
    private String desc;
    private String qtymor;
    private String qtydin;
    private String qtyaft;
    private String qtynig;
    private int qtyday;
    private int idstore;
    private String attributes;
    private String mmyy;
    private String year;
    private int insurance;
    private int type;
    private int active;
    private String usercr;
    private String timecr;
    private String userup;
    private String timeup;
    private String computer;



    public InpatientDrugOrder() {
    }

    protected InpatientDrugOrder(Parcel in) {
        siterf = in.readInt();
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
        price = in.readLong();
        total = in.readLong();
        usage = in.readString();
        desc = in.readString();
        qtymor = in.readString();
        qtydin = in.readString();
        qtyaft = in.readString();
        qtynig = in.readString();
        qtyday = in.readInt();
        idstore = in.readInt();
        attributes = in.readString();
        mmyy = in.readString();
        year = in.readString();
        insurance = in.readInt();
        type = in.readInt();
        active = in.readInt();
        usercr = in.readString();
        timecr = in.readString();
        userup = in.readString();
        timeup = in.readString();
        computer = in.readString();
    }

    public static final Creator<InpatientDrugOrder> CREATOR = new Creator<InpatientDrugOrder>() {
        @Override
        public InpatientDrugOrder createFromParcel(Parcel in) {
            return new InpatientDrugOrder(in);
        }

        @Override
        public InpatientDrugOrder[] newArray(int size) {
            return new InpatientDrugOrder[size];
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
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

    public int getIdstore() {
        return idstore;
    }

    public void setIdstore(int idstore) {
        this.idstore = idstore;
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
        dest.writeDouble(price);
        dest.writeDouble(total);
        dest.writeString(usage);
        dest.writeString(desc);
        dest.writeString(qtymor);
        dest.writeString(qtydin);
        dest.writeString(qtyaft);
        dest.writeString(qtynig);
        dest.writeInt(qtyday);
        dest.writeInt(idstore);
        dest.writeString(attributes);
        dest.writeString(mmyy);
        dest.writeString(year);
        dest.writeInt(insurance);
        dest.writeInt(type);
        dest.writeInt(active);
        dest.writeString(usercr);
        dest.writeString(timecr);
        dest.writeString(userup);
        dest.writeString(timeup);
        dest.writeString(computer);
    }
}
