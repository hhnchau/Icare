package ptt.vn.icaremobileapp.fragment.inpatient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.enums.Directionez;
import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.expand.ExpandableInstruction;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.togglebutton.MyTabButton;
import ptt.vn.icaremobileapp.utils.Fragmentuz;

public class Instruction extends BaseFragment {
    private View view;
    private HappeningDomain happening;
    private PatientDomain patient;
    private FragmentManager fragmentManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.instruction, container, false);
        if (getArguments() != null) {
            happening = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_HAPPENING);
            InpatientDomain inpatient = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT);
            if (inpatient != null)
                patient = inpatient.getPatient();
            if (patient != null) setupExpandableInstructionInfo(patient, happening);
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
        MyTabButton myTabButton = view.findViewById(R.id.toggle);
        myTabButton.setOnToggleSelectedListener(new MyTabButton.OnToggledListener() {
            @Override
            public void onToggled(Fragmentez fzg) {
                if (getActivity() != null) {
                    switch (fzg) {
                        case THAM_KHAM:
                            Fragmentuz.addFrame(fragmentManager, Fragmentez.THAM_KHAM, R.id.frame, Directionez.NEXT);
                            break;
                        case SERVICE_ITEM:
                            Fragmentuz.addFrame(fragmentManager, Fragmentez.SERVICE_ITEM, R.id.frame, Directionez.NEXT);
                            break;
                        case DRUG_ORDER:
                            Fragmentuz.addFrame(fragmentManager, Fragmentez.DRUG_ORDER, R.id.frame, Directionez.NEXT);
                            break;
                        case DRUG_ORDER_OUTSIDE:
                            Fragmentuz.addFrame(fragmentManager, Fragmentez.DRUG_ORDER_OUTSIDE, R.id.frame, Directionez.NEXT);
                            break;
                        case DIAGNOSE:
                            Fragmentuz.addFrame(fragmentManager, Fragmentez.DIAGNOSE, R.id.frame, Directionez.NEXT);
                            break;
                    }
                }
            }
        });
    }
}
