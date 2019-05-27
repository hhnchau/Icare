package ptt.vn.icaremobileapp.fragment.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyTabButton;
import ptt.vn.icaremobileapp.fragmentutils.Directionez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentoz;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;


public class Register extends BaseFragment {
    private View view;
    private FragmentManager fragmentManager;
    private List<Fragmentoz> lstFragment =  new ArrayList<>();;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register, container, false);
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
            //final Bundle bundle = new Bundle();
            //bundle.putParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT, inpatient);

            //Set Default
            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.REGISTER_INFO, false, R.id.frameRegister, null, Directionez.NEXT);
            myTabButton.setOnToggleSelectedListener(new MyTabButton.OnToggledListener() {
                @Override
                public void onTab(int tab) {
                    if (getActivity() != null) {
                        switch (tab) {
                            case MyTabButton.TAB1:
                                Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.REGISTER_INFO, false, R.id.frameRegister, null, Directionez.NEXT);
                                break;
                            case MyTabButton.TAB2:
                                Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.REGISTER_RECEIVE, false, R.id.frameRegister, null, Directionez.NEXT);
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

    @Override
    public void toolbarListener() {

    }

    @Override
    public void onDestroyView() {
        Fragmentuz.removeFragment(lstFragment, fragmentManager);
        super.onDestroyView();
    }
}
