package ptt.vn.icaremobileapp.fragment.inpatient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
    private List<AutoComplete> lstAutoHappeningType;
    private AutoCompleteTextViewAdapter adapterAutoHappeningType;
    private List<AutoComplete> lstAutoDrugRoute;
    private AutoCompleteTextViewAdapter adapterAutoDrugRoute;
    private List<InpatientDrugOrder> lstDrugOrder;
    private DrugOrderAdapter adapterDrugOrder;

    private InpatientDrugOrder drugOrder = new InpatientDrugOrder();

    private int offset = 0;
    private int limit = 1000;
    private MyAutoCompleteTextView acpDrug, acpHappeningType, acpDrugRoute;
    private MyInputTextOutline edtActiveIngre, edtDrugMorning, edtDrugAfter, edtDrugDinner, edtDrugEvening, edtDrugNumber, edtDrugTotal, edtDrugReason;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drugorder, container, false);

        setupView();
        setupDrugOrder();
        setupHappeningType();
        setupDrugRoute();
        setupListDrugOrder();
        getDrugRoute();
        getHappeningType();
        getPhaInventory(offset, limit, 1, 0);

        return view;
    }

    private void setupView() {
        MyButton btnAdd = view.findViewById(R.id.btnAdd);
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
            edtActiveIngre.setText(inpatientDrugOrder.getActivename());
            edtDrugMorning.setText(inpatientDrugOrder.getQtymor());
            edtDrugAfter.setText(inpatientDrugOrder.getQtyaft());
            edtDrugDinner.setText(inpatientDrugOrder.getQtydin());
            edtDrugEvening.setText(inpatientDrugOrder.getQtynig());
            edtDrugNumber.setText(String.valueOf(inpatientDrugOrder.getQtyday()));
            edtDrugTotal.setText(String.valueOf(inpatientDrugOrder.getQty()));
            edtDrugReason.setText(inpatientDrugOrder.getDesc());
        }
    }

    private void setupDrugOrder() {
        if (getActivity() != null) {
            acpDrug = view.findViewById(R.id.acDrug);

            lstAuto = new ArrayList<>();
            adapterAuto = new AutoCompleteTextViewDrugOrderAdapter(getActivity(), lstAuto);
            acpDrug.setAdapter(adapterAuto);
            acpDrug.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PhaInventoryDomain phaInventory = (PhaInventoryDomain) parent.getItemAtPosition(position);

                    /*
                     * Add to Domain
                     **/
                    if (drugOrder != null) {
                        drugOrder.setIddrug(phaInventory.getIddrug());
                        drugOrder.setActivename(phaInventory.getCode());
                    }

                    /*
                     * Add to ActiveIngre
                     */
                    edtActiveIngre.setText(phaInventory.getNameactiveingre(), false);

                    /*
                     * Add to Route
                     **/
                    for (int i = 0; i < lstAutoDrugRoute.size(); i++)
                        if (phaInventory.getIdroute() == lstAutoDrugRoute.get(i).getId()) {
                            acpDrugRoute.setText(lstAutoDrugRoute.get(i).getName());
                            break;
                        }

                }
            });

            acpDrug.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpDrug.showDropDown();
                }
            });
        }
    }

    private void setupHappeningType() {
        if (getActivity() != null) {
            acpHappeningType = view.findViewById(R.id.acHappeningType);

            lstAutoHappeningType = new ArrayList<>();
            adapterAutoHappeningType = new AutoCompleteTextViewAdapter(getActivity(), lstAutoHappeningType);
            acpHappeningType.setAdapter(adapterAutoHappeningType);
            acpHappeningType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AutoComplete autoComplete = (AutoComplete) parent.getItemAtPosition(position);
                    /*
                     * Add to Domain
                     **/
                    if (drugOrder != null) {

                    }

                }
            });

            acpHappeningType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpHappeningType.showDropDown();
                }
            });
        }
    }

    private void setupDrugRoute() {
        if (getActivity() != null) {
            acpDrugRoute = view.findViewById(R.id.acDrugRoute);

            lstAutoDrugRoute = new ArrayList<>();
            adapterAutoDrugRoute = new AutoCompleteTextViewAdapter(getActivity(), lstAutoDrugRoute);
            acpDrugRoute.setAdapter(adapterAutoDrugRoute);
            acpDrugRoute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AutoComplete autoComplete = (AutoComplete) parent.getItemAtPosition(position);

                    Toast.makeText(getActivity(), autoComplete.getName(), Toast.LENGTH_SHORT).show();
                }
            });

            acpDrugRoute.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpDrugRoute.showDropDown();
                }
            });
        }
    }

    private void setupListDrugOrder() {
        RecyclerView rcv = view.findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        if (Instruction.happeningDomain != null)
            lstDrugOrder = Instruction.happeningDomain.getLstInpatientDrugOrder();

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
                        for (CateSharelDomain item : list) {
                            lstAutoDrugRoute.add(new AutoComplete(item.getIdline(), item.getName()));
                        }
                        adapterAutoDrugRoute.setItems(lstAutoDrugRoute);
                        adapterAutoDrugRoute.notifyDataSetChanged();
                    }
                });
    }

    private void getHappeningType() {
        ApiController.getInstance().getHappeningType(getActivity(),
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(List<CateSharelDomain> list) {
                        for (CateSharelDomain item : list) {
                            lstAutoHappeningType.add(new AutoComplete(item.getIdline(), item.getName()));
                        }
                        adapterAutoHappeningType.setItems(lstAutoHappeningType);
                        adapterAutoHappeningType.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onClick() {
        String drug = acpDrug.getText().toString();
        if (TextUtils.isEmpty(drug)) {
            Toast.makeText(getActivity(), getString(R.string.txt_err_drug), Toast.LENGTH_SHORT).show();
            acpDrug.setError("*");
            return;
        }
        String type = acpHappeningType.getText().toString();
        String route = acpDrugRoute.getText().toString();
        String morning = edtDrugMorning.getText().toString();
        String after = edtDrugAfter.getText().toString();
        String dinner = edtDrugDinner.getText().toString();
        String evening = edtDrugEvening.getText().toString();
        String number = edtDrugNumber.getText().toString();
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(getActivity(), getString(R.string.txt_err_number), Toast.LENGTH_SHORT).show();
            edtDrugNumber.setError("*");
            return;
        }
        String total = edtDrugTotal.getText().toString();
        String reason = edtDrugReason.getText().toString();

        /*
         * Check Exist
         */

        drugOrder = new InpatientDrugOrder();
    }
}
