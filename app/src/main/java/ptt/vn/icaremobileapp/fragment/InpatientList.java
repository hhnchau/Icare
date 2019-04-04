package ptt.vn.icaremobileapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import ptt.vn.icaremobileapp.enums.Directionez;
import ptt.vn.icaremobileapp.enums.Fragmentez;
import ptt.vn.icaremobileapp.utils.Fragmentuz;

public class InpatientList extends BaseFragment {
    private View view;
    private List<String> lstInpatient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.inpatient_list, container, false);
        init();

        return view;
    }


    private void init() {
        RecyclerView rcv = view.findViewById(R.id.rcvInpatientList);
        rcv.setHasFixedSize(true);
        //rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        lstInpatient = new ArrayList<>();
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        lstInpatient.add("12");
        InpatientListAdapter adapter = new InpatientListAdapter(lstInpatient);
        rcv.setAdapter(adapter);


        adapter.setOnItemClick(new InpatientListAdapter.OnItemClick() {
            @Override
            public void onClick(int p) {
                Toast.makeText(getActivity(), "" + p, Toast.LENGTH_SHORT).show();

                if (getActivity() != null) {
                    Fragmentuz.addMainFrame(getActivity().getSupportFragmentManager(), Fragmentez.HAPPENING, R.id.mainFrame, Directionez.NEXT);
                }
            }
        });

    }
}
