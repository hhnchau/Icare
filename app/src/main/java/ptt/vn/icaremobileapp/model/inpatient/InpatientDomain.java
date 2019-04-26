package ptt.vn.icaremobileapp.model.inpatient;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.model.register.RegisterDomain;
import ptt.vn.icaremobileapp.utils.Constant;

public class InpatientDomain implements Parcelable {
    private String idlink; /*Id liên kết (toàn đợt nội trú)*/
    private String medicalcode; /*Mã y tế*/
    private String year; /*Năm*/
    private String patid; /*id bệnh nhân*/
    private int idobject; /*id đối tượng: dịch vụ, bảo hiểm y tế*/
    private String hospitalizationdate; /*Ngày giờ nhập viện*/
    private String reasonhospitalization; /*Lý do nhập viện*/
    private int idhospitalizationtype; /*id loại nhập viện (Cấp cứu, khoa khám bệnh, khoa điều trị)*/
    private int idplaceintro; /*id nơi giới thiệu (Cơ quan y tế, tự đến, khác)*/
    private int iddoctor; /*id bác sĩ chỉ định nhập viện*/
    private int idexporttype; /*id export type, id loại xuất viện: chuyển viện, ra viện*/
    private int iddisfrohostype; /*id discharge from hospital type, id loại ra viện: Ra viện, xin về, bỏ về, đưa về*/
    private String disfrohosdate; /*discharge from hospital date, ngày giờ ra viện*/
    private int idhostrantype;/*idhospitaltranfertype, id loại chuyển viện: tuyến trên, tuyến dưới, CK (Chuyên khoa)*/
    private int idhostranto; /*id hospital tranfer to, id bệnh viện chuyển đến*/
    private String attributes; /*thuộc tính mở rộng*/
    private int active; /*Đang sử dụng*/
    private List<InpatientDiagnose> lstInpatientDiagnose;
    private List<InpatientDepartment> lstInpatientDepartment;
    private int actioninpatient;

    private String computer;
    private int siterf;
    private String usercr;
    private int idstatusprofile;
    private String treatmentresults;
    private String nameObject;

    private PatientDomain patient;

    private RegisterDomain register;

    public InpatientDomain() {
    }

