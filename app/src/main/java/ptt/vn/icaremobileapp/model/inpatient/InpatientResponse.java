package ptt.vn.icaremobileapp.model.inpatient;


import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class InpatientResponse extends BaseModel<InpatientDomain> {

    public InpatientResponse(int code, List<InpatientDomain> data) {
        super(code, data);
    }
}
