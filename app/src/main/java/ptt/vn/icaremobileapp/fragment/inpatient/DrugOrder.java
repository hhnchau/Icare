package ptt.vn.icaremobileapp.fragment.inpatient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.DrugOrderAdapter;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewDrugOrderAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDrugOrder;
import ptt.vn.icaremobileapp.model.pharmacy.PhaInventoryDomain;

public class DrugOrder extends BaseFragment {
    private View view;
    private List<PhaInventoryDomain> lstAuto;
    private AutoCompleteTextViewDrugOrderAdapter adapterAuto;
    private List<InpatientDrugOrder> lstDrugOrder;
    private DrugOrderAdapter adapterDrugOrder;
    private int offset = 0;
    private int limit = 1000;

    private MyInputTextOutline tvMorning, tvAfter, tvDinner, tvEvening, tvCv1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drugorder, container, false);

        setupDrugOrder();
        setupListDrugOrder();
        getPhaInventory(offset, limit, 1, 0);

        return view;
    }

    private void setupView() {
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
    }

    private void setupDrugOrder() {
        if (getActivity() != null) {
            final MyAutoCompleteTextView myAutoCompleteTextView = view.findViewById(R.id.acDrug);

            lstAuto = new ArrayList<>();
            adapterAuto = new AutoCompleteTextViewDrugOrderAdapter(getActivity(), lstAuto);
            myAutoCompleteTextView.setAdapter(adapterAuto);
            myAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PhaInventoryDomain phaInventory = (PhaInventoryDomain) parent.getItemAtPosition(position);
                    InpatientDrugOrder drugOrder = new InpatientDrugOrder();
                    drugOrder.setActivename(phaInventory.getCode());

                    boolean exist = false;
                    for (InpatientDrugOrder item : lstDrugOrder)
                        if (item.getIddrug() == phaInventory.getIddrug()) {
                            exist = true;
                            //add
                            break;
                        }

                    if (!exist) {
                        lstDrugOrder.add(drugOrder);
                        adapterDrugOrder.setItems(lstDrugOrder);
                        adapterDrugOrder.notifyDataSetChanged();
                    }
                }
            });

            myAutoCompleteTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myAutoCompleteTextView.showDropDown();
                }
            });
        }

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

    private void getPhaInventory(int _offset, int _limit, int _idStore, int _isHi) {
        ApiController.getInstance().getPhaInventory(getActivity(), _offset, _limit, _idStore, _isHi,
                new ACallback<PhaInventoryDomain>() {
                    @Override
                    public void response(List<PhaInventoryDomain> listInventory) {
                        lstAuto = listInventory;
                        adapterAuto.setItems(lstAuto);
                        adapterAuto.notifyDataSetChanged();
                    }
                });
    }

}
