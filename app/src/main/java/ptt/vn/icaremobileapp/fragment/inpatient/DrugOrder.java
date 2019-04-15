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
import ptt.vn.icaremobileapp.adapter.DrugOrderAdapter;
import ptt.vn.icaremobileapp.custom.MyInputText;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDrugOrder;

public class DrugOrder extends BaseFragment {
    private View view;
    private List<InpatientDrugOrder> lstDrugOrder;
    private DrugOrderAdapter adapterDrugOrder;

    private MyInputText tvMorning, tvAfter, tvDinner, tvEvening, tvCv1;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drugorder, container, false);

        setupListDrugOrder();

//        tvMorning = view.findViewById(R.id.tvMorning);
//        tvMorning.setCustomView(getString(R.string.txt_morning) + "  (viên)", null, true);
//        tvAfter = view.findViewById(R.id.tvAfter);
//        tvAfter.setCustomView(getString(R.string.txt_after) + "  (viên)", null, true);
//        tvDinner = view.findViewById(R.id.tvDinner);
//        tvDinner.setCustomView(getString(R.string.txt_dinner) + "  (viên)", null, true);
//        tvEvening = view.findViewById(R.id.tvEvening);
//        tvEvening.setCustomView(getString(R.string.txt_evening) + "  (viên)", null, true);
//
//        tvCv1 = view.findViewById(R.id.cv1);
//        tvCv1.setCustomView(getString(R.string.txt_morning) + "  (viên)", null, true);
//        tvCv1.setEnabled(false);


        return view;
    }

    private void setupView(){

    }

    private void setupListDrugOrder() {
        RecyclerView rcv = view.findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        lstDrugOrder = new ArrayList<>();
        adapterDrugOrder = new DrugOrderAdapter(lstDrugOrder);
        rcv.setAdapter(adapterDrugOrder);
        adapterDrugOrder.setOnItemClick(new DrugOrderAdapter.OnItemClick() {
            @Override
            public void onClick(int p) {
                Toast.makeText(getActivity(), "" + p, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
