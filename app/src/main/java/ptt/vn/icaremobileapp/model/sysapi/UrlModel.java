package ptt.vn.icaremobileapp.model.sysapi;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class UrlModel implements Parcelable {
    private String code;
    private String module;
    private String fullpath;
    private List<UrlModel> lstApiValueObject;

    public UrlModel() {
    }

    private UrlModel(Parcel in) {
        code = in.readString();
        module = in.readString();
        fullpath = in.readString();
        lstApiValueObject = in.createTypedArrayList(UrlModel.CREATOR);
    }

    public static final Creator<UrlModel> CREATOR = new Creator<UrlModel>() {
        @Override
        public UrlModel createFromParcel(Parcel in) {
            return new UrlModel(in);
        }

        @Override
        public UrlModel[] newArray(int size) {
            return new UrlModel[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    public List<UrlModel> getLstApiValueObject() {
        return lstApiValueObject;
    }

    public void setLstApiValueObject(List<UrlModel> lstApiValueObject) {
        this.lstApiValueObject = lstApiValueObject;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(module);
        dest.writeString(fullpath);
        dest.writeTypedList(lstApiValueObject);
    }
}
