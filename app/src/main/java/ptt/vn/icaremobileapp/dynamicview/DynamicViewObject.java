package ptt.vn.icaremobileapp.dynamicview;

import android.os.Parcelable;

public class DynamicViewObject<T extends Parcelable> {
    public static final int BIOLOGICAL_DATE_TYPE = 0;
    public static final int DIAGNOSE_DATE_TYPE = 1;
    public static final int DRUG_DATE_TYPE = 2;
    public static final int SERVICE_DATE_TYPE = 3;
    public static final int DRUG_GROUP_TYPE = 4;
    public static final int DIAGNOSE_GROUP_TYPE = 5;
    public static final int SERVICE_GROUP_TYPE = 6;
    public static final int REASON_GROUP_TYPE = 7;
    public static final int BIOLOGICAL_GROUP_TYPE = 8;

    private T data;

    private int type;

    public DynamicViewObject() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
