package ptt.vn.icaremobileapp.fragment.receiving;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.activity.MainActivity;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.model.hi.HiCard;
import ptt.vn.icaremobileapp.model.hi.HiDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.model.shared.HiLiveData;
import ptt.vn.icaremobileapp.utils.Constant;
import ptt.vn.icaremobileapp.utils.Helper;
import ptt.vn.icaremobileapp.utils.Utils;


public class Receiving extends BaseFragment {
    private View view;

    public static PatientDomain patientDomain = new PatientDomain();

    private ReceivingInfo frgInfo;
    private ReceivingHi frgHi;
    private ReceivingBhi frgBhi;

    private HiLiveData hiLiveData;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.receiving, container, false);
        initFragment();
        toolbarListener();

        MyButton btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnSelectedListener(new MyButton.OnListener() {
            @Override
            public void onClick() {
                onSave();
            }
        });

        return view;
    }

    private void initFragment() {
        if (getActivity() != null) {
            hiLiveData = ViewModelProviders.of(getActivity()).get(HiLiveData.class);
            frgInfo = new ReceivingInfo();
            frgHi = new ReceivingHi();
            frgBhi = new ReceivingBhi();

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.frameInfo, frgInfo, ReceivingInfo.class.getName())
                            .add(R.id.frameHi, frgHi, ReceivingHi.class.getName())
                            .add(R.id.frameBhi, frgBhi, ReceivingBhi.class.getName())
                            .commit();
                }
            });
        }
    }

    private void getHiInfo(String maThe, String hoTen, String ngaySinh) {
        ApiController.getInstance().getHiInfo(getActivity(), maThe, hoTen, ngaySinh, new Callback<HiDomain>() {
            @Override
            public void response(HiDomain hiDomain) {
                if (hiDomain != null && hiDomain.getMaKetQua().equals("000"))
                    hiLiveData.setHiLiveData(hiDomain);
                else Toast.makeText(getActivity(), "Thẻ Không Hợp Lệ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onSave() {
        if (getActivity() != null) {
            boolean validateInfo = false;
            boolean validateHi = false;
            boolean validateBhi = false;
            ReceivingInfo receivingInfo = (ReceivingInfo) getActivity().getSupportFragmentManager().findFragmentByTag(ReceivingInfo.class.getName());
            if (receivingInfo != null) validateInfo = receivingInfo.validate();
            ReceivingHi receivingHi = (ReceivingHi) getActivity().getSupportFragmentManager().findFragmentByTag(ReceivingHi.class.getName());
            if (receivingHi != null) validateHi = receivingHi.validate();
            ReceivingBhi receivingBhi = (ReceivingBhi) getActivity().getSupportFragmentManager().findFragmentByTag(ReceivingBhi.class.getName());
            if (receivingBhi != null) validateBhi = receivingBhi.validate();

            if (!validateInfo || !validateHi || !validateBhi) {
                Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            patientDomain.setActive(Constant.ACTIVE);
            patientDomain.setSiterf(Constant.SITERF);
            patientDomain.setPatid(Utils.newGuid());

            ApiController.getInstance().saveReceiving(getActivity(), patientDomain, new Callback<PatientDomain>() {
                @Override
                public void response(PatientDomain patientDomain) {
                    Toast.makeText(getActivity(), "Đăng ký bệnh nhân thành công", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void startScanner() {
        IntentIntegrator.forSupportFragment(this).setPrompt("").setOrientationLocked(false).setBeepEnabled(true).initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && getActivity() != null) {
            if (result.getContents() == null) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                HiCard hiCard = Helper.parseQr(result.getContents());
                if (hiCard != null) {
                    getHiInfo(hiCard.getSn(), hiCard.getName(), hiCard.getBirthday());
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void toolbarListener() {
        if (getActivity() != null)
            ((MainActivity) getActivity()).toolbarClick(new MainActivity.OnToolbarListener() {
                @Override
                public void left(String frgName) {

                }

                @Override
                public void right(View v) {
                    startScanner();
                }
            });
    }

    @Override
    public void onDestroy() {
        patientDomain = null;
        new Handler().post(new Runnable() {
            public void run() {
                if (getActivity() != null)
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .remove(frgInfo)
                            .remove(frgHi)
                            .remove(frgBhi)
                            .commit();
            }
        });

        super.onDestroy();
    }

}
