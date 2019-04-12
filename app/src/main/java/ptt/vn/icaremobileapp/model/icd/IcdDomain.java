package ptt.vn.icaremobileapp.model.icd;

import android.os.Parcel;
import android.os.Parcelable;

public class IcdDomain implements Parcelable {
    private int siterf;
    private int id;
    private String code;
    private String name;
    private String nameen;
    private int sort;
    private String decrp;
    private String sortchap;
    private String codechap;
    private String namechap;
    private String codegroup;
    private String namegroup;
    private String codetype;
    private String nametype;
    private String codekit;

    public IcdDomain() {
    }

    protected IcdDomain(Parcel in) {
        siterf = in.readInt();
        id = in.readInt();
        code = in.readString();
        name = in.readString();
        nameen = in.readString();
        sort = in.readInt();
        decrp = in.readString();
        sortchap = in.readString();
        codechap = in.readString();
        namechap = in.readString();
        codegroup = in.readString();
        namegroup = in.readString();
        codetype = in.readString();
        nametype = in.readString();
        codekit = in.readString();
    }

    public static final Creator<IcdDomain> CREATOR = new Creator<IcdDomain>() {
        @Override
        public IcdDomain createFromParcel(Parcel in) {
            return new IcdDomain(in);
        }

        @Override
        public IcdDomain[] newArray(int size) {
            return new IcdDomain[size];
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

    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getDecrp() {
        return decrp;
    }

    public void setDecrp(String decrp) {
        this.decrp = decrp;
    }

    public String getSortchap() {
        return sortchap;
    }

    public void setSortchap(String sortchap) {
        this.sortchap = sortchap;
    }

    public String getCodechap() {
        return codechap;
    }

    public void setCodechap(String codechap) {
        this.codechap = codechap;
    }

    public String getNamechap() {
        return namechap;
    }

    public void setNamechap(String namechap) {
        this.namechap = namechap;
    }

    public String getCodegroup() {
        return codegroup;
    }

    public void setCodegroup(String codegroup) {
        this.codegroup = codegroup;
    }

    public String getNamegroup() {
        return namegroup;
    }

    public void setNamegroup(String namegroup) {
        this.namegroup = namegroup;
    }

    public String getCodetype() {
        return codetype;
    }

    public void setCodetype(String codetype) {
        this.codetype = codetype;
    }

    public String getNametype() {
        return nametype;
    }

    public void setNametype(String nametype) {
        this.nametype = nametype;
    }

    public String getCodekit() {
        return codekit;
    }

    public void setCodekit(String codekit) {
        this.codekit = codekit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(siterf);
        dest.writeInt(id);
        dest.writeString(code);
        dest.writeString(name);
        dest.writeString(nameen);
        dest.writeInt(sort);
        dest.writeString(decrp);
        dest.writeString(sortchap);
        dest.writeString(codechap);
        dest.writeString(namechap);
        dest.writeString(codegroup);
        dest.writeString(namegroup);
        dest.writeString(codetype);
        dest.writeString(nametype);
        dest.writeString(codekit);
    }
}
