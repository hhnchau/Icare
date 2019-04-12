package ptt.vn.icaremobileapp.model.pharmacy;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class PhaInventoryResponse extends BaseModel<ResultResponse> {

    public PhaInventoryResponse(int code, List<ResultResponse> data) {
        super(code, data);
    }
}
