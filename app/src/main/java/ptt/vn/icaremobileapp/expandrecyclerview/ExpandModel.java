package ptt.vn.icaremobileapp.expandrecyclerview;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ExpandModel<T extends Parcelable> implements Parcelable {
    private String title;
    private List<T> details;

    public ExpandModel(String title, List<T> items) {
        this.title = title;
        this.details = items;
    }

    public String getTitle() {
        return title;
    }

    public List<T> getItems() {
        return details;
    }

    public int getItemCount() {
        return details == null ? 0 : details.size();
    }

    @NonNull
    @Override
    public String toString() {
        return "ExpandableGroup{" +
                "title='" + title + '\'' +
                ", items=" + details +
                '}';
    }

    protected ExpandModel(Parcel in) {
        title = in.readString();
        if (in.readByte() == 0x01) {
            int size = in.readInt();
            details = new ArrayList<T>(size);
            Class<?> type = (Class<?>) in.readSerializable();
            in.readList(details, type.getClassLoader());
        } else {
            details = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        if (details == null) {
            dest.writeByte((byte) (0x00));
            dest.writeInt(0);
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(details.size());
            final Class<?> objectsType = details.get(0).getClass();
            dest.writeSerializable(objectsType);
            dest.writeList(details);
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<ExpandModel> CREATOR =
            new Creator<ExpandModel>() {
                @Override
                public ExpandModel createFromParcel(Parcel in) {
                    return new ExpandModel(in);
                }

                @Override
                public ExpandModel[] newArray(int size) {
                    return new ExpandModel[size];
                }
            };
}
