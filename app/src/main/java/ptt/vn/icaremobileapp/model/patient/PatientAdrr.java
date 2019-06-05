package ptt.vn.icaremobileapp.model.patient;

import android.os.Parcel;
import android.os.Parcelable;

public class PatientAdrr implements Parcelable {
    private String idline;
    private int idprovin;
    private String nameprovin;
    private int iddistric;
    private String namedistric;
    private int idward;
    private String nameward;
    private String nofhus;
    private String street;
    private String addresfull;
    private int status;
    private int active;

    public PatientAdrr() {
    }

    protected PatientAdrr(Parcel in) {
        idline = in.readString();
        idprovin = in.readInt();
        nameprovin = in.readString();
        iddistric = in.readInt();
        namedistric = in.readString();
        idward = in.readInt();
        nameward = in.readString();
        nofhus = in.readString();
        street = in.readString();
        addresfull = in.readString();
        status = in.readInt();
        active = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idline);
        dest.writeInt(idprovin);
        dest.writeString(nameprovin);
        dest.writeInt(iddistric);
        dest.writeString(namedistric);
        dest.writeInt(idward);
        dest.writeString(nameward);
        dest.writeString(nofhus);
        dest.writeString(street);
        dest.writeString(addresfull);
        dest.writeInt(status);
        dest.writeInt(active);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PatientAdrr> CREATOR = new Creator<PatientAdrr>() {
        @Override
        public PatientAdrr createFromParcel(Parcel in) {
            return new PatientAdrr(in);
        }

        @Override
        public PatientAdrr[] newArray(int size) {
            return new PatientAdrr[size];
        }
    };

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public int getIdprovin() {
        return idprovin;
    }

    public void setIdprovin(int idprovin) {
        this.idprovin = idprovin;
    }

    public String getNameprovin() {
        return nameprovin;
    }

    public void setNameprovin(String nameprovin) {
        this.nameprovin = nameprovin;
    }

    public int getIddistric() {
        return iddistric;
    }

    public void setIddistric(int iddistric) {
        this.iddistric = iddistric;
    }

    public String getNamedistric() {
        return namedistric;
    }

    public void setNamedistric(String namedistric) {
        this.namedistric = namedistric;
    }

    public int getIdward() {
        return idward;
    }

    public void setIdward(int idward) {
        this.idward = idward;
    }

    public String getNameward() {
        return nameward;
    }

    public void setNameward(String nameward) {
        this.nameward = nameward;
    }

    public String getNofhus() {
        return nofhus;
    }

    public void setNofhus(String nofhus) {
        this.nofhus = nofhus;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddresfull() {
        return addresfull;
    }

    public void setAddresfull(String addresfull) {
        this.addresfull = addresfull;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
