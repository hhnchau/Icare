package ptt.vn.icaremobileapp.model.serviceitem;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MapPriceServiceItemHDomain implements Parcelable {
    private int siterf;
    private int id;
    private String code;
    private String name;
    private String decrp;
    private int sort;
    private int isdefault;
    private int status;
    private int active;
    private List<MapPriceServiceItemLDomain> lstPriceServiceValueObject;

    public MapPriceServiceItemHDomain() { }

    protected MapPriceServiceItemHDomain(Parcel in) {
        siterf = in.readInt();
        id = in.readInt();
        code = in.readString();
        name = in.readString();
        decrp = in.readString();
        sort = in.readInt();
        isdefault = in.readInt();
        status = in.readInt();
        active = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeInt(id);
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(decrp);
        dest.writeInt(sort);
        dest.writeInt(isdefault);
        dest.writeInt(status);
        dest.writeInt(active);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MapPriceServiceItemHDomain> CREATOR = new Creator<MapPriceServiceItemHDomain>() {
        @Override
        public MapPriceServiceItemHDomain createFromParcel(Parcel in) {
            return new MapPriceServiceItemHDomain(in);
        }

        @Override
        public MapPriceServiceItemHDomain[] newArray(int size) {
            return new MapPriceServiceItemHDomain[size];
        }
    };

    public int getSiterf() {
        return siterf;
    }

    public void setSiterf(int siterf) {
        this.siterf = siterf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecrp() {
        return decrp;
    }

    public void setDecrp(String decrp) {
        this.decrp = decrp;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(int isdefault) {
        this.isdefault = isdefault;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<MapPriceServiceItemLDomain> getLstPriceServiceValueObject() {
        return lstPriceServiceValueObject;
    }

    public void setLstPriceServiceValueObject(List<MapPriceServiceItemLDomain> lstPriceServiceValueObject) {
        this.lstPriceServiceValueObject = lstPriceServiceValueObject;
    }
}
