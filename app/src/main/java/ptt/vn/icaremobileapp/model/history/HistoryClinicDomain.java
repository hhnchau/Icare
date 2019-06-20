package ptt.vn.icaremobileapp.model.history;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ptt.vn.icaremobileapp.model.Parent;
import ptt.vn.icaremobileapp.model.outclinic.OutDiagnoseDomain;
import ptt.vn.icaremobileapp.model.outclinic.OutDrugOrderDomain;
import ptt.vn.icaremobileapp.model.outclinic.OutServiceOrderDomain;

public class HistoryClinicDomain extends Parent implements Parcelable {
    private String sitename;
    private String patid;
    private String hospcode;
    private String idlineoutclinic;
    private String dateexa;
    private String reason;
    private String circui;
    private int blomax;
    private int blomin;
    private float temper;
    private int heartb;
    private float weight;
    private String predia;
    private List<OutDiagnoseDomain> lstdiagnose;
    private List<OutDrugOrderDomain> lstdrugorder;
    private List<OutServiceOrderDomain> lstserviceorder;

    public HistoryClinicDomain() {
    }

    protected HistoryClinicDomain(Parcel in) {
        sitename = in.readString();
        patid = in.readString();
        hospcode = in.readString();
        idlineoutclinic = in.readString();
        dateexa = in.readString();
        reason = in.readString();
        circui = in.readString();
        blomax = in.readInt();
        blomin = in.readInt();
        temper = in.readFloat();
        heartb = in.readInt();
        weight = in.readFloat();
        predia = in.readString();
        lstdiagnose = in.createTypedArrayList(OutDiagnoseDomain.CREATOR);
        lstdrugorder = in.createTypedArrayList(OutDrugOrderDomain.CREATOR);
        lstserviceorder = in.createTypedArrayList(OutServiceOrderDomain.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sitename);
        dest.writeString(patid);
        dest.writeString(hospcode);
        dest.writeString(idlineoutclinic);
        dest.writeString(dateexa);
        dest.writeString(reason);
        dest.writeString(circui);
        dest.writeInt(blomax);
        dest.writeInt(blomin);
        dest.writeFloat(temper);
        dest.writeInt(heartb);
        dest.writeFloat(weight);
        dest.writeString(predia);
        dest.writeTypedList(lstdiagnose);
        dest.writeTypedList(lstdrugorder);
        dest.writeTypedList(lstserviceorder);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HistoryClinicDomain> CREATOR = new Creator<HistoryClinicDomain>() {
        @Override
        public HistoryClinicDomain createFromParcel(Parcel in) {
            return new HistoryClinicDomain(in);
        }

        @Override
        public HistoryClinicDomain[] newArray(int size) {
            return new HistoryClinicDomain[size];
        }
    };

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getPatid() {
        return patid;
    }

    public void setPatid(String patid) {
        this.patid = patid;
    }

    public String getHospcode() {
        return hospcode;
    }

    public void setHospcode(String hospcode) {
        this.hospcode = hospcode;
    }

    public String getIdlineoutclinic() {
        return idlineoutclinic;
    }

    public void setIdlineoutclinic(String idlineoutclinic) {
        this.idlineoutclinic = idlineoutclinic;
    }

    public String getDateexa() {
        return dateexa;
    }

    public void setDateexa(String dateexa) {
        this.dateexa = dateexa;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCircui() {
        return circui;
    }

    public void setCircui(String circui) {
        this.circui = circui;
    }

    public int getBlomax() {
        return blomax;
    }

    public void setBlomax(int blomax) {
        this.blomax = blomax;
    }

    public int getBlomin() {
        return blomin;
    }

    public void setBlomin(int blomin) {
        this.blomin = blomin;
    }

    public float getTemper() {
        return temper;
    }

    public void setTemper(float temper) {
        this.temper = temper;
    }

    public int getHeartb() {
        return heartb;
    }

    public void setHeartb(int heartb) {
        this.heartb = heartb;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getPredia() {
        return predia;
    }

    public void setPredia(String predia) {
        this.predia = predia;
    }

    public List<OutDiagnoseDomain> getLstdiagnose() {
        return lstdiagnose;
    }

    public void setLstdiagnose(List<OutDiagnoseDomain> lstdiagnose) {
        this.lstdiagnose = lstdiagnose;
    }

    public List<OutDrugOrderDomain> getLstdrugorder() {
        return lstdrugorder;
    }

    public void setLstdrugorder(List<OutDrugOrderDomain> lstdrugorder) {
        this.lstdrugorder = lstdrugorder;
    }

    public List<OutServiceOrderDomain> getLstserviceorder() {
        return lstserviceorder;
    }

    public void setLstserviceorder(List<OutServiceOrderDomain> lstserviceorder) {
        this.lstserviceorder = lstserviceorder;
    }
}
