package ptt.vn.icaremobileapp.model.account;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class AccountResponse extends BaseModel<AccountDomain> {
    public AccountResponse(int code, List<AccountDomain> data) {
        super(code, data);
    }
}
