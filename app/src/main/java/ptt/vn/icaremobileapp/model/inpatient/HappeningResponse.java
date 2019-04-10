package ptt.vn.icaremobileapp.model.inpatient;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class HappeningResponse extends BaseModel<HappeningDomain> {
    public HappeningResponse(int code, List<HappeningDomain> data) {
        super(code, data);
    }
}
