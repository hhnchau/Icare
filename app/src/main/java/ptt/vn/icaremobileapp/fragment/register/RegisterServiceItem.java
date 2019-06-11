package ptt.vn.icaremobileapp.fragment.register;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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

import ptt.vn.icaremobileapp.adapter.RegisterServiceOrderAdapter;
import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.ServiceItemAdapter;
import ptt.vn.icaremobileapp.alert.MyAlert;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewServiceItemAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.fragment.inpatient.Instruction;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.model.filter.Objectez;
import ptt.vn.icaremobileapp.model.hi.HiDomain;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientServiceOrder;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.model.register.RegisterDomain;
import ptt.vn.icaremobileapp.model.register.RegisterServiceOrder;
import ptt.vn.icaremobileapp.model.serviceitem.MapPriceServiceItemLDomain;
import ptt.vn.icaremobileapp.model.serviceitem.ServiceItemDomain;
import ptt.vn.icaremobileapp.model.shared.HiLiveData;
import ptt.vn.icaremobileapp.model.shared.PriceLiveData;
import ptt.vn.icaremobileapp.storage.Storage;
import ptt.vn.icaremobileapp.utils.Constant;
import ptt.vn.icaremobileapp.utils.Utils;


public class RegisterServiceItem extends BaseFragment {
    private View view;
    private List<ServiceItemDomain> lstServiceItem;
    private RegisterServiceOrderAdapter adapterServiceItem;

    private List<RegisterServiceOrder> lstRegisterServiceOrder = new ArrayList<>();

    private List<MapPriceServiceItemLDomain> lstMapPriceServiceItem = new ArrayList<>();


