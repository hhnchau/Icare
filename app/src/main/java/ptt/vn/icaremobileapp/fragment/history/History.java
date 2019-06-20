package ptt.vn.icaremobileapp.fragment.history;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.activity.MainActivity;
import ptt.vn.icaremobileapp.adapter.ViewPagerAdapter;
import ptt.vn.icaremobileapp.custom.MyTabButton;
import ptt.vn.icaremobileapp.expand.ExpandableHappening;
import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.fragmentutils.Directionez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentoz;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;

public class History extends BaseFragment {
    private View view;
    private FragmentManager fragmentManager;
    private List<Fragmentoz> lstFragment = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history, container, false);

        //setupViewPager();
        setupTabButton();
        //setupExpandablePatientInfo(null);

        return view;
    }

    private void setupViewPager() {
        if (getActivity() != null) {
            final ViewPager viewPager = view.findViewById(R.id.viewPager);
            ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
            final Bundle bundle = new Bundle();
            //bundle.putString("A", "Hello");
            HistoryRegister historyRegister = new HistoryRegister();
            historyRegister.setArguments(bundle);
            adapter.addFragment(historyRegister);
            HistoryClinic historyClinic = new HistoryClinic();
            historyClinic.setArguments(bundle);
            adapter.addFragment(historyClinic);
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(1);

            final MyTabButton myTabButton = view.findViewById(R.id.myTab);
            List<String> lst = new ArrayList<>();
            lst.add(getString(R.string.tab_history_register
            ));
            lst.add(getString(R.string.tab_history_clinic));
            myTabButton.setContent(lst);
            myTabButton.setActive(MyTabButton.TAB1);

            myTabButton.setOnToggleSelectedListener(new MyTabButton.OnToggledListener() {
                @Override
                public void onTab(int tab) {
                    if (getActivity() != null)
                        viewPager.setCurrentItem(tab - 1);
                }
            });

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    myTabButton.setActive(position + 1);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    private void setupTabButton() {
        if (getActivity() != null) {
            MyTabButton myTabButton = view.findViewById(R.id.myTab);
            fragmentManager = getActivity().getSupportFragmentManager();
            List<String> lst = new ArrayList<>();
            lst.add(getString(R.string.tab_history_register));
            lst.add(getString(R.string.tab_history_clinic));
            myTabButton.setContent(lst);
            myTabButton.setActive(MyTabButton.TAB1);
            final Bundle bundle = new Bundle();
            if (getArguments() != null)
                bundle.putParcelable(Fragmentuz.BUNDLE_KEY_PATIENT, getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_PATIENT));

            //Set Default
            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.HISTORY_REGISTER, false, R.id.frameHistory, bundle, Directionez.NEXT);
            myTabButton.setOnToggleSelectedListener(new MyTabButton.OnToggledListener() {
                @Override
                public void onTab(int tab) {
                    if (getActivity() != null) {
                        switch (tab) {
                            case MyTabButton.TAB1:
                                ((MainActivity) getActivity()).toolbarIcon(0);
                                Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.HISTORY_REGISTER, false, R.id.frameHistory, bundle, Directionez.NEXT);
                                break;
                            case MyTabButton.TAB2:
                                ((MainActivity) getActivity()).toolbarIcon(R.drawable.ic_more);
                                Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.HISTORY_CLINIC, false, R.id.frameHistory, bundle, Directionez.NEXT);
                                break;
                        }
                    }
                }
            });
        }
    }

    private void setupExpandablePatientInfo(PatientDomain patient) {
        final ExpandableHappening expPatientInfo = view.findViewById(R.id.expPatientInfo);
        expPatientInfo.setChildrenView(patient);

        expPatientInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expPatientInfo.isExpanded())
                    expPatientInfo.collapse();
                else expPatientInfo.expand();
            }
        });
    }

    @Override
    public void toolbarListener() {

    }

    @Override
    public void onDestroyView() {
        new Handler().post(new Runnable() {
            public void run() {
                if (getActivity() != null)
                    Fragmentuz.removeAllFragment(getActivity().getSupportFragmentManager());
            }
        });

        super.onDestroyView();
    }
}
