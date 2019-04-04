package ptt.vn.icaremobileapp.expandrecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailModel implements Parcelable {
    private String name;

    public DetailModel() {
    }

    public DetailModel(String name) {
        this.name = name;
    }

    protected DetailModel(Parcel in) {
        name = in.readString();
    }

    public static final Creator<DetailModel> CREATOR = new Creator<DetailModel>() {
        @Override
        public DetailModel createFromParcel(Parcel in) {
            return new DetailModel(in);
        }

        @Override
        public DetailModel[] newArray(int size) {
            return new DetailModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
