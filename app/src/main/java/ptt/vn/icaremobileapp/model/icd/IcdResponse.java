package ptt.vn.icaremobileapp.model.icd;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class IcdResponse extends BaseModel<IcdDomain> {
    public IcdResponse(int code, List<IcdDomain> data) {
        super(code, data);
    }
}
