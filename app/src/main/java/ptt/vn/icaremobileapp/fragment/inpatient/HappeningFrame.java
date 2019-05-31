package ptt.vn.icaremobileapp.fragment.inpatient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyTabButton;
import ptt.vn.icaremobileapp.expand.ExpandableHappening;
import ptt.vn.icaremobileapp.fragmentutils.Directionez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentoz;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;

public class HappeningFrame extends BaseFragment {
    private View view;
    private FragmentManager fragmentManager;
    private List<Fragmentoz> lstFragment = new ArrayList<>();
    private InpatientDomain inpatient;
    private MyTabButton myTabButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.happening_frame, container, false);
        initView();

        if (getArguments() != null)
            inpatient = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT);

        updateView();

        return view;
    }

    private void initView() {
        myTabButton = view.findViewById(R.id.myTab);
    }

    private void updateView() {
        if (inpatient != null) {
            PatientDomain patient = inpatient.getPatient();
            if (patient != null) setupExpandablePatientInfo(patient);

            setupTabButton();
        }
    }

    private void setupTabButton() {
        if (getActivity() != null) {
            fragmentManager = getActivity().getSupportFragmentManager();
            List<String> lst = new ArrayList<>();
            lst.add(getString(R.string.tab_happening));
            lst.add(getString(R.string.tab_resolve));
            myTabButton.setContent(lst);
            myTabButton.setActive(MyTabButton.TAB1);
            final Bundle bundle = new Bundle();
            bundle.putParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT, inpatient);

            //Set Default
            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.HAPPENING, false, R.id.frameHappening, bundle, Directionez.NEXT);
            myTabButton.setOnToggleSelectedListener(new MyTabButton.OnToggledListener() {
                @Override
                public void onTab(int tab) {
                    if (getActivity() != null) {
                        switch (tab) {
                            case MyTabButton.TAB1:
                                Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.HAPPENING, false, R.id.frameHappening, null, Directionez.NEXT);
                                break;
                            case MyTabButton.TAB2:
                                Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.RESOLVED, false, R.id.frameHappening, null, Directionez.NEXT);
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
        Fragmentuz.removeFragment(lstFragment, fragmentManager);
        super.onDestroyView();
    }
}
