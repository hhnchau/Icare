package ptt.vn.icaremobileapp.model.pharmacy;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ResultResponse implements Parcelable {
    private List<PhaInventoryDomain> Result;

    public ResultResponse(List<PhaInventoryDomain> result) {
        Result = result;
    }

    protected ResultResponse(Parcel in) {
        Result = in.createTypedArrayList(PhaInventoryDomain.CREATOR);
    }

    public static final Creator<ResultResponse> CREATOR = new Creator<ResultResponse>() {
        @Override
        public ResultResponse createFromParcel(Parcel in) {
            return new ResultResponse(in);
        }

        @Override
        public ResultResponse[] newArray(int size) {
            return new ResultResponse[size];
        }
    };

    public List<PhaInventoryDomain> getResult() {
        return Result;
    }

    public void setResult(List<PhaInventoryDomain> result) {
        Result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(Result);
    }
}
