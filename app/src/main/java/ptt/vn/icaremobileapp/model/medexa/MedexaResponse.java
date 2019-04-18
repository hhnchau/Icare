package ptt.vn.icaremobileapp.model.medexa;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class MedexaResponse extends BaseModel<MedexaHDomain> {
    public MedexaResponse(int code, List<MedexaHDomain> data) {
        super(code, data);
    }
}
