package ptt.vn.icaremobileapp.model.outclinic;

import android.os.Parcel;
import android.os.Parcelable;

import ptt.vn.icaremobileapp.model.Parent;

public class OutDiagnoseHeaderDomain extends Parent<OutDiagnoseDomain> implements Parcelable {
    private String title;

    public OutDiagnoseHeaderDomain() {
    }

    protected OutDiagnoseHeaderDomain(Parcel in) {
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

    public static final Creator<OutDiagnoseHeaderDomain> CREATOR = new Creator<OutDiagnoseHeaderDomain>() {
        @Override
        public OutDiagnoseHeaderDomain createFromParcel(Parcel in) {
            return new OutDiagnoseHeaderDomain(in);
        }

        @Override
        public OutDiagnoseHeaderDomain[] newArray(int size) {
            return new OutDiagnoseHeaderDomain[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
