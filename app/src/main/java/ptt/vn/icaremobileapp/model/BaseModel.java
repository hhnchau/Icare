package ptt.vn.icaremobileapp.model;

import android.os.Parcelable;

import java.util.List;

public class BaseModel<T extends Parcelable> {
    private int Code;
    private List<T> Data;

    public BaseModel(int code, List<T> data) {
        Code = code;
        Data = data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public List<T> getData() {
        return Data;
    }

    public void setData(List<T> data) {
        Data = data;
    }
}
