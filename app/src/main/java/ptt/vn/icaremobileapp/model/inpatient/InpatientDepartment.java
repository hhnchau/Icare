package ptt.vn.icaremobileapp.model.inpatient;

public class InpatientDepartment {
    private String idline;
    private String idlink;
    private String transferfromdate;
    private int idmedexatranferfrom;
    private String intodate;
    private int idmedexacurrent;
    private int iddoctor;
    private int idlineroom;
    private int idlinebed;
    private String attributes;
    private int idstatustranfer;
    private int active;

    public InpatientDepartment() {
    }

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public String getIdlink() {
        return idlink;
    }

    public void setIdlink(String idlink) {
        this.idlink = idlink;
    }

    public String getTransferfromdate() {
        return transferfromdate;
    }

    public void setTransferfromdate(String transferfromdate) {
        this.transferfromdate = transferfromdate;
    }

    public int getIdmedexatranferfrom() {
        return idmedexatranferfrom;
    }

    public void setIdmedexatranferfrom(int idmedexatranferfrom) {
        this.idmedexatranferfrom = idmedexatranferfrom;
    }

    public String getIntodate() {
        return intodate;
    }

    public void setIntodate(String intodate) {
        this.intodate = intodate;
    }

    public int getIdmedexacurrent() {
        return idmedexacurrent;
    }

    public void setIdmedexacurrent(int idmedexacurrent) {
        this.idmedexacurrent = idmedexacurrent;
    }

    public int getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(int iddoctor) {
        this.iddoctor = iddoctor;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getIdstatustranfer() {
        return idstatustranfer;
    }

    public void setIdstatustranfer(int idstatustranfer) {
        this.idstatustranfer = idstatustranfer;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getIdlineroom() {
        return idlineroom;
    }

    public void setIdlineroom(int idlineroom) {
        this.idlineroom = idlineroom;
    }

    public int getIdlinebed() {
        return idlinebed;
    }

    public void setIdlinebed(int idlinebed) {
        this.idlinebed = idlinebed;
    }
}
