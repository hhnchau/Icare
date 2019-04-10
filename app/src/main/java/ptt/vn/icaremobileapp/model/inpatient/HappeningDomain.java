package ptt.vn.icaremobileapp.model.inpatient;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class HappeningDomain implements Parcelable, Cloneable {
    private int siterf;
    private String idline;
    private String idlink;
    private String idlinedepartinfolog;
    private String idtreatment;
    private float code;
    private int sort;
    private String datecreate;
    private String body;
    private String cyclic;
    private String respiratory;
    private String digest;
    private String kidney;
    private String nerve;
    private String muscle;
    private String nose;
    private String teeth;
    private String eye;
    private String endocrine;
    private String circui;
    private int blomin;
    private int blomax;
    private int heartb;
    private float temper;
    private float weight;
    private String happening;
    private int iddoctor;
    private String namedoctor;
    private int active;
    private String attributes;
    private String usercr;
    private String timecr;
    private String userup;
    private String timeup;
    private String computer;

    private List<DrugOrder> lstInpatientDrugOrder;
    private List<DrugOrderOutside> lstInpatientDrugOrderOutside;
    private List<ServiceOrder> lstInpatientServiceOrder;
    private List<InpatientDiagnose> lstInpatientDiagnose;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public HappeningDomain() {
    }

    protected HappeningDomain(Parcel in) {
        siterf = in.readInt();
        idline = in.readString();
        idlink = in.readString();
        idlinedepartinfolog = in.readString();
        idtreatment = in.readString();
        code = in.readFloat();
        sort = in.readInt();
        datecreate = in.readString();
        body = in.readString();
        cyclic = in.readString();
        respiratory = in.readString();
        digest = in.readString();
        kidney = in.readString();
        nerve = in.readString();
        muscle = in.readString();
        nose = in.readString();
        teeth = in.readString();
        eye = in.readString();
        endocrine = in.readString();
        circui = in.readString();
        blomin = in.readInt();
        blomax = in.readInt();
        heartb = in.readInt();
        temper = in.readFloat();
        weight = in.readFloat();
        happening = in.readString();
        iddoctor = in.readInt();
        namedoctor = in.readString();
        active = in.readInt();
        attributes = in.readString();
        usercr = in.readString();
        timecr = in.readString();
        userup = in.readString();
        timeup = in.readString();
        computer = in.readString();
        lstInpatientDrugOrder = in.createTypedArrayList(DrugOrder.CREATOR);
        lstInpatientDrugOrderOutside = in.createTypedArrayList(DrugOrderOutside.CREATOR);
        lstInpatientServiceOrder = in.createTypedArrayList(ServiceOrder.CREATOR);
        lstInpatientDiagnose = in.createTypedArrayList(InpatientDiagnose.CREATOR);
    }

    public static final Creator<HappeningDomain> CREATOR = new Creator<HappeningDomain>() {
        @Override
        public HappeningDomain createFromParcel(Parcel in) {
            return new HappeningDomain(in);
        }

        @Override
        public HappeningDomain[] newArray(int size) {
            return new HappeningDomain[size];
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

    public String getIdlink() {
        return idlink;
    }

    public void setIdlink(String idlink) {
        this.idlink = idlink;
    }

    public String getIdlinedepartinfolog() {
        return idlinedepartinfolog;
    }

    public void setIdlinedepartinfolog(String idlinedepartinfolog) {
        this.idlinedepartinfolog = idlinedepartinfolog;
    }

    public String getIdtreatment() {
        return idtreatment;
    }

    public void setIdtreatment(String idtreatment) {
        this.idtreatment = idtreatment;
    }

    public float getCode() {
        return code;
    }

    public void setCode(float code) {
        this.code = code;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCyclic() {
        return cyclic;
    }

    public void setCyclic(String cyclic) {
        this.cyclic = cyclic;
    }

    public String getRespiratory() {
        return respiratory;
    }

    public void setRespiratory(String respiratory) {
        this.respiratory = respiratory;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getKidney() {
        return kidney;
    }

    public void setKidney(String kidney) {
        this.kidney = kidney;
    }

    public String getNerve() {
        return nerve;
    }

    public void setNerve(String nerve) {
        this.nerve = nerve;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getNose() {
        return nose;
    }

    public void setNose(String nose) {
        this.nose = nose;
    }

    public String getTeeth() {
        return teeth;
    }

    public void setTeeth(String teeth) {
        this.teeth = teeth;
    }

    public String getEye() {
        return eye;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }

    public String getEndocrine() {
        return endocrine;
    }

    public void setEndocrine(String endocrine) {
        this.endocrine = endocrine;
    }

    public String getCircui() {
        return circui;
    }

    public void setCircui(String circui) {
        this.circui = circui;
    }

    public int getBlomin() {
        return blomin;
    }

    public void setBlomin(int blomin) {
        this.blomin = blomin;
    }

    public int getBlomax() {
        return blomax;
    }

    public void setBlomax(int blomax) {
        this.blomax = blomax;
    }

    public int getHeartb() {
        return heartb;
    }

    public void setHeartb(int heartb) {
        this.heartb = heartb;
    }

    public float getTemper() {
        return temper;
    }

    public void setTemper(float temper) {
        this.temper = temper;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getHappening() {
        return happening;
    }

    public void setHappening(String happening) {
        this.happening = happening;
    }

    public int getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(int iddoctor) {
        this.iddoctor = iddoctor;
    }

    public String getNamedoctor() {
        return namedoctor;
    }

    public void setNamedoctor(String namedoctor) {
        this.namedoctor = namedoctor;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
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

    public List<DrugOrder> getLstInpatientDrugOrder() {
        return lstInpatientDrugOrder;
    }

    public void setLstInpatientDrugOrder(List<DrugOrder> lstInpatientDrugOrder) {
        this.lstInpatientDrugOrder = lstInpatientDrugOrder;
    }

    public List<DrugOrderOutside> getLstInpatientDrugOrderOutside() {
        return lstInpatientDrugOrderOutside;
    }

    public void setLstInpatientDrugOrderOutside(List<DrugOrderOutside> lstInpatientDrugOrderOutside) {
        this.lstInpatientDrugOrderOutside = lstInpatientDrugOrderOutside;
    }

    public List<ServiceOrder> getLstInpatientServiceOrder() {
        return lstInpatientServiceOrder;
    }

    public void setLstInpatientServiceOrder(List<ServiceOrder> lstInpatientServiceOrder) {
        this.lstInpatientServiceOrder = lstInpatientServiceOrder;
    }

    public List<InpatientDiagnose> getLstInpatientDiagnose() {
        return lstInpatientDiagnose;
    }

    public void setLstInpatientDiagnose(List<InpatientDiagnose> lstInpatientDiagnose) {
        this.lstInpatientDiagnose = lstInpatientDiagnose;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeString(idline);
        dest.writeString(idlink);
        dest.writeString(idlinedepartinfolog);
        dest.writeString(idtreatment);
        dest.writeFloat(code);
        dest.writeInt(sort);
        dest.writeString(datecreate);
        dest.writeString(body);
        dest.writeString(cyclic);
        dest.writeString(respiratory);
        dest.writeString(digest);
        dest.writeString(kidney);
        dest.writeString(nerve);
        dest.writeString(muscle);
        dest.writeString(nose);
        dest.writeString(teeth);
        dest.writeString(eye);
        dest.writeString(endocrine);
        dest.writeString(circui);
        dest.writeInt(blomin);
        dest.writeInt(blomax);
        dest.writeInt(heartb);
        dest.writeFloat(temper);
        dest.writeFloat(weight);
        dest.writeString(happening);
        dest.writeInt(iddoctor);
        dest.writeString(namedoctor);
        dest.writeInt(active);
        dest.writeString(attributes);
        dest.writeString(usercr);
        dest.writeString(timecr);
        dest.writeString(userup);
        dest.writeString(timeup);
        dest.writeString(computer);
        dest.writeTypedList(lstInpatientDrugOrder);
        dest.writeTypedList(lstInpatientDrugOrderOutside);
        dest.writeTypedList(lstInpatientServiceOrder);
        dest.writeTypedList(lstInpatientDiagnose);
    }
}
