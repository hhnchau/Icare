package ptt.vn.icaremobileapp.model.inpatient;

import android.os.Parcel;
import android.os.Parcelable;

public class DrugOrderOutside implements Parcelable {
    private int siterf;
    private String idline;
    private String idhappening;
    private int iddrug;
    private String codedrug;
    private String namedrug;
    private float qty;
    private int idunit;
    private String idunitname;
    private int idunituse;
    private String unitusename;
    private int idunitroute;
    private String unitroutename;
    private String usage;
    private String desc;
    private String qtymor;
    private String qtydin;
    private String qtyaft;
    private String qtynig;
    private int qtyday;
    private String attributes;
    private String mmyy;
    private String year;
    private int active;
    private String usercr;
    private String timecr;
    private String userup;
    private String timeup;
    private String computer;


    public DrugOrderOutside() {
    }

    protected DrugOrderOutside(Parcel in) {
        siterf = in.readInt();
        idline = in.readString();
        idhappening = in.readString();
        iddrug = in.readInt();
        codedrug = in.readString();
        namedrug = in.readString();
        qty = in.readFloat();
        idunit = in.readInt();
        idunitname = in.readString();
        idunituse = in.readInt();
        unitusename = in.readString();
        idunitroute = in.readInt();
        unitroutename = in.readString();
        usage = in.readString();
        desc = in.readString();
        qtymor = in.readString();
        qtydin = in.readString();
        qtyaft = in.readString();
        qtynig = in.readString();
        qtyday = in.readInt();
        attributes = in.readString();
        mmyy = in.readString();
        year = in.readString();
        active = in.readInt();
        usercr = in.readString();
        timecr = in.readString();
        userup = in.readString();
        timeup = in.readString();
        computer = in.readString();
    }

    public static final Creator<DrugOrderOutside> CREATOR = new Creator<DrugOrderOutside>() {
        @Override
        public DrugOrderOutside createFromParcel(Parcel in) {
            return new DrugOrderOutside(in);
        }

        @Override
        public DrugOrderOutside[] newArray(int size) {
            return new DrugOrderOutside[size];
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

    public int getIdunitroute() {
        return idunitroute;
    }

    public void setIdunitroute(int idunitroute) {
        this.idunitroute = idunitroute;
    }

    public String getUnitroutename() {
        return unitroutename;
    }

    public void setUnitroutename(String unitroutename) {
        this.unitroutename = unitroutename;
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
        dest.writeFloat(qty);
        dest.writeInt(idunit);
        dest.writeString(idunitname);
        dest.writeInt(idunituse);
        dest.writeString(unitusename);
        dest.writeInt(idunitroute);
        dest.writeString(unitroutename);
        dest.writeString(usage);
        dest.writeString(desc);
        dest.writeString(qtymor);
        dest.writeString(qtydin);
        dest.writeString(qtyaft);
        dest.writeString(qtynig);
        dest.writeInt(qtyday);
        dest.writeString(attributes);
        dest.writeString(mmyy);
        dest.writeString(year);
        dest.writeInt(active);
        dest.writeString(usercr);
        dest.writeString(timecr);
        dest.writeString(userup);
        dest.writeString(timeup);
        dest.writeString(computer);
    }
}
