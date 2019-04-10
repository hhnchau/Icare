package ptt.vn.icaremobileapp.model.register;

import android.os.Parcel;
import android.os.Parcelable;

public class RegisterDomain implements Parcelable {
    private int siterf;
    private String patid;
    private String idlink;
    private String hosnum;
    private String idaddr;
    private String idide;
    private String idbhi;
    private String patcode;
    private String regdate;
    private String purpos;
    private int typrec;
    private int formco;
    private int rfdoctor;
    private int introd;
    private int idobject;
    private int pricelist;
    private int promotions;
    private String emplocode;
    private int relationsh;
    private String symptoms;
    private int roomservice;
    private int idmedexa;
    private int typeexamination;
    private int status;
    private int isemergency;
    private String attributes;
    private int active;
    private String usercr;
    private String timecr;
    private String userup;
    private String timeup;
    private String computer;

    public RegisterDomain() {
    }

    protected RegisterDomain(Parcel in) {
        siterf = in.readInt();
        patid = in.readString();
        idlink = in.readString();
        hosnum = in.readString();
        idaddr = in.readString();
        idide = in.readString();
        idbhi = in.readString();
        patcode = in.readString();
        regdate = in.readString();
        purpos = in.readString();
        typrec = in.readInt();
        formco = in.readInt();
        rfdoctor = in.readInt();
        introd = in.readInt();
        idobject = in.readInt();
        pricelist = in.readInt();
        promotions = in.readInt();
        emplocode = in.readString();
        relationsh = in.readInt();
        symptoms = in.readString();
        roomservice = in.readInt();
        idmedexa = in.readInt();
        typeexamination = in.readInt();
        status = in.readInt();
        isemergency = in.readInt();
        attributes = in.readString();
        active = in.readInt();
        usercr = in.readString();
        timecr = in.readString();
        userup = in.readString();
        timeup = in.readString();
        computer = in.readString();
    }

    public static final Creator<RegisterDomain> CREATOR = new Creator<RegisterDomain>() {
        @Override
        public RegisterDomain createFromParcel(Parcel in) {
            return new RegisterDomain(in);
        }

        @Override
        public RegisterDomain[] newArray(int size) {
            return new RegisterDomain[size];
        }
    };

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

    public String getIdlink() {
        return idlink;
    }

    public void setIdlink(String idlink) {
        this.idlink = idlink;
    }

    public String getHosnum() {
        return hosnum;
    }

    public void setHosnum(String hosnum) {
        this.hosnum = hosnum;
    }

    public String getIdaddr() {
        return idaddr;
    }

    public void setIdaddr(String idaddr) {
        this.idaddr = idaddr;
    }

    public String getIdide() {
        return idide;
    }

    public void setIdide(String idide) {
        this.idide = idide;
    }

    public String getIdbhi() {
        return idbhi;
    }

    public void setIdbhi(String idbhi) {
        this.idbhi = idbhi;
    }

    public String getPatcode() {
        return patcode;
    }

    public void setPatcode(String patcode) {
        this.patcode = patcode;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getPurpos() {
        return purpos;
    }

    public void setPurpos(String purpos) {
        this.purpos = purpos;
    }

    public int getTyprec() {
        return typrec;
    }

    public void setTyprec(int typrec) {
        this.typrec = typrec;
    }

    public int getFormco() {
        return formco;
    }

    public void setFormco(int formco) {
        this.formco = formco;
    }

    public int getRfdoctor() {
        return rfdoctor;
    }

    public void setRfdoctor(int rfdoctor) {
        this.rfdoctor = rfdoctor;
    }

    public int getIntrod() {
        return introd;
    }

    public void setIntrod(int introd) {
        this.introd = introd;
    }

    public int getIdobject() {
        return idobject;
    }

    public void setIdobject(int idobject) {
        this.idobject = idobject;
    }

    public int getPricelist() {
        return pricelist;
    }

    public void setPricelist(int pricelist) {
        this.pricelist = pricelist;
    }

    public int getPromotions() {
        return promotions;
    }

    public void setPromotions(int promotions) {
        this.promotions = promotions;
    }

    public String getEmplocode() {
        return emplocode;
    }

    public void setEmplocode(String emplocode) {
        this.emplocode = emplocode;
    }

    public int getRelationsh() {
        return relationsh;
    }

    public void setRelationsh(int relationsh) {
        this.relationsh = relationsh;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public int getRoomservice() {
        return roomservice;
    }

    public void setRoomservice(int roomservice) {
        this.roomservice = roomservice;
    }

    public int getIdmedexa() {
        return idmedexa;
    }

    public void setIdmedexa(int idmedexa) {
        this.idmedexa = idmedexa;
    }

    public int getTypeexamination() {
        return typeexamination;
    }

    public void setTypeexamination(int typeexamination) {
        this.typeexamination = typeexamination;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsemergency() {
        return isemergency;
    }

    public void setIsemergency(int isemergency) {
        this.isemergency = isemergency;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeString(patid);
        dest.writeString(idlink);
        dest.writeString(hosnum);
        dest.writeString(idaddr);
        dest.writeString(idide);
        dest.writeString(idbhi);
        dest.writeString(patcode);
        dest.writeString(regdate);
        dest.writeString(purpos);
        dest.writeInt(typrec);
        dest.writeInt(formco);
        dest.writeInt(rfdoctor);
        dest.writeInt(introd);
        dest.writeInt(idobject);
        dest.writeInt(pricelist);
        dest.writeInt(promotions);
        dest.writeString(emplocode);
        dest.writeInt(relationsh);
        dest.writeString(symptoms);
        dest.writeInt(roomservice);
        dest.writeInt(idmedexa);
        dest.writeInt(typeexamination);
        dest.writeInt(status);
        dest.writeInt(isemergency);
        dest.writeString(attributes);
        dest.writeInt(active);
        dest.writeString(usercr);
        dest.writeString(timecr);
        dest.writeString(userup);
        dest.writeString(timeup);
        dest.writeString(computer);
    }
}
