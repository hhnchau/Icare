package ptt.vn.icaremobileapp.fragment.register;

import android.os.Bundle;
import android.os.Handler;
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
import ptt.vn.icaremobileapp.model.register.RegisterDomain;
import ptt.vn.icaremobileapp.utils.Constant;
import ptt.vn.icaremobileapp.utils.Utils;


public class Register extends BaseFragment {
    private View view;
    private FragmentManager fragmentManager;
    private List<Fragmentoz> lstFragment = new ArrayList<>();

    public static RegisterDomain registerDomain = new RegisterDomain();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register, container, false);

        MyButton btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnSelectedListener(new MyButton.OnListener() {
            @Override
            public void onClick() {
                onSave();
            }
        });

        setupTabButton();
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
            if (getArguments() != null)
                bundle.putParcelable(Fragmentuz.BUNDLE_KEY_PATIENT, getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_PATIENT));

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
            boolean validateInfo = false;
            boolean validateReceive = false;
            boolean validateHi = false;
            boolean validateBhi;
            boolean validateService = false;
            RegisterInfo registerInfo = (RegisterInfo) getActivity().getSupportFragmentManager().findFragmentByTag(RegisterInfo.class.getName());
            if (registerInfo != null) validateInfo = registerInfo.validate();
            RegisterReceive registerReceive = (RegisterReceive) getActivity().getSupportFragmentManager().findFragmentByTag(RegisterReceive.class.getName());
            if (registerReceive != null) validateReceive = registerReceive.validate();
            RegisterReceiveHi registerReceiveHi = (RegisterReceiveHi) getActivity().getSupportFragmentManager().findFragmentByTag(RegisterReceiveHi.class.getName());
            if (registerReceiveHi != null && registerReceiveHi.isVisible())
                validateHi = registerReceiveHi.validate();
            RegisterReceiveBhi registerReceiveBhi = (RegisterReceiveBhi) getActivity().getSupportFragmentManager().findFragmentByTag(RegisterReceiveBhi.class.getName());
            if (registerReceiveBhi != null && registerReceiveBhi.isVisible())
                validateBhi = registerReceiveBhi.validate();
            else validateBhi = true;
            RegisterServiceItem registerServiceItem = (RegisterServiceItem) getActivity().getSupportFragmentManager().findFragmentByTag(RegisterServiceItem.class.getName());
            if (registerServiceItem != null)
                validateService = registerServiceItem.validate();

            if (!validateInfo || !validateReceive || !validateHi || !validateBhi || !validateService) {
                Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            registerDomain.setActive(Constant.ACTIVE);
            registerDomain.setSiterf(Constant.SITERF);
            registerDomain.setIdlink(Utils.newGuid());
            registerDomain.setRegdate(Utils.getCurrentDate(Utils.ddMMyyyyTHHmmss));
            //registerDomain.setTypeexamination(25458);
            //registerDomain.setIdpriob(25508);

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
        registerDomain = null;
        new Handler().post(new Runnable() {
            public void run() {
                if (getActivity() != null)
                    Fragmentuz.removeFragment(lstFragment, fragmentManager);
            }
        });

        super.onDestroyView();
    }
}