    private int offset = 0;
    private int limit = 1000;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            PriceLiveData priceLiveData = ViewModelProviders.of(getActivity()).get(PriceLiveData.class);
            priceLiveData.getPriceLiveData().observe(getActivity(), new Observer<List<MapPriceServiceItemLDomain>>() {
                @Override
                public void onChanged(@Nullable List<MapPriceServiceItemLDomain> list) {
                    lstMapPriceServiceItem = list;
                }
            });
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register_serviceitem, container, false);

        setupListServiceItem();

        getServiceItem(offset, limit);

        return view;
    }

    private void setupServiceItem(List<ServiceItemDomain> lstAuto) {
        if (getActivity() != null) {
            final MyAutoCompleteTextView myAutoCompleteTextView = view.findViewById(R.id.acServiceItem);
            AutoCompleteTextViewServiceItemAdapter adapterAuto = new AutoCompleteTextViewServiceItemAdapter(getActivity(), lstAuto);
            myAutoCompleteTextView.setAdapter(adapterAuto);
            myAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), myAutoCompleteTextView);
                    myAutoCompleteTextView.setValue("");
                    ServiceItemDomain serviceItemDomain = (ServiceItemDomain) parent.getItemAtPosition(position);

                    boolean exist = false;
                    for (RegisterServiceOrder item : lstRegisterServiceOrder)
                        if (item.getIdservitem() == serviceItemDomain.getId()) {
                            exist = true;
                            break;
                        }

                    if (!exist) {
                        RegisterServiceOrder registerServiceOrder = new RegisterServiceOrder();
                        registerServiceOrder.setPatid(Utils.newGuid());
                        registerServiceOrder.setIdline(Utils.newGuid());
                        registerServiceOrder.setDateorder(Utils.getCurrentDate(Utils.ddMMyyyyTHHmmss));
                        registerServiceOrder.setDateapp(Utils.getCurrentDate(Utils.ddMMyyyyTHHmmss));
                        registerServiceOrder.setIdservitem(serviceItemDomain.getId());
                        registerServiceOrder.setServcode(serviceItemDomain.getCode());
                        registerServiceOrder.setServname(serviceItemDomain.getNamehosp());
                        registerServiceOrder.setNameunit(serviceItemDomain.getNameunit());
                        registerServiceOrder.setQty(1);
                        registerServiceOrder.setIshi(serviceItemDomain.getIshi());
                        for (MapPriceServiceItemLDomain price : lstMapPriceServiceItem)
                            if (serviceItemDomain.getId() == price.getIdservice()) {
                                registerServiceOrder.setPrice(price.getPrice());
                                registerServiceOrder.setPricehi(price.getPricehi());
                            }

                        lstRegisterServiceOrder.add(registerServiceOrder);
                        adapterServiceItem.setItems(lstRegisterServiceOrder);
                        adapterServiceItem.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getActivity(), "Đã tồn tại", Toast.LENGTH_SHORT).show();
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

    private void setupListServiceItem() {
        RecyclerView rcv = view.findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapterServiceItem = new RegisterServiceOrderAdapter(lstRegisterServiceOrder);
        rcv.setAdapter(adapterServiceItem);
        adapterServiceItem.setOnItemClick(new RegisterServiceOrderAdapter.OnItemClick() {
            @Override
            public void onDelete(final int p) {
                if (getActivity() != null) {
                    MyAlert.getInstance().show(getActivity(), getString(R.string.txt_delete_happening), getString(R.string.btn_delete), MyAlert.REB, getString(R.string.btn_cancel), MyAlert.WHITE, false, new MyAlert.OnAlertClickListener() {
                        @Override
                        public void onYes() {
                            /*
                             * DELETE
                             **/
                            adapterServiceItem.removeItem(p);
                        }

                        @Override
                        public void onNo() {

                        }
                    });
                }
            }

            @Override
            public void onEdit(int p) {

            }

            @Override
            public void onIsHi(int p) {
                RegisterServiceOrder registerServiceOrder = lstRegisterServiceOrder.get(p);

                if (RegisterReceive.idPatientObject == Objectez.BHYT.getValue() && lstServiceItem != null) {
                    for (ServiceItemDomain service : lstServiceItem)
                        if (service.getId() == registerServiceOrder.getIdservitem()) {
                            if (service.getIshi() == Objectez.DICHVU.ordinal())
                                Toast.makeText(getActivity(), getString(R.string.txt_not_available), Toast.LENGTH_SHORT).show();
                            else {
                                registerServiceOrder.setIshi(registerServiceOrder.getIshi() == Constant.ACTIVE ? Constant.DEACTIVE : Constant.ACTIVE);
                                lstRegisterServiceOrder.set(p, registerServiceOrder);
                                adapterServiceItem.notifyItemChanged(p);
                            }

                            break;
                        }
                } else {
                    Toast.makeText(getActivity(), getString(R.string.txt_not_available), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getServiceItem(int _offset, int _limit) {
        ApiController.getInstance().getServiceItem(getActivity(), _offset, _limit,
                new ACallback<ServiceItemDomain>() {
                    @Override
                    public void response(final List<ServiceItemDomain> list) {
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {

                                    lstServiceItem = list;

                                    /*Update Suggest Search*/
                                    setupServiceItem(lstServiceItem);

                                    /*Join Happening To Service*/
                                    //updateListService(lstServiceItem);
                                }
                            });
                        }
                    }
                });
    }


    private void updateListService(List<ServiceItemDomain> listServiceItem) {

        if (lstRegisterServiceOrder != null && lstRegisterServiceOrder.size() > 0 && listServiceItem != null && listServiceItem.size() > 0) {
            for (RegisterServiceOrder inpatientService : lstRegisterServiceOrder) {
                for (ServiceItemDomain serviceItem : listServiceItem) {
                    if (inpatientService.getIdservitem() == serviceItem.getId()) {

                        inpatientService.setNamehosp(serviceItem.getNamehosp());
                        inpatientService.setServcode(serviceItem.getCode());
                        //inpatientService.setUnitid(serviceItem.getUnitid());
                        inpatientService.setNameunit(serviceItem.getNameunit());
                        //inpatientService.setDescrp(serviceItem.getDescrp());
                        inpatientService.setStatus(serviceItem.getStatus());
                        inpatientService.setNamehosp(serviceItem.getNamehi());

                        break;
                    }
                }
            }

            adapterServiceItem.setItems(lstRegisterServiceOrder);
            adapterServiceItem.notifyDataSetChanged();

        }

    }

    public void deleteService(HappeningDomain happening, final int p) {
        ApiController.getInstance().saveHappening(getActivity(), happening, new Callback<HappeningDomain>() {
            @Override
            public void response(HappeningDomain happening) {
                adapterServiceItem.removeItem(p);
                Toast.makeText(getActivity(), getString(R.string.txt_delete_success), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean validate() {
        if (lstRegisterServiceOrder == null || lstRegisterServiceOrder.size() == 0) return false;
        Register.registerDomain.setLstRegServiceOrder(lstRegisterServiceOrder);
        return true;
    }

    @Override
    public void toolbarListener() {

    }
}
