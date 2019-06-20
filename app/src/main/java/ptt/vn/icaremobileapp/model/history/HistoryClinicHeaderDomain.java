package ptt.vn.icaremobileapp.model.history;

import android.os.Parcel;
import android.os.Parcelable;

import ptt.vn.icaremobileapp.model.Parent;

public class HistoryClinicHeaderDomain extends Parent<HistoryClinicDomain> implements Parcelable {
    private String title;

    public HistoryClinicHeaderDomain() {
    }

    protected HistoryClinicHeaderDomain(Parcel in) {
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

    public static final Creator<HistoryClinicHeaderDomain> CREATOR = new Creator<HistoryClinicHeaderDomain>() {
        @Override
        public HistoryClinicHeaderDomain createFromParcel(Parcel in) {
            return new HistoryClinicHeaderDomain(in);
        }

        @Override
        public HistoryClinicHeaderDomain[] newArray(int size) {
            return new HistoryClinicHeaderDomain[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
