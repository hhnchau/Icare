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

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.DiagnoseAdapter;
import ptt.vn.icaremobileapp.adapter.DrugOrderAdapter;

public class Diagnose extends BaseFragment {
    private View view;
    private List<String> lstDiagnose;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diagnose, container, false);


        RecyclerView rcv = view.findViewById(R.id.rcv);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        lstDiagnose = new ArrayList<>();
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        lstDiagnose.add("12");
        DiagnoseAdapter a = new DiagnoseAdapter(lstDiagnose);
        rcv.setAdapter(a);


        a.setOnItemClick(new DiagnoseAdapter.OnItemClick() {
            @Override
            public void onClick(int p) {
                Toast.makeText(getActivity(), "" + p, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
