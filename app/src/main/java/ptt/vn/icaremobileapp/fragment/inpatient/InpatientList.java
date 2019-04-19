package ptt.vn.icaremobileapp.fragment.inpatient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.InpatientListAdapter;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.CompositeManager;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewAdapter;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewMedexaAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.fragmentutils.Directionez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.medexa.MedexaHDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;

public class InpatientList extends BaseFragment {
    private View view;

    private InpatientListAdapter adapter;
    private List<InpatientDomain> lstInpatient;
    private int offset = 0;
    private int limit = 1000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.inpatient_list, container, false);
        initView();

        getMedexa();

        return view;
    }

    private void initView() {
        RecyclerView rcv = view.findViewById(R.id.rcvInpatientList);
        rcv.setHasFixedSize(true);
        //rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        lstInpatient = new ArrayList<>();
        adapter = new InpatientListAdapter(lstInpatient);
        rcv.setAdapter(adapter);

        adapter.setOnItemClick(new InpatientListAdapter.OnItemClick() {
            @Override
            public void onClick(int p) {
                if (getActivity() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT, lstInpatient.get(p));
                    Fragmentuz.replaceFrame(getActivity().getSupportFragmentManager(), bundle, Fragmentez.HAPPENING, R.id.mainFrame, Directionez.NEXT);
                }
            }
        });

    }

    private void getMedexa() {
        ApiController.getInstance().getMedexa(getActivity(),
                new ACallback<MedexaHDomain>() {
                    @Override
                    public void response(List<MedexaHDomain> list) {
                        setupListMedexa(list);
                    }
                });
    }

    private void setupListMedexa(List<MedexaHDomain> lstMedexa) {
        if (getActivity() != null) {
            final MyAutoCompleteTextView acpSearch = view.findViewById(R.id.acpSearch);
            AutoCompleteTextViewMedexaAdapter adapterMedexa = new AutoCompleteTextViewMedexaAdapter(getActivity(), lstMedexa);
            acpSearch.setAdapter(adapterMedexa);
            acpSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MedexaHDomain medexa = (MedexaHDomain) parent.getItemAtPosition(position);
                    if (medexa != null) getInpatient(offset, limit, medexa.getId());
                }
            });

            acpSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpSearch.showDropDown();
                }
            });
        }
    }

    @SuppressWarnings("unchecked")
    private void getInpatient(int _offset, int _limit, int _idmedexa) {
        ApiController.getInstance().getInpatient(getActivity(), _offset, _limit, _idmedexa, new ACallback<InpatientDomain>() {
            @Override
            public void response(List<InpatientDomain> listInpatient) {
                lstInpatient = listInpatient;

                adapter.setItems(lstInpatient);
                adapter.notifyDataSetChanged();

                //Get Patient
                getPatientByPatId(lstInpatient);
            }
        });
    }

    private void getPatientByPatId(List<InpatientDomain> list) {
        ApiController.getInstance().getPatientByPatId(getActivity(), list, new ACallback<PatientDomain>() {
            @Override
            public void response(List<PatientDomain> lstPatient) {
                int position;
                for (InpatientDomain inpatient : lstInpatient)
                    for (PatientDomain patient : lstPatient)
                        if (inpatient.getPatid().equals(patient.getPatid())) {
                            inpatient.setPatient(patient);
                            position = lstInpatient.indexOf(inpatient);

                            if (position == -1) return;

                            lstInpatient.set(position, inpatient);
                            adapter.notifyItemChanged(position);
                        }
            }
        });
    }

    @Override
    public void onDestroy() {
        CompositeManager.dispose();
        super.onDestroy();
    }
}
