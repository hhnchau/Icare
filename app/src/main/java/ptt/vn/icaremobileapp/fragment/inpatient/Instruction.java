package ptt.vn.icaremobileapp.fragment.inpatient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.fragmentutils.Directionez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.expand.ExpandableInstruction;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.custom.MyTabButton;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentoz;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;

public class Instruction extends BaseFragment implements MyButton.OnListener {
    private View view;
    public static HappeningDomain happeningDomain;
    private List<Fragmentoz> lstFragment = new ArrayList<>();
    private FragmentManager fragmentManager;
    private InpatientDomain inpatient;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.instruction, container, false);

        MyButton btn = view.findViewById(R.id.btnSave);
        btn.setOnSelectedListener(this);

        if (getArguments() != null) {
            happeningDomain = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_HAPPENING);
            inpatient = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT);
            if (inpatient != null)
                if (inpatient.getPatient() != null)
                    setupExpandableInstructionInfo(inpatient.getPatient(), happeningDomain);
        }

        if (getActivity() != null) {
            fragmentManager = getActivity().getSupportFragmentManager();
            setupTabButton(fragmentManager);
        }
        return view;
    }

    private void setupExpandableInstructionInfo(PatientDomain patient, HappeningDomain happening) {
        final ExpandableInstruction expandableInstruction = view.findViewById(R.id.epxInstructionInfo);
        expandableInstruction.setChildrenView(patient, happening);
        expandableInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandableInstruction.isExpanded())
                    expandableInstruction.collapse();
                else expandableInstruction.expand();
            }
        });
    }

    private void setupTabButton(final FragmentManager fragmentManager) {
        MyTabButton myTabButton = view.findViewById(R.id.myTab);
        List<String> lst = new ArrayList<>();
        //lst.add(getString(R.string.tab_tham_kham));
        lst.add(null);
        lst.add(getString(R.string.tab_service_item));
        lst.add(getString(R.string.tab_drug_order));
        //lst.add(getString(R.string.tab_drug_order_outside));
        lst.add(null);
        lst.add(getString(R.string.tab_diagnose));
        myTabButton.setContent(lst);
        //Default
        myTabButton.setActive(MyTabButton.TAB2);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT, inpatient);
        Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.SERVICE_ITEM, false, R.id.frame, bundle, Directionez.NEXT);
        myTabButton.setOnToggleSelectedListener(new MyTabButton.OnToggledListener() {
            @Override
            public void onTab(int tab) {
                if (getActivity() != null) {
                    switch (tab) {
                        case MyTabButton.TAB1:
                            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.THAM_KHAM, false, R.id.frame, null, Directionez.NEXT);
                            break;
                        case MyTabButton.TAB2:
                            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.SERVICE_ITEM, false, R.id.frame, bundle, Directionez.NEXT);
                            break;
                        case MyTabButton.TAB3:
                            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.DRUG_ORDER, false, R.id.frame, bundle, Directionez.NEXT);
                            break;
                        case MyTabButton.TAB4:
                            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.DRUG_ORDER_OUTSIDE, false, R.id.frame, null, Directionez.NEXT);
                            break;
                        case MyTabButton.TAB5:
                            Fragmentuz.addFragment(lstFragment, fragmentManager, Fragmentez.DIAGNOSE, false, R.id.frame, null, Directionez.NEXT);
                            break;
                    }
                }
            }
        });
    }

    private void saveHappening(HappeningDomain happening) {
        String json = new Gson().toJson(happening);
        ApiController.getInstance().saveHappening(getActivity(), happening, new Callback<HappeningDomain>() {
            @Override
            public void response(HappeningDomain happening) {
                Toast.makeText(getActivity(), getString(R.string.txt_save_success), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick() {
        saveHappening(happeningDomain);
    }

    @Override
    public void toolbarListener() {

    }

    @Override
    public void onDestroy() {
        Fragmentuz.removeFragment(lstFragment, fragmentManager);
        super.onDestroy();
    }
}
