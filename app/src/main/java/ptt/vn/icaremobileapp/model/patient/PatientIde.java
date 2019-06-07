package ptt.vn.icaremobileapp.model.patient;

import android.os.Parcel;
import android.os.Parcelable;

public class PatientIde implements Parcelable {
    private String idline;
    private String cardid;
    private String daterg;
    private String place;
    private String imgpth;
    private int status;
    private String image;


    public PatientIde() {
    }


    protected PatientIde(Parcel in) {
        idline = in.readString();
        cardid = in.readString();
        daterg = in.readString();
        place = in.readString();
        imgpth = in.readString();
        status = in.readInt();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idline);
        dest.writeString(cardid);
        dest.writeString(daterg);
        dest.writeString(place);
        dest.writeString(imgpth);
        dest.writeInt(status);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PatientIde> CREATOR = new Creator<PatientIde>() {
        @Override
        public PatientIde createFromParcel(Parcel in) {
            return new PatientIde(in);
        }

        @Override
        public PatientIde[] newArray(int size) {
            return new PatientIde[size];
        }
    };

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public String getDaterg() {
        return daterg;
    }

    public void setDaterg(String daterg) {
        this.daterg = daterg;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImgpth() {
        return imgpth;
    }

    public void setImgpth(String imgpth) {
        this.imgpth = imgpth;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
