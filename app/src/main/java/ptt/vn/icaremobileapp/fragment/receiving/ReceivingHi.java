package ptt.vn.icaremobileapp.fragment.receiving;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyInputText;
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.custom.MyInputTextOutlineMultiLine;
import ptt.vn.icaremobileapp.model.hi.HiDomain;
import ptt.vn.icaremobileapp.model.shared.HiLiveData;


public class ReceivingHi extends BaseFragment {
    private View view;

    private MyInputTextOutline edtHiCode, edtHiStart, edtHiEnd, edtHiObjectCode, edtHi5y;
    private MyInputTextOutlineMultiLine edtHospitalName, edtHiAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.receiving_hi, container, false);
        initView();


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            HiLiveData hiLiveData = ViewModelProviders.of(getActivity()).get(HiLiveData.class);
            hiLiveData.getHiLiveData().observe(getActivity(), new Observer<HiDomain>() {
                @Override
                public void onChanged(@Nullable HiDomain hiDomain) {
                    updateView(hiDomain);
                }
            });
        }
    }

    private void updateView(HiDomain hiDomain) {
        if (hiDomain != null) {
            if (!TextUtils.isEmpty(hiDomain.getMaThe()))
                edtHiCode.setText(hiDomain.getMaThe());
            if (!TextUtils.isEmpty(hiDomain.getGtTheTu()))
                edtHiStart.setText(hiDomain.getGtTheTu());
            if (!TextUtils.isEmpty(hiDomain.getGtTheDen()))
                edtHiEnd.setText(hiDomain.getGtTheDen());
            if (!TextUtils.isEmpty(hiDomain.getMaKV()))
                edtHiObjectCode.setText(hiDomain.getMaKV());
            if (!TextUtils.isEmpty(hiDomain.getNgayDu5Nam()))
                edtHi5y.setText(hiDomain.getNgayDu5Nam());
            if (!TextUtils.isEmpty(hiDomain.getMaDKBDMoi()))
                edtHospitalName.setText(hiDomain.getMaDKBDMoi());
            if (!TextUtils.isEmpty(hiDomain.getDiaChi()))
                edtHiAddress.setText(hiDomain.getDiaChi());
        }
    }

    private void initView() {
        edtHiCode = view.findViewById(R.id.edtHiCode);
        edtHiStart = view.findViewById(R.id.edtHiStart);
        edtHiEnd = view.findViewById(R.id.edtHiEnd);
        edtHiObjectCode = view.findViewById(R.id.edtHiObjectCode);
        edtHi5y = view.findViewById(R.id.edtHi5y);
        edtHospitalName = view.findViewById(R.id.edtHospitalName);
        edtHiAddress = view.findViewById(R.id.edtHiAddress);
    }

    public boolean validate(){
        return true;
    }

    @Override
    public void toolbarListener() {

    }
}
