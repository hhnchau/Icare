package ptt.vn.icaremobileapp.model.discount;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class DiscountResponse extends BaseModel<DiscountHDomain> {
    public DiscountResponse(int code, List<DiscountHDomain> data) {
        super(code, data);
    }
}
