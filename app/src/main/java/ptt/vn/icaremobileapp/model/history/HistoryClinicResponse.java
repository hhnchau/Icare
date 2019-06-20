package ptt.vn.icaremobileapp.model.history;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class HistoryClinicResponse extends BaseModel<HistoryClinicDomain> {

    public HistoryClinicResponse(int code, List<HistoryClinicDomain> data) {
        super(code, data);
    }
}
