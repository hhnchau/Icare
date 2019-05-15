package ptt.vn.icaremobileapp.model.hi;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kingpes on 9/7/18.
 */

public class HiCard implements Parcelable {
    private String sn;
    private String name;
    private String birthday;
    private int gender;
    private String address;
    private String firstRegistration;
    private String startDate;
    private String endDate;
    private String releaseDate;
    private String managerCode;
    private String parentName;
    private String objectCode;
    private String timeOver5Year;
    private String stringTest;
    private String charEnd;

    public HiCard() {
    }

    private HiCard(Parcel in) {
        sn = in.readString();
        name = in.readString();
        birthday = in.readString();
        gender = in.readInt();
        address = in.readString();
        firstRegistration = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        releaseDate = in.readString();
        managerCode = in.readString();
        parentName = in.readString();
        objectCode = in.readString();
        timeOver5Year = in.readString();
        stringTest = in.readString();
        charEnd = in.readString();
    }

    public static final Creator<HiCard> CREATOR = new Creator<HiCard>() {
        @Override
        public HiCard createFromParcel(Parcel in) {
            return new HiCard(in);
        }

        @Override
        public HiCard[] newArray(int size) {
            return new HiCard[size];
        }
    };

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(String firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getTimeOver5Year() {
        return timeOver5Year;
    }

    public void setTimeOver5Year(String timeOver5Year) {
        this.timeOver5Year = timeOver5Year;
    }

    public String getStringTest() {
        return stringTest;
    }

    public void setStringTest(String stringTest) {
        this.stringTest = stringTest;
    }

    public String getCharEnd() {
        return charEnd;
    }

    public void setCharEnd(String charEnd) {
        this.charEnd = charEnd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sn);
        dest.writeString(name);
        dest.writeString(birthday);
        dest.writeInt(gender);
        dest.writeString(address);
        dest.writeString(firstRegistration);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(releaseDate);
        dest.writeString(managerCode);
        dest.writeString(parentName);
        dest.writeString(objectCode);
        dest.writeString(timeOver5Year);
        dest.writeString(stringTest);
        dest.writeString(charEnd);
    }
}
