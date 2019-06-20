package ptt.vn.icaremobileapp.fragment.history;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.activity.MainActivity;
import ptt.vn.icaremobileapp.adapter.HistoryClinicDateAdapter;
import ptt.vn.icaremobileapp.adapter.HistoryClinicGroupAdapter;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.model.history.HistoryClinicDomain;
import ptt.vn.icaremobileapp.model.history.HistoryClinicHeaderDomain;


public class HistoryClinic extends BaseFragment {
    private View view;
    private RecyclerView rcv;
    List<HistoryClinicDomain> lstHistoryRegister = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history_clinic, container, false);


        toolbarListener();

        rcv = view.findViewById(R.id.rcvParent);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));


        getHistoryClinic("9976b9d6-ee9d-4c83-9d03-348fd0bb28c4");

        return view;
    }

    private void onGroupViewType(List<HistoryClinicDomain> lstHistoryRegister) {
        List<HistoryClinicHeaderDomain> lst = new ArrayList<>();

        for (String s : getResources().getStringArray(R.array.history_clinic_group)) {
            HistoryClinicHeaderDomain historyClinicHeaderDomain = new HistoryClinicHeaderDomain();
            historyClinicHeaderDomain.setTitle(s);
            historyClinicHeaderDomain.setLstChild(lstHistoryRegister);
            lst.add(historyClinicHeaderDomain);
        }

        HistoryClinicGroupAdapter dynamicViewAdapter = new HistoryClinicGroupAdapter(lst);
        rcv.setAdapter(dynamicViewAdapter);

    }

    private void onDateViewType(List<HistoryClinicDomain> lstHistoryRegister) {

        HistoryClinicDateAdapter adapter = new HistoryClinicDateAdapter(lstHistoryRegister);
        rcv.setAdapter(adapter);
    }

    private void getHistoryClinic(String patid) {
        if (getActivity() != null)
            ApiController.getInstance().getHistoryClinic(getActivity(), patid, new ACallback<HistoryClinicDomain>() {
                @Override
                public void response(List<HistoryClinicDomain> list) {
                    lstHistoryRegister = list;
                    onDateViewType(lstHistoryRegister);
                }
            });
    }

    @Override
    public void toolbarListener() {
        if (getActivity() != null)
            ((MainActivity) getActivity()).toolbarClick(new MainActivity.OnToolbarListener() {
                @Override
                public void left(String frgName) {

                }

                @Override
                public void right(View v) {
                    showPopupMenu(v);
                }
            });
    }

    @SuppressLint("RestrictedApi")
    private void showPopupMenu(View v) {
        if (getActivity() != null) {
            PopupMenu menu = new PopupMenu(getActivity(), v);
            menu.inflate(R.menu.popup_menu);
            MenuPopupHelper menuHelper = new MenuPopupHelper(getActivity(), (MenuBuilder) menu.getMenu(), v);
            menuHelper.setForceShowIcon(true);
            menuHelper.show();

            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.date_filter)
                        onDateViewType(lstHistoryRegister);
                    if (menuItem.getItemId() == R.id.group_filter)
                        onGroupViewType(lstHistoryRegister);
                    return false;
                }
            });
        }
    }

}
