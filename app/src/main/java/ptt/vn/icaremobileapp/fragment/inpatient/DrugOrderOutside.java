package ptt.vn.icaremobileapp.fragment.inpatient;

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
import ptt.vn.icaremobileapp.adapter.DrugOrderOutSideAdapter;

public class DrugOrderOutside extends BaseFragment {
    private View view;
    private List<String> lstDrugOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drugorderoutside, container, false);

        RecyclerView rcv = view.findViewById(R.id.rcv);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        lstDrugOrder = new ArrayList<>();
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        lstDrugOrder.add("12");
        DrugOrderOutSideAdapter a = new DrugOrderOutSideAdapter(lstDrugOrder);
        rcv.setAdapter(a);


        a.setOnItemClick(new DrugOrderOutSideAdapter.OnItemClick() {
            @Override
            public void onClick(int p) {
                Toast.makeText(getActivity(), "" + p, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
