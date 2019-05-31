package ptt.vn.icaremobileapp.model.register;

import android.os.Parcel;
import android.os.Parcelable;

public class RegisterHi implements Parcelable {
    private String idline;

    private String exemptions;

    private int ratehi;

    private int ratepay;

    private int level;

    private int reason;

    private String reasonname;

    private long minpay;

    private long maxpay;

    private long salary;

    private String file;

    private String filename;

    private int rateother;

    public RegisterHi() {
    }

    protected RegisterHi(Parcel in) {
        idline = in.readString();
        exemptions = in.readString();
        ratehi = in.readInt();
        ratepay = in.readInt();
        level = in.readInt();
        reason = in.readInt();
        reasonname = in.readString();
        minpay = in.readLong();
        maxpay = in.readLong();
        salary = in.readLong();
        file = in.readString();
        filename = in.readString();
        rateother = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idline);
        dest.writeString(exemptions);
        dest.writeInt(ratehi);
        dest.writeInt(ratepay);
        dest.writeInt(level);
        dest.writeInt(reason);
        dest.writeString(reasonname);
        dest.writeLong(minpay);
        dest.writeLong(maxpay);
        dest.writeLong(salary);
        dest.writeString(file);
        dest.writeString(filename);
        dest.writeInt(rateother);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RegisterHi> CREATOR = new Creator<RegisterHi>() {
        @Override
        public RegisterHi createFromParcel(Parcel in) {
            return new RegisterHi(in);
        }

        @Override
        public RegisterHi[] newArray(int size) {
            return new RegisterHi[size];
        }
    };

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public String getExemptions() {
        return exemptions;
    }

    public void setExemptions(String exemptions) {
        this.exemptions = exemptions;
    }

    public int getRatehi() {
        return ratehi;
    }

    public void setRatehi(int ratehi) {
        this.ratehi = ratehi;
    }

    public int getRatepay() {
        return ratepay;
    }

    public void setRatepay(int ratepay) {
        this.ratepay = ratepay;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public String getReasonname() {
        return reasonname;
    }

    public void setReasonname(String reasonname) {
        this.reasonname = reasonname;
    }

    public long getMinpay() {
        return minpay;
    }

    public void setMinpay(long minpay) {
        this.minpay = minpay;
    }

    public long getMaxpay() {
        return maxpay;
    }

    public void setMaxpay(long maxpay) {
        this.maxpay = maxpay;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getRateother() {
        return rateother;
    }

    public void setRateother(int rateother) {
        this.rateother = rateother;
    }
}
