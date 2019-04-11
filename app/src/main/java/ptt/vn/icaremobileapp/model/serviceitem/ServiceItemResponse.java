package ptt.vn.icaremobileapp.model.serviceitem;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;

public class ServiceItemResponse extends BaseModel<ServiceItemDomain> {
    public ServiceItemResponse(int code, List<ServiceItemDomain> data) {
        super(code, data);
    }
}
