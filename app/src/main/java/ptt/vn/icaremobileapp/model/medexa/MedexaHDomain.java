package ptt.vn.icaremobileapp.model.medexa;

import android.os.Parcel;
import android.os.Parcelable;

public class MedexaHDomain implements Parcelable {
    private int siterf;
    private int id;
    private int iddepart;
    private String code;
    private String name;
    private String decrp;
    private int sort;
    private int active;
    private String usercontrol;
    private String lablecontrol;
    private String stored;

    public MedexaHDomain() {
    }

    protected MedexaHDomain(Parcel in) {
        siterf = in.readInt();
        id = in.readInt();
        iddepart = in.readInt();
        code = in.readString();
        name = in.readString();
        decrp = in.readString();
        sort = in.readInt();
        active = in.readInt();
        usercontrol = in.readString();
        lablecontrol = in.readString();
        stored = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeInt(id);
        dest.writeInt(iddepart);
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(decrp);
        dest.writeInt(sort);
        dest.writeInt(active);
        dest.writeString(usercontrol);
        dest.writeString(lablecontrol);
        dest.writeString(stored);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MedexaHDomain> CREATOR = new Creator<MedexaHDomain>() {
        @Override
        public MedexaHDomain createFromParcel(Parcel in) {
            return new MedexaHDomain(in);
        }

        @Override
        public MedexaHDomain[] newArray(int size) {
            return new MedexaHDomain[size];
        }
    };

    public int getSiterf() {
        return siterf;
    }

    public void setSiterf(int siterf) {
        this.siterf = siterf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddepart() {
        return iddepart;
    }

    public void setIddepart(int iddepart) {
        this.iddepart = iddepart;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecrp() {
        return decrp;
    }

    public void setDecrp(String decrp) {
        this.decrp = decrp;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getUsercontrol() {
        return usercontrol;
    }

    public void setUsercontrol(String usercontrol) {
        this.usercontrol = usercontrol;
    }

    public String getLablecontrol() {
        return lablecontrol;
    }

    public void setLablecontrol(String lablecontrol) {
        this.lablecontrol = lablecontrol;
    }

    public String getStored() {
        return stored;
    }

    public void setStored(String stored) {
        this.stored = stored;
    }
}
