package ptt.vn.icaremobileapp.model.account;

import android.os.Parcel;
import android.os.Parcelable;

public class AccountDomain implements Parcelable {
    private String idline;
    private int iduser;
    private String username;
    private String password;
    private int active;
    private int siterf;
    private String usercr;
    private String timecr;

    public AccountDomain() {
    }

    protected AccountDomain(Parcel in) {
        idline = in.readString();
        iduser = in.readInt();
        username = in.readString();
        password = in.readString();
        active = in.readInt();
        siterf = in.readInt();
        usercr = in.readString();
        timecr = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idline);
        dest.writeInt(iduser);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeInt(active);
        dest.writeInt(siterf);
        dest.writeString(usercr);
        dest.writeString(timecr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AccountDomain> CREATOR = new Creator<AccountDomain>() {
        @Override
        public AccountDomain createFromParcel(Parcel in) {
            return new AccountDomain(in);
        }

        @Override
        public AccountDomain[] newArray(int size) {
            return new AccountDomain[size];
        }
    };

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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
}
