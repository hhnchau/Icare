package ptt.vn.icaremobileapp.model.shared;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import ptt.vn.icaremobileapp.model.hi.HiDomain;
import ptt.vn.icaremobileapp.model.serviceitem.MapPriceServiceItemLDomain;

public class PriceLiveData extends AndroidViewModel {
    public PriceLiveData(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<List<MapPriceServiceItemLDomain>> priceLiveData = new MutableLiveData<>();

    public MutableLiveData<List<MapPriceServiceItemLDomain>> getPriceLiveData() {
        return priceLiveData;
    }

    public void setPriceLiveData(List<MapPriceServiceItemLDomain> lstPriceLiveData) {
        this.priceLiveData.setValue(lstPriceLiveData);
    }
}
