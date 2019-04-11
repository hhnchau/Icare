package ptt.vn.icaremobileapp.model.pharmacy;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class PhaInventoryResponse extends BaseModel<PhaInventoryDomain> {
    public PhaInventoryResponse(int code, List<PhaInventoryDomain> data) {
        super(code, data);
    }
}
