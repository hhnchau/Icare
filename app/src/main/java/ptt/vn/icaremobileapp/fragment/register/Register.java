package ptt.vn.icaremobileapp.fragment.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.custom.MyTabButton;
import ptt.vn.icaremobileapp.fragmentutils.Directionez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentoz;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.model.register.RegisterDomain;
import ptt.vn.icaremobileapp.model.register.RegisterHi;
import ptt.vn.icaremobileapp.model.register.RegisterServiceOrder;
import ptt.vn.icaremobileapp.utils.Constant;
import ptt.vn.icaremobileapp.utils.Utils;


public class Register extends BaseFragment {
    private View view;
    private FragmentManager fragmentManager;
    private List<Fragmentoz> lstFragment = new ArrayList<>();

    private PatientDomain patientDomain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register, container, false);
        if (getArguments() != null)
            patientDomain = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_PATIENT);

        MyButton btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnSelectedListener(new MyButton.OnListener() {
            @Override
            public void onClick() {
                onSave();
            }
        });

        setupTabButton();
        toolbarListener();
        return view;
    }

    private void setupTabButton() {
        if (getActivity() != null) {
            MyTabButton myTabButton = view.findViewById(R.id.myTab);
            fragmentManager = getActivity().getSupportFragmentManager();
            List<String> lst = new ArrayList<>();
            lst.add(getString(R.string.tab_info));
            lst.add(getString(R.string.tab_receive));
            lst.add(getString(R.string.tab_service_item));
            myTabButton.setContent(lst);
            myTabButton.setActive(MyTabButton.TAB1);
            final Bundle bundle = new Bundle();
            bundle.putParcelable(Fragmentuz.BUNDLE_KEY_PATIENT, patientDomain);

            //Set Default
            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.REGISTER_INFO, false, R.id.frameRegister, bundle, Directionez.NEXT);
            myTabButton.setOnToggleSelectedListener(new MyTabButton.OnToggledListener() {
                @Override
                public void onTab(int tab) {
                    if (getActivity() != null) {
                        switch (tab) {
                            case MyTabButton.TAB1:
                                Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.REGISTER_INFO, false, R.id.frameRegister, bundle, Directionez.NEXT);
                                break;
                            case MyTabButton.TAB2:
                                Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.REGISTER_RECEIVE, false, R.id.frameRegister, bundle, Directionez.NEXT);
                                break;
                            case MyTabButton.TAB3:
                                Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.REGISTER_SERVICEITEM, false, R.id.frameRegister, null, Directionez.NEXT);
                                break;
                        }
                    }
                }
            });
        }
    }

    public void onSave() {
        if (getActivity() != null) {
//            boolean validateInfo = false;
//            boolean validateHi = false;
//            boolean validateBhi = false;
//            ReceivingInfo receivingInfo = (ReceivingInfo) getActivity().getSupportFragmentManager().findFragmentByTag(ReceivingInfo.class.getName());
//            if (receivingInfo != null) validateInfo = receivingInfo.validate();
//            ReceivingHi receivingHi = (ReceivingHi) getActivity().getSupportFragmentManager().findFragmentByTag(ReceivingHi.class.getName());
//            if (receivingHi != null) validateHi = receivingHi.validate();
//            ReceivingBhi receivingBhi = (ReceivingBhi) getActivity().getSupportFragmentManager().findFragmentByTag(ReceivingBhi.class.getName());
//            if (receivingBhi != null) validateBhi = receivingBhi.validate();
//
//            if (!validateInfo || !validateHi || !validateBhi) {
//                Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                return;
//            }

            RegisterDomain registerDomain = new RegisterDomain();
            registerDomain.setActive(Constant.ACTIVE);
            registerDomain.setSiterf(Constant.SITERF);
            registerDomain.setIdlink(Utils.newGuid());
            //registerDomain.setPatcode();
            //registerDomain.setPatid();
            //registerDomain.setPurpos();
            //registerDomain.setIdaddr();
            //registerDomain.setIdide();

            RegisterHi registerHi = new RegisterHi();
            //registerHi.setIdline();
            List<RegisterHi> lstRegisterHi = new ArrayList<>();
            lstRegisterHi.add(registerHi);

            registerDomain.setLstRegisterHi(lstRegisterHi);

            List<RegisterServiceOrder> lstServiceOrder = new ArrayList<>();
            registerDomain.setLstRegServiceOrder(lstServiceOrder);


            ApiController.getInstance().saveRegister(getActivity(), registerDomain, new Callback<RegisterDomain>() {
                @Override
                public void response(RegisterDomain registerDomain) {
                    Toast.makeText(getActivity(), "Đăng ký bệnh nhân thành công", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void toolbarListener() {

    }

    @Override
    public void onDestroyView() {
        Fragmentuz.removeFragment(lstFragment, fragmentManager);
        super.onDestroyView();
    }
}
