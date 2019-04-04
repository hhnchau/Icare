package ptt.vn.icaremobileapp.model.inpatient;

import java.util.List;

public class InpatientDomain {
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

    public InpatientDomain() {
    }

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
}
