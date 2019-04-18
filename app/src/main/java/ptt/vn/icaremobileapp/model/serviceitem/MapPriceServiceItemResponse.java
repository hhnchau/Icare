package ptt.vn.icaremobileapp.model.serviceitem;

import java.util.List;

import ptt.vn.icaremobileapp.model.BaseModel;
import ptt.vn.icaremobileapp.model.pharmacy.ResultResponse;

public class MapPriceServiceItemResponse extends BaseModel<MapResultResponse> {
    public MapPriceServiceItemResponse(int code, List<MapResultResponse> data) {
        super(code, data);
    }
}
