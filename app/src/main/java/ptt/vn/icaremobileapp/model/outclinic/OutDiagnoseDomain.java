package ptt.vn.icaremobileapp.model.outclinic;

import android.os.Parcel;
import android.os.Parcelable;

public class OutDiagnoseDomain implements Parcelable {
    private String idline;
    private int idicdx;
    private int idmedexa;
    private String nameicdx;
    private String codeicdx;
    private int primary;
    private int active;
    private int sort;
    private String nameen;

    public OutDiagnoseDomain() {
    }

    protected OutDiagnoseDomain(Parcel in) {
        idline = in.readString();
        idicdx = in.readInt();
        idmedexa = in.readInt();
        nameicdx = in.readString();
        codeicdx = in.readString();
        primary = in.readInt();
        active = in.readInt();
        sort = in.readInt();
        nameen = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idline);
        dest.writeInt(idicdx);
        dest.writeInt(idmedexa);
        dest.writeString(nameicdx);
        dest.writeString(codeicdx);
        dest.writeInt(primary);
        dest.writeInt(active);
        dest.writeInt(sort);
        dest.writeString(nameen);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OutDiagnoseDomain> CREATOR = new Creator<OutDiagnoseDomain>() {
        @Override
        public OutDiagnoseDomain createFromParcel(Parcel in) {
            return new OutDiagnoseDomain(in);
        }

        @Override
        public OutDiagnoseDomain[] newArray(int size) {
            return new OutDiagnoseDomain[size];
        }
    };

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public int getIdicdx() {
        return idicdx;
    }

    public void setIdicdx(int idicdx) {
        this.idicdx = idicdx;
    }

    public int getIdmedexa() {
        return idmedexa;
    }

    public void setIdmedexa(int idmedexa) {
        this.idmedexa = idmedexa;
    }

    public String getNameicdx() {
        return nameicdx;
    }

    public void setNameicdx(String nameicdx) {
        this.nameicdx = nameicdx;
    }

    public String getCodeicdx() {
        return codeicdx;
    }

    public void setCodeicdx(String codeicdx) {
        this.codeicdx = codeicdx;
    }

    public int getPrimary() {
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }
}
