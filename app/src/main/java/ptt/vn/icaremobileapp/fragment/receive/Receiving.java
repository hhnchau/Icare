package ptt.vn.icaremobileapp.fragment.receive;

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

public class Receiving extends BaseFragment {
    private View view;
    private List<Fragmentoz> lstFragment = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.receiving, container, false);

        //setupTabButton();

        view.findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.RECEIVING_ONE, false, R.id.frameReceiving, null, Directionez.NEXT);
                }
            }
        });


        return view;
    }

    private void setupTabButton() {
        if (getActivity() != null) {
            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            MyTabButton myTabButton = view.findViewById(R.id.myTab);
            List<String> lst = new ArrayList<>();
            lst.add("Mot");
            lst.add("Hai");
            myTabButton.setContent(lst);
            myTabButton.setActive(MyTabButton.TAB1);
            final Bundle bundle = new Bundle();
            bundle.putParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT, null);
            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.RECEIVING_ONE, false, R.id.frameReceiving, bundle, Directionez.NEXT);
            myTabButton.setOnToggleSelectedListener(new MyTabButton.OnToggledListener() {
                @Override
                public void onTab(int tab) {
                    switch (tab) {
                        case MyTabButton.TAB1:
                            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.RECEIVING_ONE,false, R.id.frameReceiving, null, Directionez.NEXT);
                            break;
                        case MyTabButton.TAB2:
                            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.RECEIVING_TWO, false, R.id.frameReceiving, null, Directionez.NEXT);
                            break;
                    }
                }
            });
        }
    }
}
