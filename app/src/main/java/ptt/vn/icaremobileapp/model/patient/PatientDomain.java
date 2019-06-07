package ptt.vn.icaremobileapp.model.patient;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PatientDomain implements Parcelable {
    private int siterf;
    private String patid;
    private String patcode;
    private String hospcode;
    private String firstname;
    private String lastname;
    private int sex;
    private int ismarr;
    private int yearbr;
    private Integer monthbr;
    private Integer daybr;
    private String paport;
    private int idnation;
    private String namenation;
    private int idjob;
    private String namejob;
    private int idethnic;
    private String nameethnic;
    private String email;
    private String phone;
    private String faname;
    private String facard;
    private String attributes;
    private int active;
    private String usercr;
    private String timecr;
    private String userup;
    private String timeup;
    private String computer;
    private String PATIENTNAME;
    private String birthday;
    private List<PatientAdrr> lstPatientAddr;
    private List<PatientBHi> lstPatientBhi;
    private List<PatientHi> lstPatientHi;
    private List<PatientIde> lstPatientIde;

    public PatientDomain() {
    }

    protected PatientDomain(Parcel in) {
        siterf = in.readInt();
        patid = in.readString();
        patcode = in.readString();
        hospcode = in.readString();
        firstname = in.readString();
        lastname = in.readString();
        sex = in.readInt();
        ismarr = in.readInt();
        yearbr = in.readInt();
        monthbr = in.readInt();
        daybr = in.readInt();
        paport = in.readString();
        idnation = in.readInt();
        namenation = in.readString();
        idjob = in.readInt();
        namejob = in.readString();
        idethnic = in.readInt();
        nameethnic = in.readString();
        email = in.readString();
        phone = in.readString();
        faname = in.readString();
        facard = in.readString();
        attributes = in.readString();
        active = in.readInt();
        usercr = in.readString();
        timecr = in.readString();
        userup = in.readString();
        timeup = in.readString();
        computer = in.readString();
        PATIENTNAME = in.readString();
        birthday = in.readString();
    }

    public static final Creator<PatientDomain> CREATOR = new Creator<PatientDomain>() {
        @Override
        public PatientDomain createFromParcel(Parcel in) {
            return new PatientDomain(in);
        }

        @Override
        public PatientDomain[] newArray(int size) {
            return new PatientDomain[size];
        }
    };

    public String getGender() {
        if (sex == 0) return "Nữ";
        if (sex == 1) return "Nam";
        return "KXD";
    }

    public String getMarried() {
        if (ismarr == 0) return "Độc Thân";
        if (ismarr == 1) return "Kết Hôn";
        return "KXD";
    }

    public int getSiterf() {
        return siterf;
    }

    public void setSiterf(int siterf) {
        this.siterf = siterf;
    }

    public String getPatid() {
        return patid;
    }

    public void setPatid(String patid) {
        this.patid = patid;
    }

    public String getPatcode() {
        return patcode;
    }

    public void setPatcode(String patcode) {
        this.patcode = patcode;
    }

    public String getHospcode() {
        return hospcode;
    }

    public void setHospcode(String hospcode) {
        this.hospcode = hospcode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getIsmarr() {
        return ismarr;
    }

    public void setIsmarr(int ismarr) {
        this.ismarr = ismarr;
    }

    public int getYearbr() {
        return yearbr;
    }

    public void setYearbr(int yearbr) {
        this.yearbr = yearbr;
    }

    public int getMonthbr() {
        return monthbr;
    }

    public void setMonthbr(Integer monthbr) {
        this.monthbr = monthbr;
    }

    public int getDaybr() {
        return daybr;
    }

    public void setDaybr(Integer daybr) {
        this.daybr = daybr;
    }

    public String getPaport() {
        return paport;
    }

    public void setPaport(String paport) {
        this.paport = paport;
    }

    public int getIdnation() {
        return idnation;
    }

    public void setIdnation(int idnation) {
        this.idnation = idnation;
    }

    public String getNamenation() {
        return namenation;
    }

    public void setNamenation(String namenation) {
        this.namenation = namenation;
    }

    public int getIdjob() {
        return idjob;
    }

    public void setIdjob(int idjob) {
        this.idjob = idjob;
    }

    public String getNamejob() {
        return namejob;
    }

    public void setNamejob(String namejob) {
        this.namejob = namejob;
    }

    public int getIdethnic() {
        return idethnic;
    }

    public void setIdethnic(int idethnic) {
        this.idethnic = idethnic;
    }

    public String getNameethnic() {
        return nameethnic;
    }

    public void setNameethnic(String nameethnic) {
        this.nameethnic = nameethnic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFaname() {
        return faname;
    }

    public void setFaname(String faname) {
        this.faname = faname;
    }

    public String getFacard() {
        return facard;
    }

    public void setFacard(String facard) {
        this.facard = facard;
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

    public String getPATIENTNAME() {
        return PATIENTNAME;
    }

    public void setPATIENTNAME(String PATIENTNAME) {
        this.PATIENTNAME = PATIENTNAME;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<PatientAdrr> getLstPatientAddr() {
        return lstPatientAddr;
    }

    public void setLstPatientAddr(List<PatientAdrr> lstPatientAddr) {
        this.lstPatientAddr = lstPatientAddr;
    }

    public List<PatientBHi> getLstPatientBhi() {
        return lstPatientBhi;
    }

    public void setLstPatientBhi(List<PatientBHi> lstPatientBhi) {
        this.lstPatientBhi = lstPatientBhi;
    }

    public List<PatientHi> getLstPatientHi() {
        return lstPatientHi;
    }

    public void setLstPatientHi(List<PatientHi> lstPatientHi) {
        this.lstPatientHi = lstPatientHi;
    }

    public List<PatientIde> getLstPatientIde() {
        return lstPatientIde;
    }

    public void setLstPatientIde(List<PatientIde> lstPatientIde) {
        this.lstPatientIde = lstPatientIde;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeString(patid);
        dest.writeString(patcode);
        dest.writeString(hospcode);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeInt(sex);
        dest.writeInt(ismarr);
        dest.writeInt(yearbr);
        dest.writeInt(monthbr);
        dest.writeInt(daybr);
        dest.writeString(paport);
        dest.writeInt(idnation);
        dest.writeString(namenation);
        dest.writeInt(idjob);
        dest.writeString(namejob);
        dest.writeInt(idethnic);
        dest.writeString(nameethnic);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(faname);
        dest.writeString(facard);
        dest.writeString(attributes);
        dest.writeInt(active);
        dest.writeString(usercr);
        dest.writeString(timecr);
        dest.writeString(userup);
        dest.writeString(timeup);
        dest.writeString(computer);
        dest.writeString(PATIENTNAME);
        dest.writeString(birthday);
    }
}