    protected InpatientDomain(Parcel in) {
        idlink = in.readString();
        medicalcode = in.readString();
        year = in.readString();
        patid = in.readString();
        idobject = in.readInt();
        hospitalizationdate = in.readString();
        reasonhospitalization = in.readString();
        idhospitalizationtype = in.readInt();
        idplaceintro = in.readInt();
        iddoctor = in.readInt();
        idexporttype = in.readInt();
        iddisfrohostype = in.readInt();
        disfrohosdate = in.readString();
        idhostrantype = in.readInt();
        idhostranto = in.readInt();
        attributes = in.readString();
        active = in.readInt();
        lstInpatientDiagnose = in.createTypedArrayList(InpatientDiagnose.CREATOR);
        lstInpatientDepartment = in.createTypedArrayList(InpatientDepartment.CREATOR);
        actioninpatient = in.readInt();
        computer = in.readString();
        siterf = in.readInt();
        usercr = in.readString();
        idstatusprofile = in.readInt();
        treatmentresults = in.readString();
        nameObject = in.readString();
        patient = in.readParcelable(PatientDomain.class.getClassLoader());
        register = in.readParcelable(RegisterDomain.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idlink);
        dest.writeString(medicalcode);
        dest.writeString(year);
        dest.writeString(patid);
        dest.writeInt(idobject);
        dest.writeString(hospitalizationdate);
        dest.writeString(reasonhospitalization);
        dest.writeInt(idhospitalizationtype);
        dest.writeInt(idplaceintro);
        dest.writeInt(iddoctor);
        dest.writeInt(idexporttype);
        dest.writeInt(iddisfrohostype);
        dest.writeString(disfrohosdate);
        dest.writeInt(idhostrantype);
        dest.writeInt(idhostranto);
        dest.writeString(attributes);
        dest.writeInt(active);
        dest.writeTypedList(lstInpatientDiagnose);
        dest.writeTypedList(lstInpatientDepartment);
        dest.writeInt(actioninpatient);
        dest.writeString(computer);
        dest.writeInt(siterf);
        dest.writeString(usercr);
        dest.writeInt(idstatusprofile);
        dest.writeString(treatmentresults);
        dest.writeString(nameObject);
        dest.writeParcelable(patient, flags);
        dest.writeParcelable(register, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InpatientDomain> CREATOR = new Creator<InpatientDomain>() {
        @Override
        public InpatientDomain createFromParcel(Parcel in) {
            return new InpatientDomain(in);
        }

        @Override
        public InpatientDomain[] newArray(int size) {
            return new InpatientDomain[size];
        }
    };

    public String getIdlink() {
        return idlink;
    }

    public void setIdlink(String idlink) {
        this.idlink = idlink;
    }

    public String getMedicalcode() {
        return medicalcode;
    }

    public void setMedicalcode(String medicalcode) {
        this.medicalcode = medicalcode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPatid() {
        return patid;
    }

    public void setPatid(String patid) {
        this.patid = patid;
    }

    public int getIdobject() {
        return idobject;
    }

    public void setIdobject(int idobject) {
        this.idobject = idobject;
    }

    public String getHospitalizationdate() {
        return hospitalizationdate;
    }

    public void setHospitalizationdate(String hospitalizationdate) {
        this.hospitalizationdate = hospitalizationdate;
    }

    public String getReasonhospitalization() {
        return reasonhospitalization;
    }

    public void setReasonhospitalization(String reasonhospitalization) {
        this.reasonhospitalization = reasonhospitalization;
    }

    public int getIdhospitalizationtype() {
        return idhospitalizationtype;
    }

    public void setIdhospitalizationtype(int idhospitalizationtype) {
        this.idhospitalizationtype = idhospitalizationtype;
    }

    public int getIdplaceintro() {
        return idplaceintro;
    }

    public void setIdplaceintro(int idplaceintro) {
        this.idplaceintro = idplaceintro;
    }

    public int getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(int iddoctor) {
        this.iddoctor = iddoctor;
    }

    public int getIdexporttype() {
        return idexporttype;
    }

    public void setIdexporttype(int idexporttype) {
        this.idexporttype = idexporttype;
    }

    public int getIddisfrohostype() {
        return iddisfrohostype;
    }

    public void setIddisfrohostype(int iddisfrohostype) {
        this.iddisfrohostype = iddisfrohostype;
    }

    public String getDisfrohosdate() {
        return disfrohosdate;
    }

    public void setDisfrohosdate(String disfrohosdate) {
        this.disfrohosdate = disfrohosdate;
    }

    public int getIdhostrantype() {
        return idhostrantype;
    }

    public void setIdhostrantype(int idhostrantype) {
        this.idhostrantype = idhostrantype;
    }

    public int getIdhostranto() {
        return idhostranto;
    }

    public void setIdhostranto(int idhostranto) {
        this.idhostranto = idhostranto;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<InpatientDiagnose> getLstInpatientDiagnose() {
        return lstInpatientDiagnose;
    }

    public void setLstInpatientDiagnose(List<InpatientDiagnose> lstInpatientDiagnose) {
        this.lstInpatientDiagnose = lstInpatientDiagnose;
    }

    public List<InpatientDepartment> getLstInpatientDepartment() {
        return lstInpatientDepartment;
    }

    public void setLstInpatientDepartment(List<InpatientDepartment> lstInpatientDepartment) {
        this.lstInpatientDepartment = lstInpatientDepartment;
    }

    public int getActioninpatient() {
        return actioninpatient;
    }

    public void setActioninpatient(int actioninpatient) {
        this.actioninpatient = actioninpatient;
    }

    public String getComputer() {
        return computer;
    }

    public void setComputer(String computer) {
        this.computer = computer;
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

    public int getIdstatusprofile() {
        return idstatusprofile;
    }

    public void setIdstatusprofile(int idstatusprofile) {
        this.idstatusprofile = idstatusprofile;
    }

    public String getTreatmentresults() {
        return treatmentresults;
    }

    public void setTreatmentresults(String treatmentresults) {
        this.treatmentresults = treatmentresults;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public PatientDomain getPatient() {
        return patient;
    }

    public void setPatient(PatientDomain patient) {
        this.patient = patient;
    }

    public RegisterDomain getRegister() {
        return register;
    }

    public void setRegister(RegisterDomain register) {
        this.register = register;
    }
}
