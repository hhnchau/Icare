package ptt.vn.icaremobileapp.model.patient;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class PatientResponse extends BaseModel<PatientDomain> {
    public PatientResponse(int code, List<PatientDomain> data) {
        super(code, data);
    }
}
