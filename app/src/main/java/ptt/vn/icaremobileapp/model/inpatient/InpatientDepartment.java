package ptt.vn.icaremobileapp.model.inpatient;

import android.os.Parcel;
import android.os.Parcelable;

public class InpatientDepartment implements Parcelable {
    private int siterf;
    private String idline;
    private String idlink;
    private String transferfromdate;
    private int idmedexatranferfrom;
    private String intodate;
    private int idmedexacurrent;
    private int iddoctor;
    private String attributes;
    private int idstatustranfer;
    private int active;
    private String usercr;
    private String timecr;
    private String userup;
    private String timeup;
    private String computer;
    private int idlineroom;
    private int idlinebed;


    public InpatientDepartment() {
    }

    protected InpatientDepartment(Parcel in) {
        siterf = in.readInt();
        idline = in.readString();
        idlink = in.readString();
        transferfromdate = in.readString();
        idmedexatranferfrom = in.readInt();
        intodate = in.readString();
        idmedexacurrent = in.readInt();
        iddoctor = in.readInt();
        attributes = in.readString();
        idstatustranfer = in.readInt();
        active = in.readInt();
        usercr = in.readString();
        timecr = in.readString();
        userup = in.readString();
        timeup = in.readString();
        computer = in.readString();
        idlineroom = in.readInt();
        idlinebed = in.readInt();
    }

    public static final Creator<InpatientDepartment> CREATOR = new Creator<InpatientDepartment>() {
        @Override
        public InpatientDepartment createFromParcel(Parcel in) {
            return new InpatientDepartment(in);
        }

        @Override
        public InpatientDepartment[] newArray(int size) {
            return new InpatientDepartment[size];
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

    public String getTransferfromdate() {
        return transferfromdate;
    }

    public void setTransferfromdate(String transferfromdate) {
        this.transferfromdate = transferfromdate;
    }

    public int getIdmedexatranferfrom() {
        return idmedexatranferfrom;
    }

    public void setIdmedexatranferfrom(int idmedexatranferfrom) {
        this.idmedexatranferfrom = idmedexatranferfrom;
    }

    public String getIntodate() {
        return intodate;
    }

    public void setIntodate(String intodate) {
        this.intodate = intodate;
    }

    public int getIdmedexacurrent() {
        return idmedexacurrent;
    }

    public void setIdmedexacurrent(int idmedexacurrent) {
        this.idmedexacurrent = idmedexacurrent;
    }

    public int getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(int iddoctor) {
        this.iddoctor = iddoctor;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getIdstatustranfer() {
        return idstatustranfer;
    }

    public void setIdstatustranfer(int idstatustranfer) {
        this.idstatustranfer = idstatustranfer;
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

    public int getIdlineroom() {
        return idlineroom;
    }

    public void setIdlineroom(int idlineroom) {
        this.idlineroom = idlineroom;
    }

    public int getIdlinebed() {
        return idlinebed;
    }

    public void setIdlinebed(int idlinebed) {
        this.idlinebed = idlinebed;
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
        dest.writeString(transferfromdate);
        dest.writeInt(idmedexatranferfrom);
        dest.writeString(intodate);
        dest.writeInt(idmedexacurrent);
        dest.writeInt(iddoctor);
        dest.writeString(attributes);
        dest.writeInt(idstatustranfer);
        dest.writeInt(active);
        dest.writeString(usercr);
        dest.writeString(timecr);
        dest.writeString(userup);
        dest.writeString(timeup);
        dest.writeString(computer);
        dest.writeInt(idlineroom);
        dest.writeInt(idlinebed);
    }
}
