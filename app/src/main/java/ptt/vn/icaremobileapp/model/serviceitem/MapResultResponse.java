package ptt.vn.icaremobileapp.model.serviceitem;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MapResultResponse implements Parcelable {
    private List<MapPriceServiceItemHDomain> Result;

    public MapResultResponse(List<MapPriceServiceItemHDomain> result) {
        Result = result;
    }

    protected MapResultResponse(Parcel in) {
        Result = in.createTypedArrayList(MapPriceServiceItemHDomain.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(Result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MapResultResponse> CREATOR = new Creator<MapResultResponse>() {
        @Override
        public MapResultResponse createFromParcel(Parcel in) {
            return new MapResultResponse(in);
        }

        @Override
        public MapResultResponse[] newArray(int size) {
            return new MapResultResponse[size];
        }
    };

    public List<MapPriceServiceItemHDomain> getResult() {
        return Result;
    }

    public void setResult(List<MapPriceServiceItemHDomain> result) {
        Result = result;
    }
}
