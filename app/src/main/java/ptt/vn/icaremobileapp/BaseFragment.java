package ptt.vn.icaremobileapp;

import android.support.v4.app.Fragment;

import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.fragment.inpatient.Diagnose;
import ptt.vn.icaremobileapp.fragment.inpatient.DrugOrder;
import ptt.vn.icaremobileapp.fragment.inpatient.DrugOrderOutside;
import ptt.vn.icaremobileapp.fragment.inpatient.ServiceItem;
import ptt.vn.icaremobileapp.fragment.inpatient.ThamKham;

public class BaseFragment extends Fragment {

    public static BaseFragment newInstance(Fragmentez fragmentez) {
        BaseFragment baseFragment = null;
        switch (fragmentez) {
            case THAM_KHAM:
                baseFragment = new ThamKham();
                break;
            case SERVICE_ITEM:
                baseFragment = new ServiceItem();
                break;
            case DRUG_ORDER:
                baseFragment = new DrugOrder();
                break;
            case DRUG_ORDER_OUTSIDE:
                baseFragment = new DrugOrderOutside();
                break;
            case DIAGNOSE:
                baseFragment = new Diagnose();
                break;
        }
        return baseFragment;
    }
}
