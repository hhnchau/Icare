package ptt.vn.icaremobileapp.fragment;

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
import ptt.vn.icaremobileapp.adapter.ServiceItemAdapter;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewServiceItemAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.model.inpatient.InpatientServiceOrder;
import ptt.vn.icaremobileapp.model.pharmacy.PhaInventoryDomain;
import ptt.vn.icaremobileapp.model.serviceitem.ServiceItemDomain;

public class ServiceItem extends BaseFragment {
    private View view;
    private List<ServiceItemDomain> lstAuto;
    private AutoCompleteTextViewServiceItemAdapter adapterAuto;
    private List<InpatientServiceOrder> lstServiceItem;
    private ServiceItemAdapter adapterServiceItem;
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
                    InpatientServiceOrder serviceOrder = new InpatientServiceOrder();
                    serviceOrder.setDocoder(serviceItemDomain.getNamehosp());

                    int exist = 0;
                    for (InpatientServiceOrder item : lstServiceItem)
                        if (item.getDocoder().equals(serviceOrder.getDocoder())) {
                            exist++;
                            break;
                        }

                    if (exist == 0) {
                        lstServiceItem.add(serviceOrder);
                        adapterServiceItem.setItems(lstServiceItem);
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
        lstServiceItem = new ArrayList<>();
        adapterServiceItem = new ServiceItemAdapter(lstServiceItem);
        rcv.setAdapter(adapterServiceItem);
        adapterServiceItem.setOnItemClick(new ServiceItemAdapter.OnItemClick() {
            @Override
            public void onClick(int p) {
                if (getActivity() != null) {
                    adapterServiceItem.removeItem(p);
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
                    }
                });
    }

    private void getPhaInventory(int _offset, int _limit, int _idStore, int _isHi) {
        ApiController.getInstance().getPhaInventory(getActivity(), _offset, _limit, _idStore, _isHi,
                new ACallback<PhaInventoryDomain>() {
                    @Override
                    public void response(List<PhaInventoryDomain> listInventory) {
                        int i = 0;
                    }
                });
    }
}
