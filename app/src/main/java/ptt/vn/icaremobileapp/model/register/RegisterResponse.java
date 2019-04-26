package ptt.vn.icaremobileapp.model.register;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class RegisterResponse extends BaseModel<RegisterDomain> {
    public RegisterResponse(int code, List<RegisterDomain> data) {
        super(code, data);
    }
}
