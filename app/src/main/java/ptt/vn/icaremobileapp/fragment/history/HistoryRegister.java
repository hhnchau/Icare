package ptt.vn.icaremobileapp.fragment.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.HistoryRegisterAdapter;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.model.history.HistoryRegisterDomain;
import ptt.vn.icaremobileapp.model.history.HistoryRegisterServiceOrderDomain;
import ptt.vn.icaremobileapp.model.register.RegisterDomain;

public class HistoryRegister extends BaseFragment {
    private View view;
    private RecyclerView rcv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history_register, container, false);
        initView();



        getHistoryRegister("1024c99e-87b0-4cd8-8d1a-2e29bb0106f9");


        return view;
    }


    private void initView(){
        rcv = view.findViewById(R.id.rcvParent);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void getHistoryRegister(String patid) {
        if (getActivity() != null)
            ApiController.getInstance().getHistoryRegister(getActivity(), patid, new Callback<RegisterDomain>() {
                @Override
                public void response(RegisterDomain registerDomain) {
                    List<HistoryRegisterDomain> lstHistoryRegister = registerDomain.getLstHistoryRegisters();

                    HistoryRegisterAdapter adapter = new HistoryRegisterAdapter(lstHistoryRegister);
                    rcv.setAdapter(adapter);

                    List<HistoryRegisterServiceOrderDomain> lstHistoryRegisterServiceOrder = registerDomain.getLstHistoryRegServiceOrders();
                    for (HistoryRegisterDomain parent : lstHistoryRegister) {
                        List<HistoryRegisterServiceOrderDomain> lst = new ArrayList<>();
                        for (HistoryRegisterServiceOrderDomain child : lstHistoryRegisterServiceOrder)
                            if (parent.getIdlink().equals(child.getIdlink()))
                                lst.add(child);

                        parent.setLstChild(lst);
                    }

                    adapter.notifyDataSetChanged();
                }
            });
    }

    @Override
    public void toolbarListener() {

    }
}
