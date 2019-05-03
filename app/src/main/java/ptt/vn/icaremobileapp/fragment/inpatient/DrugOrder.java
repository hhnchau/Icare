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
import ptt.vn.icaremobileapp.alert.MyAlert;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.model.filter.Objectez;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.utils.Constant;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewAdapter;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewDrugOrderAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDrugOrder;
import ptt.vn.icaremobileapp.model.pharmacy.PhaInventoryDomain;
import ptt.vn.icaremobileapp.utils.Utils;

import static ptt.vn.icaremobileapp.model.filter.FieldName.routedrug;
import static ptt.vn.icaremobileapp.model.filter.FieldName.typemedicalchart;
import static ptt.vn.icaremobileapp.model.filter.FieldName.unitusedrug;

public class DrugOrder extends BaseFragment implements MyButton.OnListener {
    private View view;

    private List<CateSharelDomain> lstAutoDrugRoute = new ArrayList<>();

    private List<CateSharelDomain> lstDrugUnitUse;

    private List<InpatientDrugOrder> lstDrugOrder;
    private DrugOrderAdapter adapterDrugOrder;

    List<PhaInventoryDomain> lstPhaInventory;
    private PhaInventoryDomain phaInventoryDomain;
    private CateSharelDomain happeningType;
    private CateSharelDomain drugRoute;
    private CateSharelDomain drugUnitUse;

    private InpatientDomain inpatient;

