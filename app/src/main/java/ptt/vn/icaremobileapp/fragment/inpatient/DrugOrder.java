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

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.DrugOrderAdapter;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewAdapter;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewDrugOrderAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.model.common.AutoComplete;
import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDrugOrder;
import ptt.vn.icaremobileapp.model.pharmacy.PhaInventoryDomain;

public class DrugOrder extends BaseFragment implements MyButton.OnListener {
    private View view;
    private List<PhaInventoryDomain> lstAuto;
    private AutoCompleteTextViewDrugOrderAdapter adapterAuto;
    private List<AutoComplete> lstAutoDrugType;
    private AutoCompleteTextViewAdapter adapterAutoHappeningType;
    private List<AutoComplete> lstAutoHappeningRoute;
    private AutoCompleteTextViewAdapter adapterAutoDrugRoute;
    private List<InpatientDrugOrder> lstDrugOrder;
    private DrugOrderAdapter adapterDrugOrder;
    private int offset = 0;
    private int limit = 1000;

    private MyInputTextOutline edtActiveIngre, edtDrugMorning, edtDrugAfter, edtDrugDinner, edtDrugEvening, edtDrugNumber, edtDrugTotal, edtDrugReason;
    private MyButton btnAdd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drugorder, container, false);

        setupView();
        setupDrugOrder();
        setupDrugType();
        setupDrugRoute();
        setupListDrugOrder();
        getDrugRoute();
        getHappeningType();
        getPhaInventory(offset, limit, 1, 0);

        return view;
    }

    private void setupView() {
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnSelectedListener(this);
        edtActiveIngre = view.findViewById(R.id.edtActiveIngre);
        edtDrugMorning = view.findViewById(R.id.edtDrugMorning);
        edtDrugAfter = view.findViewById(R.id.edtDrugAfter);
        edtDrugDinner = view.findViewById(R.id.edtDrugDinner);
        edtDrugEvening = view.findViewById(R.id.edtDrugEvening);
        edtDrugNumber = view.findViewById(R.id.edtDrugNumber);
        edtDrugTotal = view.findViewById(R.id.edtDrugTotal);
        edtDrugReason = view.findViewById(R.id.edtDrugReason);
    }

    private void setView(InpatientDrugOrder inpatientDrugOrder) {
        if (inpatientDrugOrder != null) {
            edtActiveIngre.setValue(inpatientDrugOrder.getActivename());
            edtDrugMorning.setValue(inpatientDrugOrder.getQtymor());
            edtDrugAfter.setValue(inpatientDrugOrder.getQtyaft());
            edtDrugDinner.setValue(inpatientDrugOrder.getQtydin());
            edtDrugEvening.setValue(inpatientDrugOrder.getQtynig());
            edtDrugNumber.setValue(String.valueOf(inpatientDrugOrder.getQtyday()));
            edtDrugTotal.setValue(String.valueOf(inpatientDrugOrder.getQty()));
            edtDrugReason.setValue(inpatientDrugOrder.getDesc());
        }
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

    private void setupDrugType() {
        if (getActivity() != null) {
            final MyAutoCompleteTextView myAutoCompleteTextView = view.findViewById(R.id.acDrugtype);

            lstAutoDrugType = new ArrayList<>();
            adapterAutoHappeningType = new AutoCompleteTextViewAdapter(getActivity(), lstAutoDrugType);
            myAutoCompleteTextView.setAdapter(adapterAutoHappeningType);
            myAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AutoComplete autoComplete = (AutoComplete) parent.getItemAtPosition(position);
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

    private void setupDrugRoute() {
        if (getActivity() != null) {
            final MyAutoCompleteTextView myAutoCompleteTextView = view.findViewById(R.id.acDrugRoute);

            lstAutoHappeningRoute = new ArrayList<>();
            adapterAutoDrugRoute = new AutoCompleteTextViewAdapter(getActivity(), lstAutoHappeningRoute);
            myAutoCompleteTextView.setAdapter(adapterAutoDrugRoute);
            myAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AutoComplete autoComplete = (AutoComplete) parent.getItemAtPosition(position);
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
            public void onClick(InpatientDrugOrder inpatientDrugOrder) {
                setView(inpatientDrugOrder);
            }

            @Override
            public void onEdit(InpatientDrugOrder inpatientDrugOrder) {

            }

            @Override
            public void onDelete(InpatientDrugOrder inpatientDrugOrder) {

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

    private void getDrugRoute() {
        ApiController.getInstance().getDrugRoute(getActivity(),
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(List<CateSharelDomain> list) {
                        for (CateSharelDomain item: list){
                            lstAutoHappeningRoute.add(new AutoComplete(item.getIdline(), item.getName()));
                        }
                        adapterAutoDrugRoute.setItems(lstAutoHappeningRoute);
                        adapterAutoDrugRoute.notifyDataSetChanged();
                    }
                });
    }

    private void getHappeningType() {
        ApiController.getInstance().getHappeningType(getActivity(),
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(List<CateSharelDomain> list) {
                        for (CateSharelDomain item: list){
                            lstAutoDrugType.add(new AutoComplete(item.getIdline(), item.getName()));
                        }
                        adapterAutoHappeningType.setItems(lstAutoDrugType);
                        adapterAutoHappeningType.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onClick() {
        boolean validate = true;
        validate = validateInputText(edtActiveIngre);
    }

    private boolean validateInputText(MyInputTextOutline edt){
        String s = edt.getText().toString();
        if (s.matches("")) {
            edt.setError("*");
            return false;
        }
        return true;
    }
}
