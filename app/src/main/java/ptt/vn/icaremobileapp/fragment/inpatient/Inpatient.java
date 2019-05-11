package ptt.vn.icaremobileapp.fragment.inpatient;

import android.os.AsyncTask;
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
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewMedexaAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.fragmentutils.Directionez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentoz;
import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.medexa.MedexaHDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.model.register.RegisterDomain;

import static ptt.vn.icaremobileapp.model.filter.FieldName.regobject;

public class Inpatient extends BaseFragment {
    private View view;
    private List<Fragmentoz> lstFragment = new ArrayList<>();
    private InpatientListAdapter adapter;
    private List<InpatientDomain> lstInpatient;
    private int offset = 0;
    private int limit = 1000;
    private int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.inpatient, container, false);
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

                gotoHappening(p);

            }
        });
    }

    private void gotoHappening(int p) {
        if (getActivity() != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT, lstInpatient.get(p));
            //Fragmentuz.addFragment(getActivity().getSupportFragmentManager(), bundle, Fragmentez.HAPPENINGFRAME, R.id.mainFrame, Directionez.NEXT);
            Fragmentuz.replaceFragment(getActivity().getSupportFragmentManager(), Fragmentez.HAPPENINGFRAME, true, R.id.mainFrame, bundle, Directionez.NEXT);
        }
    }

    private void getMedexa() {
        ApiController.getInstance().getMedexa(getActivity(),
                new ACallback<MedexaHDomain>() {
                    @Override
                    public void response(final List<MedexaHDomain> list) {
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    setupListMedexa(list);
                                }
                            });
                        }
                    }
                });
    }

    private void setupListMedexa(List<MedexaHDomain> lstMedexa) {
        if (getActivity() != null) {
            final MyAutoCompleteTextView acpMedexa = view.findViewById(R.id.acpMedexa);
            AutoCompleteTextViewMedexaAdapter adapterMedexa = new AutoCompleteTextViewMedexaAdapter(getActivity(), lstMedexa);
            acpMedexa.setAdapter(adapterMedexa);
            acpMedexa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MedexaHDomain medexa = (MedexaHDomain) parent.getItemAtPosition(position);
                    if (medexa != null) getInpatient(offset, limit, medexa.getId());
                }
            });

            acpMedexa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpMedexa.showDropDown();
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
                //Get Register
                getPatientRegister(lstInpatient);
                //Get Object
                getPatientObject();
            }
        });
    }

    private void getPatientByPatId(List<InpatientDomain> list) {
        ApiController.getInstance().getPatientByPatId(getActivity(), list, new ACallback<PatientDomain>() {
            @Override
            public void response(final List<PatientDomain> lstPatient) {


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

    private void getPatientObject() {
        ApiController.getInstance().getCateShare(getActivity(),regobject, new ACallback<CateSharelDomain>() {
            @Override
            public void response(final List<CateSharelDomain> listCate) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (InpatientDomain inpatient : lstInpatient)
                            for (CateSharelDomain cate : listCate)
                                if (inpatient.getIdobject() == cate.getIdline()) {
                                    inpatient.setNameObject(cate.getName());
                                    break;
                                }

                    }
                });
            }
        });
    }

    private void getPatientRegister(List<InpatientDomain> list) {
        ApiController.getInstance().getRegisterByIdLink(getActivity(), list, new ACallback<RegisterDomain>() {
            @Override
            public void response(final List<RegisterDomain> listRegister) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (InpatientDomain inpatient : lstInpatient)
                            for (RegisterDomain register : listRegister)
                                if (inpatient.getIdlink().equals(register.getIdlink())) {
                                    inpatient.setRegister(register);
                                    break;
                                }

                    }
                });
            }
        });
    }

}
