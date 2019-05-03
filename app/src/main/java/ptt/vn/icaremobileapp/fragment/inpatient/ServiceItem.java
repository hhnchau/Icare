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

import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.ServiceItemAdapter;
import ptt.vn.icaremobileapp.alert.MyAlert;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.model.filter.Objectez;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.register.RegisterDomain;
import ptt.vn.icaremobileapp.utils.Constant;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewServiceItemAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientServiceOrder;
import ptt.vn.icaremobileapp.model.serviceitem.MapPriceServiceItemLDomain;
import ptt.vn.icaremobileapp.model.serviceitem.ServiceItemDomain;
import ptt.vn.icaremobileapp.storage.Storage;
import ptt.vn.icaremobileapp.utils.Utils;

public class ServiceItem extends BaseFragment {
    private View view;
    private List<ServiceItemDomain> lstServiceItem;
    private ServiceItemAdapter adapterServiceItem;

    private List<MapPriceServiceItemLDomain> lstMapPriceServiceItem;

    private List<InpatientServiceOrder> lstInpatientServiceOrder;

    private InpatientDomain inpatient;

    private int offset = 0;
    private int limit = 1000;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.serviceitem, container, false);

        if (Instruction.happeningDomain != null)
            lstInpatientServiceOrder = Instruction.happeningDomain.getLstInpatientServiceOrder();

        if (getArguments() != null) {
            inpatient = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_INPATIENT);
            if (inpatient != null) {
                RegisterDomain register = inpatient.getRegister();
                if (register != null) {
                    /*Get Price*/
                    getMapPriceServiceItem(offset, limit, register.getPricelist());
                }
            }
        }


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
                    ServiceItemDomain serviceItemDomain = (ServiceItemDomain) parent.getItemAtPosition(position);

                    boolean exist = false;
                    for (InpatientServiceOrder item : lstInpatientServiceOrder)
                        if (item.getIdservice() == serviceItemDomain.getId()) {
                            exist = true;
                            break;
                        }

                    if (!exist) {
                        InpatientServiceOrder inpatientService = new InpatientServiceOrder();
                        inpatientService.setActive(Constant.ACTIVE);
                        inpatientService.setIdline(Utils.newGuid());
                        inpatientService.setIdhappening(Utils.newGuid());
                        inpatientService.setNamehi(serviceItemDomain.getNamehi());
                        inpatientService.setUnitid(serviceItemDomain.getUnitid());
                        inpatientService.setNameunit(serviceItemDomain.getNameunit());
                        inpatientService.setIshi(serviceItemDomain.getIshi());
                        inpatientService.setDescrp(serviceItemDomain.getDescrp());
                        inpatientService.setCode(serviceItemDomain.getCode());
                        inpatientService.setNamehosp(serviceItemDomain.getNamehosp());
                        inpatientService.setIdservice(serviceItemDomain.getId());
                        inpatientService.setDocoder(Storage.getInstance(getActivity()).getUserName());

                        inpatientService.setQty(1);
                        for (MapPriceServiceItemLDomain price : lstMapPriceServiceItem)
                            if (serviceItemDomain.getId() == price.getIdservice()) {
                                inpatientService.setPrice(price.getPrice());
                                inpatientService.setPricehi(price.getPricehi());
                            }

                        lstInpatientServiceOrder.add(inpatientService);
                        adapterServiceItem.setItems(lstInpatientServiceOrder);
                        adapterServiceItem.notifyDataSetChanged();
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
        adapterServiceItem = new ServiceItemAdapter(lstInpatientServiceOrder);
        rcv.setAdapter(adapterServiceItem);
        adapterServiceItem.setOnItemClick(new ServiceItemAdapter.OnItemClick() {
            @Override
            public void onDelete(final int p) {
                if (getActivity() != null) {
                    MyAlert.getInstance().show(getActivity(), getString(R.string.txt_delete_happening), getString(R.string.btn_delete), MyAlert.REB, getString(R.string.btn_cancel), MyAlert.WHITE, false, new MyAlert.OnAlertClickListener() {
                        @Override
                        public void onYes() {
                            /*
                             * DELETE
                             **/
                            InpatientServiceOrder inpatientService = lstInpatientServiceOrder.get(p);
                            inpatientService.setActive(Constant.DELETE);
                            if (Instruction.happeningDomain != null)
                                deleteService(Instruction.happeningDomain, p);
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
                InpatientServiceOrder inpatientService = lstInpatientServiceOrder.get(p);

                if (inpatient.getNameObject().equals(Objectez.BHYT.name()) && lstServiceItem != null) {
                    for (ServiceItemDomain service : lstServiceItem)
                        if (service.getId() == inpatientService.getIdservice()) {
                            if (service.getIshi() == Objectez.DICHVU.ordinal())
                                Toast.makeText(getActivity(), getString(R.string.txt_not_available), Toast.LENGTH_SHORT).show();
                            else {
                                inpatientService.setIshi(inpatientService.getIshi() == Constant.ACTIVE ? Constant.DEACTIVE : Constant.ACTIVE);
                                lstInpatientServiceOrder.set(p, inpatientService);
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
                                    updateListService(lstServiceItem);
                                }
                            });
                        }
                    }
                });
    }

    private void getMapPriceServiceItem(int _offset, int _limit, int _idPrice) {
        ApiController.getInstance().getMapPriceServiceItem(getActivity(), _offset, _limit, _idPrice,
                new ACallback<MapPriceServiceItemLDomain>() {
                    @Override
                    public void response(List<MapPriceServiceItemLDomain> list) {
                        lstMapPriceServiceItem = list;
                    }
                });
    }

    private void updateListService(List<ServiceItemDomain> listServiceItem) {

        if (lstInpatientServiceOrder != null && lstInpatientServiceOrder.size() > 0 && listServiceItem != null && listServiceItem.size() > 0) {
            for (InpatientServiceOrder inpatientService : lstInpatientServiceOrder) {
                for (ServiceItemDomain serviceItem : listServiceItem) {
                    if (inpatientService.getIdservice() == serviceItem.getId()) {

                        inpatientService.setNamehosp(serviceItem.getNamehosp());
                        inpatientService.setCode(serviceItem.getCode());
                        inpatientService.setUnitid(serviceItem.getUnitid());
                        inpatientService.setNameunit(serviceItem.getNameunit());
                        inpatientService.setDescrp(serviceItem.getDescrp());
                        inpatientService.setStatus(serviceItem.getStatus());
                        inpatientService.setNamehi(serviceItem.getNamehi());

                        break;
                    }
                }
            }

            adapterServiceItem.setItems(lstInpatientServiceOrder);
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

}
