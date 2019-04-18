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
import ptt.vn.icaremobileapp.adapter.ServiceItemAdapter;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Host;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewServiceItemAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.model.inpatient.InpatientServiceOrder;
import ptt.vn.icaremobileapp.model.serviceitem.MapPriceServiceItemLDomain;
import ptt.vn.icaremobileapp.model.serviceitem.ServiceItemDomain;
import ptt.vn.icaremobileapp.storage.Storage;
import ptt.vn.icaremobileapp.utils.Utils;

public class ServiceItem extends BaseFragment {
    private View view;
    private List<ServiceItemDomain> lstAuto;
    private AutoCompleteTextViewServiceItemAdapter adapterAuto;
    private List<ServiceItemDomain> lstServiceItem;
    private ServiceItemAdapter adapterServiceItem;

    private List<InpatientServiceOrder> lstInpatientServiceOrder;

    private int offset = 0;
    private int limit = 1000;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.serviceitem, container, false);

        setupServiceItem();

        setupListServiceItem();

        getServiceItem(offset, limit);

        return view;
    }

    private void setupServiceItem() {
        if (getActivity() != null) {
            final MyAutoCompleteTextView myAutoCompleteTextView = view.findViewById(R.id.acServiceItem);

            lstAuto = new ArrayList<>();
            adapterAuto = new AutoCompleteTextViewServiceItemAdapter(getActivity(), lstAuto);
            myAutoCompleteTextView.setAdapter(adapterAuto);
            myAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ServiceItemDomain serviceItemDomain = (ServiceItemDomain) parent.getItemAtPosition(position);

                    boolean exist = false;
                    for (ServiceItemDomain item : lstServiceItem)
                        if (item.getId() == serviceItemDomain.getId()) {
                            exist = true;
                            break;
                        }

                    if (!exist) {
                        serviceItemDomain.setDateapp(Utils.getCurrentDate(Utils.ddMMyyyyHHmm));
                        serviceItemDomain.setDocoder(Storage.getInstance(getActivity()).getUserName());
                        lstServiceItem.add(serviceItemDomain);
                        adapterServiceItem.setItems(lstServiceItem);
                        adapterServiceItem.notifyDataSetChanged();

                        //Update Domain
                        InpatientServiceOrder inpatientServiceOrder = new InpatientServiceOrder();
                        inpatientServiceOrder.setActive(Host.ACTIVE);
                        inpatientServiceOrder.setIdline(Utils.newGuid());
                        inpatientServiceOrder.setIdhappening(Utils.newGuid());
                        inpatientServiceOrder.setIdservice(serviceItemDomain.getId());
                        inpatientServiceOrder.setPrice(serviceItemDomain.getPrice());
                        inpatientServiceOrder.setPricehi(serviceItemDomain.getPricehi());
                        inpatientServiceOrder.setQty(serviceItemDomain.getQty());
                        lstInpatientServiceOrder.add(inpatientServiceOrder);
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
        lstServiceItem = new ArrayList<>();
        adapterServiceItem = new ServiceItemAdapter(lstServiceItem);
        rcv.setAdapter(adapterServiceItem);
        adapterServiceItem.setOnItemClick(new ServiceItemAdapter.OnItemClick() {
            @Override
            public void onClick(ServiceItemDomain serviceItemDomain) {
                if (getActivity() != null) {
                    //Update Domain
                    for (int i = 0; i < lstInpatientServiceOrder.size(); i++)
                        if (serviceItemDomain.getId() == lstInpatientServiceOrder.get(i).getIdservice()) {
                            lstInpatientServiceOrder.remove(i);
                        }

                }
            }
        });
    }

    private void getServiceItem(int _offset, int _limit) {
        ApiController.getInstance().getServiceItem(getActivity(), _offset, _limit,
                new ACallback<ServiceItemDomain>() {
                    @Override
                    public void response(List<ServiceItemDomain> listServiceItem) {
                        lstAuto = listServiceItem;
                        adapterAuto.setItems(lstAuto);
                        adapterAuto.notifyDataSetChanged();

                        /*Get Price*/
                        //getMapPriceServiceItem(offset, limit, 1);

                        //Add to List
                        updateListService(listServiceItem);

                    }
                });
    }

    private void getMapPriceServiceItem(int _offset, int _limit, int _idPrice) {
        ApiController.getInstance().getMapPriceServiceItem(getActivity(), _offset, _limit, _idPrice,
                new ACallback<MapPriceServiceItemLDomain>() {
                    @Override
                    public void response(List<MapPriceServiceItemLDomain> list) {
                        if (lstAuto != null)
                            for (ServiceItemDomain item : lstAuto)
                                for (MapPriceServiceItemLDomain price : list)
                                    if (item.getId() == price.getIdservice()) {
                                        item.setPrice(price.getPrice());
                                        item.setPricehi(price.getPricehi());
                                    }


                        int i = 0;
                    }
                });
    }

    private void updateListService(List<ServiceItemDomain> listServiceItem) {
        if (Instruction.happeningDomain != null) {
            lstInpatientServiceOrder = Instruction.happeningDomain.getLstInpatientServiceOrder();

            ServiceItemDomain serviceItemDomain = null;

            if (lstInpatientServiceOrder != null && lstInpatientServiceOrder.size() > 0 && listServiceItem != null && listServiceItem.size() > 0) {
                for (InpatientServiceOrder service : lstInpatientServiceOrder) {
                    for (ServiceItemDomain serviceItem : listServiceItem) {
                        if (service.getIdservice() == serviceItem.getId()) {
                            try {
                                serviceItemDomain = (ServiceItemDomain) serviceItem.clone();

                                serviceItemDomain.setDocoder(service.getDocoder());
                                serviceItemDomain.setPrice(service.getPrice());
                                serviceItemDomain.setPricehi(service.getPricehi());
                                serviceItemDomain.setQty(service.getQty());
                                serviceItemDomain.setDateapp(service.getDateapp());

                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                            if (serviceItemDomain != null) lstServiceItem.add(serviceItemDomain);
                            break;
                        }
                    }
                }

                adapterServiceItem.setItems(lstServiceItem);
                adapterServiceItem.notifyDataSetChanged();

            }

        }
    }

}
