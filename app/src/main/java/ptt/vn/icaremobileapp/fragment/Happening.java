package ptt.vn.icaremobileapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.dialog.DialogNewHappening;
import ptt.vn.icaremobileapp.enums.Directionez;
import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.expandcardview.ExpandableCardView;
import ptt.vn.icaremobileapp.adapter.HappeningAdapter;
import ptt.vn.icaremobileapp.model.filter.Method;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.utils.Fragmentuz;

public class Happening extends BaseFragment {
    private View view;
    private InpatientDomain inpatient;
    private HappeningAdapter adapter;
    private List<HappeningDomain> lstHappening;
    private int offset = 0;
    private int limit = 1000;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.happening, container, false);
        if (getArguments() != null)
            inpatient = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT);
        if (inpatient != null) {
            initView();
            PatientDomain patient = inpatient.getPatient();
            if (patient != null) setupExpandableCardView(patient);
            setupExpandableRecyclerView();

            getHappening(offset, limit, Method.GetHappeningInDepartment, inpatient.getIdlink());
        }
        return view;
    }

    private void initView() {
        view.findViewById(R.id.fabAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNewHappening.getInstance().show(getActivity(), null, new DialogNewHappening.OnClickListener() {
                    @Override
                    public void onClickListener(HappeningDomain happening) {
                        /*
                         * NEW
                         **/
                        Toast.makeText(getActivity(), "NEW", Toast.LENGTH_SHORT).show();
                        happening.setIdlink(inpatient.getIdlink());
                        saveHappening(happening);
                    }
                });
            }
        });
    }

    private void setupExpandableCardView(PatientDomain patient) {
        final ExpandableCardView expPatientInfo = view.findViewById(R.id.expPatientInfo);
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

    private void setupExpandableRecyclerView() {
        RecyclerView rcv = view.findViewById(R.id.rcv);
        lstHappening = new ArrayList<>();
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HappeningAdapter(lstHappening);
        rcv.setAdapter(adapter);

        adapter.setOnItemClick(new HappeningAdapter.OnItemClick() {
            @Override
            public void onItem(HappeningDomain happening) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Fragmentuz.BUNDLE_KEY_HAPPENING, happening);
                if (getActivity() != null)
                    Fragmentuz.addMainFrame(getActivity().getSupportFragmentManager(), bundle, Fragmentez.INSTRUCTION, R.id.mainFrame, Directionez.NEXT);
            }

            @Override
            public void onCopy(HappeningDomain happening) {
                DialogNewHappening.getInstance().show(getActivity(), happening, new DialogNewHappening.OnClickListener() {
                    @Override
                    public void onClickListener(HappeningDomain happening) {
                        /*
                         * COPY
                         **/
                        Toast.makeText(getActivity(), "COPY", Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        String json = gson.toJson(happening);
                        saveHappening(happening);
                    }
                });
            }

            @Override
            public void onEdit(HappeningDomain happening) {
                DialogNewHappening.getInstance().show(getActivity(), happening, new DialogNewHappening.OnClickListener() {
                    @Override
                    public void onClickListener(HappeningDomain happening) {
                        /*
                         * EDIT
                         **/
                        Toast.makeText(getActivity(), "EDIT", Toast.LENGTH_SHORT).show();
                        saveHappening(happening);
                    }
                });
            }

            @Override
            public void onDelete(HappeningDomain happening) {
                /*
                 * DELETE
                 **/
                Toast.makeText(getActivity(), "DELETE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getHappening(int _offset, int _limit, Method _method, @NonNull String _idLink) {
        ApiController.getInstance().getHappening(getActivity(), _offset, _limit, _method, _idLink, new ACallback<HappeningDomain>() {
            @Override
            public void response(List<HappeningDomain> listHappening) {
                lstHappening = listHappening;

                adapter.setItems(lstHappening);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void saveHappening(HappeningDomain happening) {
        ApiController.getInstance().saveHappening(getActivity(), happening, null);
    }
}
