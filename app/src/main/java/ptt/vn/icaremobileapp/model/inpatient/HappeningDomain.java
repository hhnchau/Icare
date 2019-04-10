package ptt.vn.icaremobileapp.model.inpatient;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class HappeningDomain implements Parcelable {
    private String idline;
    private String idlink;
    private String idlinedepartinfolog;
    private String idtreatment;
    private String code;
    private int sort;
    private String datecreate;
    private int body;
    private int cyclic;
    private int respiratory;
    private int digest;
    private int kidney;
    private int nerve;
    private int muscle;
    private int nose;
    private int teeth;
    private int eye;
    private int endocrine;
    private float circui;
    private float blomin;
    private float blomax;
    private float heartb;
    private float temper;
    private float weight;
    private String happening;
    private int iddoctor;
    private String namedoctor;
    private int active;
    private String typemedicalname;
    private String attributes;
    private List<DrugOrder> lstInpatientDrugOrder;
    private List<DrugOrderOutside> lstInpatientDrugOrderOutside;
    private List<ServiceOrder> lstInpatientServiceOrder;
    private List<InpatientDiagnose> lstInpatientDiagnose;
    private int siterf;
    private String usercr;
    private String timecr;
    private String userup;
    private String timeup;
    private String computer;
    private int ismodify;

    public HappeningDomain() {
    }

    protected HappeningDomain(Parcel in) {
        idline = in.readString();
        idlink = in.readString();
        idlinedepartinfolog = in.readString();
        idtreatment = in.readString();
        code = in.readString();
        sort = in.readInt();
        datecreate = in.readString();
        body = in.readInt();
        cyclic = in.readInt();
        respiratory = in.readInt();
        digest = in.readInt();
        kidney = in.readInt();
        nerve = in.readInt();
        muscle = in.readInt();
        nose = in.readInt();
        teeth = in.readInt();
        eye = in.readInt();
        endocrine = in.readInt();
        circui = in.readFloat();
        blomin = in.readFloat();
        blomax = in.readFloat();
        heartb = in.readFloat();
        temper = in.readFloat();
        weight = in.readFloat();
        happening = in.readString();
        iddoctor = in.readInt();
        namedoctor = in.readString();
        active = in.readInt();
        typemedicalname = in.readString();
        attributes = in.readString();
        lstInpatientDrugOrder = in.createTypedArrayList(DrugOrder.CREATOR);
        lstInpatientDrugOrderOutside = in.createTypedArrayList(DrugOrderOutside.CREATOR);
        lstInpatientServiceOrder = in.createTypedArrayList(ServiceOrder.CREATOR);
        siterf = in.readInt();
        usercr = in.readString();
        timecr = in.readString();
        userup = in.readString();
        timeup = in.readString();
        computer = in.readString();
        ismodify = in.readInt();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int getCyclic() {
        return cyclic;
    }

    public void setCyclic(int cyclic) {
        this.cyclic = cyclic;
    }

    public int getRespiratory() {
        return respiratory;
    }

    public void setRespiratory(int respiratory) {
        this.respiratory = respiratory;
    }

    public int getDigest() {
        return digest;
    }

    public void setDigest(int digest) {
        this.digest = digest;
    }

    public int getKidney() {
        return kidney;
    }

    public void setKidney(int kidney) {
        this.kidney = kidney;
    }

    public int getNerve() {
        return nerve;
    }

    public void setNerve(int nerve) {
        this.nerve = nerve;
    }

    public int getMuscle() {
        return muscle;
    }

    public void setMuscle(int muscle) {
        this.muscle = muscle;
    }

    public int getNose() {
        return nose;
    }

    public void setNose(int nose) {
        this.nose = nose;
    }

    public int getTeeth() {
        return teeth;
    }

    public void setTeeth(int teeth) {
        this.teeth = teeth;
    }

    public int getEye() {
        return eye;
    }

    public void setEye(int eye) {
        this.eye = eye;
    }

    public int getEndocrine() {
        return endocrine;
    }

    public void setEndocrine(int endocrine) {
        this.endocrine = endocrine;
    }

    public float getCircui() {
        return circui;
    }

    public void setCircui(float circui) {
        this.circui = circui;
    }

    public float getBlomin() {
        return blomin;
    }

    public void setBlomin(float blomin) {
        this.blomin = blomin;
    }

    public float getBlomax() {
        return blomax;
    }

    public void setBlomax(float blomax) {
        this.blomax = blomax;
    }

    public float getHeartb() {
        return heartb;
    }

    public void setHeartb(float heartb) {
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

    public String getTypemedicalname() {
        return typemedicalname;
    }

    public void setTypemedicalname(String typemedicalname) {
        this.typemedicalname = typemedicalname;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
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

    public int getIsmodify() {
        return ismodify;
    }

    public void setIsmodify(int ismodify) {
        this.ismodify = ismodify;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idline);
        dest.writeString(idlink);
        dest.writeString(idlinedepartinfolog);
        dest.writeString(idtreatment);
        dest.writeString(code);
        dest.writeInt(sort);
        dest.writeString(datecreate);
        dest.writeInt(body);
        dest.writeInt(cyclic);
        dest.writeInt(respiratory);
        dest.writeInt(digest);
        dest.writeInt(kidney);
        dest.writeInt(nerve);
        dest.writeInt(muscle);
        dest.writeInt(nose);
        dest.writeInt(teeth);
        dest.writeInt(eye);
        dest.writeInt(endocrine);
        dest.writeFloat(circui);
        dest.writeFloat(blomin);
        dest.writeFloat(blomax);
        dest.writeFloat(heartb);
        dest.writeFloat(temper);
        dest.writeFloat(weight);
        dest.writeString(happening);
        dest.writeInt(iddoctor);
        dest.writeString(namedoctor);
        dest.writeInt(active);
        dest.writeString(typemedicalname);
        dest.writeString(attributes);
        dest.writeTypedList(lstInpatientDrugOrder);
        dest.writeTypedList(lstInpatientDrugOrderOutside);
        dest.writeTypedList(lstInpatientServiceOrder);
        dest.writeInt(siterf);
        dest.writeString(usercr);
        dest.writeString(timecr);
        dest.writeString(userup);
        dest.writeString(timeup);
        dest.writeString(computer);
        dest.writeInt(ismodify);
    }
}
