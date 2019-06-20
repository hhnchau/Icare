package ptt.vn.icaremobileapp.model.outclinic;

import android.os.Parcel;
import android.os.Parcelable;

import ptt.vn.icaremobileapp.model.Parent;

public class OutServiceOrderHeaderDomain extends Parent<OutServiceOrderDomain> implements Parcelable {
    private String title;

    public OutServiceOrderHeaderDomain() {
    }

    protected OutServiceOrderHeaderDomain(Parcel in) {
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OutServiceOrderHeaderDomain> CREATOR = new Creator<OutServiceOrderHeaderDomain>() {
        @Override
        public OutServiceOrderHeaderDomain createFromParcel(Parcel in) {
            return new OutServiceOrderHeaderDomain(in);
        }

        @Override
        public OutServiceOrderHeaderDomain[] newArray(int size) {
            return new OutServiceOrderHeaderDomain[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
