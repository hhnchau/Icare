package ptt.vn.icaremobileapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.alert.Alert;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.api.Host;
import ptt.vn.icaremobileapp.dialog.DialogNewHappening;
import ptt.vn.icaremobileapp.enums.Directionez;
import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.expand.ExpandableHappening;
import ptt.vn.icaremobileapp.adapter.HappeningAdapter;
import ptt.vn.icaremobileapp.model.filter.Method;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.utils.Fragmentuz;
import ptt.vn.icaremobileapp.utils.Utils;

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
            if (patient != null) setupExpandablePatientInfo(patient);
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
                        happening.setActive(Host.ACTIVE);
                        happening.setSiterf(Host.SITERF);
                        happening.setIdline(Utils.newGuid());
                        happening.setIdlinedepartinfolog(Utils.newGuid());
                        happening.setIdlink(inpatient.getIdlink());
                        saveHappening(happening);
                    }
                });
            }
        });
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

    private void setupExpandableRecyclerView() {
        RecyclerView rcv = view.findViewById(R.id.rcv);
        lstHappening = new ArrayList<>();
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HappeningAdapter(lstHappening);
        rcv.setAdapter(adapter);

        adapter.setOnItemClick(new HappeningAdapter.OnItemClick() {
            @Override
            public void onItem(HappeningDomain happening) {
                /*
                 * GOTO
                 **/
                Bundle bundle = new Bundle();
                bundle.putParcelable(Fragmentuz.BUNDLE_KEY_HAPPENING, happening);
                bundle.putParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT, inpatient);
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
                        happening.setIdline(Utils.newGuid());
                        happening.setIdlinedepartinfolog(Utils.newGuid());
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
                        saveHappening(happening);
                    }
                });
            }

            @Override
            public void onDelete(final HappeningDomain happening) {
                Alert.getInstance().show(getActivity(), getString(R.string.txt_delete_happening), getString(R.string.btn_yes), getString(R.string.btn_no), false, new Alert.OnAlertClickListener() {
                    @Override
                    public void onYes() {
                        /*
                         * DELETE
                         **/
                        happening.setActive(Host.DELETE);
                        deleteHappening(happening);
                    }

                    @Override
                    public void onNo() {

                    }
                });
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
        ApiController.getInstance().saveHappening(getActivity(), happening, new Callback<HappeningDomain>() {
            @Override
            public void response(HappeningDomain happening) {
                boolean isNew = true;
                for (int i = 0; i < lstHappening.size(); i++)
                    if (lstHappening.get(i).getIdline().equals(happening.getIdline())) {
                        lstHappening.set(i, happening);
                        adapter.notifyDataSetChanged();
                        isNew = false;
                        break;
                    }

                if (isNew) {
                    lstHappening.add(happening);
                    adapter.setItems(lstHappening);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void deleteHappening(HappeningDomain happening) {
        ApiController.getInstance().deleteHappening(getActivity(), happening, new Callback<HappeningDomain>() {
            @Override
            public void response(HappeningDomain happening) {
                for (int i = 0; i < lstHappening.size(); i++)
                    if (lstHappening.get(i).getIdline().equals(happening.getIdline())) {
                        adapter.removeItem(i);
                        break;
                    }
            }
        });
    }
}
