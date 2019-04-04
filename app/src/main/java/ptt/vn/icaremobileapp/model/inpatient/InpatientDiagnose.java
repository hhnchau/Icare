package ptt.vn.icaremobileapp.model.inpatient;

public class InpatientDiagnose {
    private String idline;
    private int idicd;
    private String nameicdvn;
    private String nameicdeng;
    private int type;
    private int primary;
    private String attributes;
    private int active;

    public InpatientDiagnose() {
    }

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public int getIdicd() {
        return idicd;
    }

    public void setIdicd(int idicd) {
        this.idicd = idicd;
    }

    public String getNameicdvn() {
        return nameicdvn;
    }

    public void setNameicdvn(String nameicdvn) {
        this.nameicdvn = nameicdvn;
    }

    public String getNameicdeng() {
        return nameicdeng;
    }

    public void setNameicdeng(String nameicdeng) {
        this.nameicdeng = nameicdeng;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrimary() {
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