    private int offset = 0;
    private int limit = 1000;
    private MyAutoCompleteTextView acpDrug, acpHappeningType, acpDrugRoute, acpDrugUnitUse;
    private MyInputTextOutline edtActiveIngre, edtDrugMorning, edtDrugAfter, edtDrugDinner, edtDrugEvening, edtDrugNumber, edtDrugTotal, edtDrugReason;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drugorder, container, false);

        if (getArguments() != null) {
            inpatient = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT);
            if (inpatient != null) {

                setupView();
                setupListDrugOrder();
                getDrugRoute();
                getHappeningType();
                getDrugUnitUse();
                getPhaInventory(offset, limit, 1, inpatient.getNameObject().equals(Objectez.BHYT.name()) ? Objectez.BHYT.ordinal() : Objectez.DICHVU.ordinal());


            }
        }

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

    private void setupDrugOrder(List<PhaInventoryDomain> lstPhaInventory) {
        if (getActivity() != null) {
            acpDrug = view.findViewById(R.id.acDrug);

            AutoCompleteTextViewDrugOrderAdapter adapterAuto = new AutoCompleteTextViewDrugOrderAdapter(getActivity(), lstPhaInventory);
            acpDrug.setAdapter(adapterAuto);
            acpDrug.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    phaInventoryDomain = (PhaInventoryDomain) parent.getItemAtPosition(position);

                    edtActiveIngre.setText(phaInventoryDomain.getNameactiveingre(), false);

                    for (int i = 0; i < lstAutoDrugRoute.size(); i++)
                        if (phaInventoryDomain.getIdroute() == lstAutoDrugRoute.get(i).getIdline()) {
                            acpDrugRoute.setText(lstAutoDrugRoute.get(i).getName());
                            drugRoute = lstAutoDrugRoute.get(i);
                            break;
                        }

                    for (int i = 0; i < lstDrugUnitUse.size(); i++)
                        if (phaInventoryDomain.getIdunituse() == lstDrugUnitUse.get(i).getIdline()) {
                            acpDrugUnitUse.setText(lstDrugUnitUse.get(i).getName());
                            drugUnitUse = lstDrugUnitUse.get(i);
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

    private void setupDrugUnitUse() {
        if (getActivity() != null) {
            acpDrugUnitUse = view.findViewById(R.id.acpDrugUnitUse);

            AutoCompleteTextViewAdapter adapterDrugUnitUse = new AutoCompleteTextViewAdapter(getActivity(), lstDrugUnitUse);
            acpDrugUnitUse.setAdapter(adapterDrugUnitUse);
            acpDrugUnitUse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    drugUnitUse = (CateSharelDomain) parent.getItemAtPosition(position);

                }
            });

            acpDrugUnitUse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpDrugUnitUse.showDropDown();
                }
            });
        }
    }

    private void setupHappeningType(List<CateSharelDomain> lstAutoHappeningType) {
        if (getActivity() != null) {
            acpHappeningType = view.findViewById(R.id.acHappeningType);

            AutoCompleteTextViewAdapter adapterAutoHappeningType = new AutoCompleteTextViewAdapter(getActivity(), lstAutoHappeningType);
            acpHappeningType.setAdapter(adapterAutoHappeningType);
            acpHappeningType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    happeningType = (CateSharelDomain) parent.getItemAtPosition(position);
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

            AutoCompleteTextViewAdapter adapterAutoDrugRoute = new AutoCompleteTextViewAdapter(getActivity(), lstAutoDrugRoute);
            acpDrugRoute.setAdapter(adapterAutoDrugRoute);
            acpDrugRoute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    drugRoute = (CateSharelDomain) parent.getItemAtPosition(position);
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
            public void onIsHi(int p) {
                InpatientDrugOrder inpatientDrugOrder = lstDrugOrder.get(p);

                if (inpatient.getNameObject().equals(Objectez.BHYT.name()) && lstPhaInventory != null) {
                    for (PhaInventoryDomain pha : lstPhaInventory)
                        if (pha.getIddrug() == inpatientDrugOrder.getIddrug()) {
                            if (pha.getIshi() == Objectez.DICHVU.ordinal())
                                Toast.makeText(getActivity(), getString(R.string.txt_not_available), Toast.LENGTH_SHORT).show();
                            else {
                                inpatientDrugOrder.setInsurance(inpatientDrugOrder.getInsurance() == Constant.ACTIVE ? Constant.DEACTIVE : Constant.ACTIVE);
                                lstDrugOrder.set(p, inpatientDrugOrder);
                                adapterDrugOrder.notifyItemChanged(p);
                            }

                            break;
                        }
                } else {
                    Toast.makeText(getActivity(), getString(R.string.txt_not_available), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onEdit(InpatientDrugOrder inpatientDrugOrder) {

            }

            @Override
            public void onDelete(final int p) {
                if (getActivity() != null) {
                    MyAlert.getInstance().show(getActivity(), getString(R.string.txt_delete_happening), getString(R.string.btn_delete), MyAlert.REB, getString(R.string.btn_cancel), MyAlert.WHITE, false, new MyAlert.OnAlertClickListener() {
                        @Override
                        public void onYes() {
                            /*
                             * DELETE
                             **/
                            InpatientDrugOrder inpatientDrugOrder = lstDrugOrder.get(p);
                            inpatientDrugOrder.setActive(Constant.DELETE);
                            if (Instruction.happeningDomain != null)
                                deleteDrugOrder(Instruction.happeningDomain, p);
                        }

                        @Override
                        public void onNo() {

                        }
                    });
                }
            }
        });
    }

    private void getPhaInventory(int _offset, int _limit, int _idStore, int _isHi) {
        ApiController.getInstance().getPhaInventory(getActivity(), _offset, _limit, _idStore, _isHi,
                new ACallback<PhaInventoryDomain>() {
                    @Override
                    public void response(final List<PhaInventoryDomain> listInventory) {
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    lstPhaInventory = listInventory;
                                    setupDrugOrder(listInventory);
                                }
                            });
                        }
                    }
                });
    }

    private void getDrugRoute() {
        ApiController.getInstance().getCateShare(getActivity(),routedrug,
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(final List<CateSharelDomain> list) {
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    lstAutoDrugRoute = list;
                                    setupDrugRoute();
                                }
                            });
                        }
                    }
                });
    }

    private void getDrugUnitUse() {
        ApiController.getInstance().getCateShare(getActivity(),unitusedrug,
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(final List<CateSharelDomain> list) {
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    lstDrugUnitUse = list;
                                    setupDrugUnitUse();
                                }
                            });
                        }
                    }
                });
    }

    private void getHappeningType() {
        ApiController.getInstance().getCateShare(getActivity(),typemedicalchart,
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(final List<CateSharelDomain> list) {
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    setupHappeningType(list);
                                }
                            });
                        }
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
        boolean exist = false;
        for (InpatientDrugOrder item : lstDrugOrder)
            if (phaInventoryDomain.getIddrug() == item.getIddrug()) {
                exist = true;
                break;
            }

        if (!exist) {
            InpatientDrugOrder drugOrder = new InpatientDrugOrder();
            drugOrder.setActive(Constant.ACTIVE);
            drugOrder.setIdline(Utils.newGuid());
            drugOrder.setIdhappening(Utils.newGuid());
            if (phaInventoryDomain != null) {
                drugOrder.setIddrug(phaInventoryDomain.getIddrug());
                drugOrder.setCodedrug(phaInventoryDomain.getCode());
                drugOrder.setNamedrug(phaInventoryDomain.getName());
                drugOrder.setActivename(phaInventoryDomain.getNameactiveingre());
                drugOrder.setIdstore(phaInventoryDomain.getIdstore());
                drugOrder.setIdunit(phaInventoryDomain.getIdunit());
                drugOrder.setPrice(phaInventoryDomain.getPrice());
                drugOrder.setInsurance(phaInventoryDomain.getIshi());
            }
            if (drugRoute != null) drugOrder.setIdroute(drugRoute.getIdline());


            if (drugUnitUse != null) drugOrder.setIdunituse(drugUnitUse.getIdline());

            drugOrder.setQtymor(morning);
            drugOrder.setQtyaft(after);
            drugOrder.setQtydin(dinner);
            drugOrder.setQtynig(evening);
            drugOrder.setQty(Float.parseFloat(total));
            drugOrder.setQtyday(Integer.parseInt(number));
            drugOrder.setTotal(phaInventoryDomain.getPrice() * Float.parseFloat(total));
            drugOrder.setDesc(reason);

            lstDrugOrder.add(drugOrder);
            adapterDrugOrder.setItems(lstDrugOrder);
            adapterDrugOrder.notifyDataSetChanged();
        }
    }

    public void deleteDrugOrder(HappeningDomain happening, final int p) {
        ApiController.getInstance().saveHappening(getActivity(), happening, new Callback<HappeningDomain>() {
            @Override
            public void response(HappeningDomain happening) {
                adapterDrugOrder.removeItem(p);
                Toast.makeText(getActivity(), getString(R.string.txt_delete_success), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
