package ptt.vn.icaremobileapp.model.inpatient;

import java.util.List;

public class ResultInpatient {
    private int Code;
    private List<InpatientDomain> Data;

    public int getCode() {
        return Code;
    }

    public List<InpatientDomain> getData() {
        return Data;
    }
}
