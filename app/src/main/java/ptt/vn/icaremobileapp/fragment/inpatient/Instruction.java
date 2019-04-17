package ptt.vn.icaremobileapp.fragment.inpatient;

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

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.enums.Directionez;
import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.expand.ExpandableInstruction;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.togglebutton.MyTabButton;
import ptt.vn.icaremobileapp.utils.Fragmentoz;
import ptt.vn.icaremobileapp.utils.Fragmentuz;

public class Instruction extends BaseFragment implements MyButton.OnListener {
    private View view;
    public static HappeningDomain happeningDomain;
    private List<Fragmentoz> lstFragment = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.instruction, container, false);

        MyButton btn = view.findViewById(R.id.btnSave);
        btn.setOnSelectedListener(this);

        if (getArguments() != null) {
            happeningDomain = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_HAPPENING);
            InpatientDomain inpatient = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT);
            if (inpatient != null)
                if (inpatient.getPatient() != null)
                    setupExpandableInstructionInfo(inpatient.getPatient(), happeningDomain);
        }

        if (getActivity() != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            setupTabButton(fragmentManager);

            //Default
            Fragmentuz.addFrame(lstFragment, fragmentManager, Fragmentez.SERVICE_ITEM, R.id.frame, Directionez.NEXT);
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
        myTabButton.setActive(MyTabButton.TAB2);
        myTabButton.setOnToggleSelectedListener(new MyTabButton.OnToggledListener() {
            @Override
            public void onToggled(Fragmentez fzg) {
                if (getActivity() != null) {
                    switch (fzg) {
                        case THAM_KHAM:
                            Fragmentuz.addFrame(lstFragment, fragmentManager, Fragmentez.THAM_KHAM, R.id.frame, Directionez.NEXT);
                            break;
                        case SERVICE_ITEM:
                            Fragmentuz.addFrame(lstFragment, fragmentManager, Fragmentez.SERVICE_ITEM, R.id.frame, Directionez.NEXT);
                            break;
                        case DRUG_ORDER:
                            Fragmentuz.addFrame(lstFragment, fragmentManager, Fragmentez.DRUG_ORDER, R.id.frame, Directionez.NEXT);
                            break;
                        case DRUG_ORDER_OUTSIDE:
                            Fragmentuz.addFrame(lstFragment, fragmentManager, Fragmentez.DRUG_ORDER_OUTSIDE, R.id.frame, Directionez.NEXT);
                            break;
                        case DIAGNOSE:
                            Fragmentuz.addFrame(lstFragment, fragmentManager, Fragmentez.DIAGNOSE, R.id.frame, Directionez.NEXT);
                            break;
                    }
                }
            }
        });
    }

    private void saveHappening(HappeningDomain happening) {
        ApiController.getInstance().saveHappening(getActivity(), happening, new Callback<HappeningDomain>() {
            @Override
            public void response(HappeningDomain happening) {
                Toast.makeText(getActivity(), getString(R.string.txt_success), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick() {
        saveHappening(happeningDomain);
    }
}
