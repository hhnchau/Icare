package ptt.vn.icaremobileapp.model.patient;

public class PatientAdrr {
    private String idline;
    private int idprovin;
    private int iddistric;
    private int idward;
    private String nofhus;
    private String street;
    private String addresfull;
    private int status;
    private int active;

    public PatientAdrr() {
    }

    public int getIdprovin() {
        return idprovin;
    }

    public void setIdprovin(int idprovin) {
        this.idprovin = idprovin;
    }

    public int getIddistric() {
        return iddistric;
    }

    public void setIddistric(int iddistric) {
        this.iddistric = iddistric;
    }

    public int getIdward() {
        return idward;
    }

    public void setIdward(int idward) {
        this.idward = idward;
    }

    public String getNofhus() {
        return nofhus;
    }

    public void setNofhus(String nofhus) {
        this.nofhus = nofhus;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddresfull() {
        return addresfull;
    }

    public void setAddresfull(String addresfull) {
        this.addresfull = addresfull;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIdline() {
        return idline;
    }

    public void setIdline(String idline) {
        this.idline = idline;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
