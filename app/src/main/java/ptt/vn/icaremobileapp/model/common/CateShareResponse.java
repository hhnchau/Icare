package ptt.vn.icaremobileapp.model.common;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class CateShareResponse extends BaseModel<CateSharehDomain> {
    public CateShareResponse(int code, List<CateSharehDomain> data) {
        super(code, data);
    }
}
