package ptt.vn.icaremobileapp.model.shared;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import ptt.vn.icaremobileapp.model.hi.HiDomain;

public class HiLiveData extends AndroidViewModel {
    public HiLiveData(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<HiDomain> hiLiveData = new MutableLiveData<>();

    public MutableLiveData<HiDomain> getHiLiveData() {
        return hiLiveData;
    }

    public void setHiLiveData(HiDomain hiLiveData) {
        this.hiLiveData.setValue(hiLiveData);
    }
}
