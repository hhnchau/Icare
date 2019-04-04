package ptt.vn.icaremobileapp.model.filter;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;

import java.util.List;

public class FilterModel {
    private int offset;
    private int limit;
    private String method;
    private List<Para> lstPara;

    public FilterModel(int _offset, int _limit, Method _method, List<Para> _lstPara) {
        offset = _offset;
        limit = _limit;
        method = _method.name();
        lstPara = _lstPara;
    }

    @NonNull
    @Override
    public String toString() {

        return new GsonBuilder().create().toJson(this, FilterModel.class);

    }
}
