package ptt.vn.icaremobileapp.model.serviceitem;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceItemDomain implements Parcelable {
    private int siterf;
    private int id;
    private int idgroupl2;
    private int equivalentid;
    private String code;
    private String namehi;
    private String namehosp;
    private int unitid;
    private int ishi;
    private int status;
    private String descrp;
    private int sort;
    private int selectquick;
    private int active;

    public ServiceItemDomain() {
    }


    protected ServiceItemDomain(Parcel in) {
        siterf = in.readInt();
        id = in.readInt();
        idgroupl2 = in.readInt();
        equivalentid = in.readInt();
        code = in.readString();
        namehi = in.readString();
        namehosp = in.readString();
        unitid = in.readInt();
        ishi = in.readInt();
        status = in.readInt();
        descrp = in.readString();
        sort = in.readInt();
        selectquick = in.readInt();
        active = in.readInt();
    }

    public static final Creator<ServiceItemDomain> CREATOR = new Creator<ServiceItemDomain>() {
        @Override
        public ServiceItemDomain createFromParcel(Parcel in) {
            return new ServiceItemDomain(in);
        }

        @Override
        public ServiceItemDomain[] newArray(int size) {
            return new ServiceItemDomain[size];
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

    public int getIdgroupl2() {
        return idgroupl2;
    }

    public void setIdgroupl2(int idgroupl2) {
        this.idgroupl2 = idgroupl2;
    }

    public int getEquivalentid() {
        return equivalentid;
    }

    public void setEquivalentid(int equivalentid) {
        this.equivalentid = equivalentid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNamehi() {
        return namehi;
    }

    public void setNamehi(String namehi) {
        this.namehi = namehi;
    }

    public String getNamehosp() {
        return namehosp;
    }

    public void setNamehosp(String namehosp) {
        this.namehosp = namehosp;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public int getIshi() {
        return ishi;
    }

    public void setIshi(int ishi) {
        this.ishi = ishi;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescrp() {
        return descrp;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getSelectquick() {
        return selectquick;
    }

    public void setSelectquick(int selectquick) {
        this.selectquick = selectquick;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeInt(id);
        dest.writeInt(idgroupl2);
        dest.writeInt(equivalentid);
        dest.writeString(code);
        dest.writeString(namehi);
        dest.writeString(namehosp);
        dest.writeInt(unitid);
        dest.writeInt(ishi);
        dest.writeInt(status);
        dest.writeString(descrp);
        dest.writeInt(sort);
        dest.writeInt(selectquick);
        dest.writeInt(active);
    }
}
