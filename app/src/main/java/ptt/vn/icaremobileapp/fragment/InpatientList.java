package ptt.vn.icaremobileapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.InpatientListAdapter;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.CompositeManager;
import ptt.vn.icaremobileapp.enums.Directionez;
import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.model.filter.Method;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.utils.Fragmentuz;

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getInpatient(offset, limit, Method.GetInpatientInDepartment, 1);
            }
        }, 2000);


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
                    Fragmentuz.addMainFrame(getActivity().getSupportFragmentManager(), bundle, Fragmentez.HAPPENING, R.id.mainFrame, Directionez.NEXT);
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    private void getInpatient(int _offset, int _limit, Method _method, int _idmedexa) {
        ApiController.getInstance().getInpatient(getActivity(), _offset, _limit, _method, _idmedexa, new ACallback<InpatientDomain>() {
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
