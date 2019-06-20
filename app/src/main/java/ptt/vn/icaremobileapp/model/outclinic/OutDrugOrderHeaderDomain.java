package ptt.vn.icaremobileapp.model.outclinic;

import android.os.Parcel;
import android.os.Parcelable;

import ptt.vn.icaremobileapp.model.Parent;

public class OutDrugOrderHeaderDomain extends Parent<OutDrugOrderDomain> implements Parcelable {
    private String title;

    public OutDrugOrderHeaderDomain() {
    }

    protected OutDrugOrderHeaderDomain(Parcel in) {
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

    public static final Creator<OutDrugOrderHeaderDomain> CREATOR = new Creator<OutDrugOrderHeaderDomain>() {
        @Override
        public OutDrugOrderHeaderDomain createFromParcel(Parcel in) {
            return new OutDrugOrderHeaderDomain(in);
        }

        @Override
        public OutDrugOrderHeaderDomain[] newArray(int size) {
            return new OutDrugOrderHeaderDomain[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
