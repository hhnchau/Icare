package ptt.vn.icaremobileapp.fragment.search;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.PatientListAdapter;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.model.filter.FieldName;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.utils.Constant;

public class PatientList extends BaseFragment {
    private View view;
    private PatientListAdapter adapter;
    private long clickTime = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.patient_list, container, false);
        initView();
        setupList();
        return view;
    }

    private void initView() {
        final MyInputTextOutline edtPatientName = view.findViewById(R.id.edtPatientName);
        final MyInputTextOutline edtPatientPhone = view.findViewById(R.id.edtPatientPhone);
        final MyInputTextOutline edtPatientBirthday = view.findViewById(R.id.edtPatientBirthday);
        final MyInputTextOutline edtPatientAddr = view.findViewById(R.id.edtPatientAddr);
        final MyInputTextOutline edtPatientIde = view.findViewById(R.id.edtPatientIde);
        final MyInputTextOutline edtPatientHi = view.findViewById(R.id.edtPatientHi);
        MyButton btnFind = view.findViewById(R.id.btnFind);
        btnFind.setOnSelectedListener(new MyButton.OnListener() {
            @Override
            public void onClick() {
                if (SystemClock.elapsedRealtime() - clickTime < 1000) {
                    return;
                }
                clickTime = SystemClock.elapsedRealtime();

                if (TextUtils.isEmpty(edtPatientName.getText()) &&
                        TextUtils.isEmpty(edtPatientPhone.getText()) &&
                        TextUtils.isEmpty(edtPatientBirthday.getText()) &&
                        TextUtils.isEmpty(edtPatientAddr.getText()) &&
                        TextUtils.isEmpty(edtPatientIde.getText()) &&
                        TextUtils.isEmpty(edtPatientHi.getText())
                        ) {
                    Toast.makeText(getActivity(), getString(R.string.txt_fill_blank), Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, Object> map = new HashMap<>();
                map.put(FieldName.fullname.name(), TextUtils.isEmpty(edtPatientName.getText().toString()) ? null : edtPatientName.getText().toString());
                map.put(FieldName.phone.name(), TextUtils.isEmpty(edtPatientPhone.getText().toString()) ? null : edtPatientPhone.getText().toString());
                map.put(FieldName.yearbr.name(), TextUtils.isEmpty(edtPatientBirthday.getText().toString()) ? null : edtPatientBirthday.getText().toString());
                map.put(FieldName.addresfull.name(), TextUtils.isEmpty(edtPatientAddr.getText().toString()) ? null : edtPatientAddr.getText().toString());
                map.put(FieldName.nohi.name(), TextUtils.isEmpty(edtPatientIde.getText().toString()) ? null : edtPatientIde.getText().toString());
                map.put(FieldName.cardid.name(), TextUtils.isEmpty(edtPatientHi.getText().toString()) ? null : edtPatientHi.getText().toString());

                getPatientListByFilter(map);
            }
        });
    }

    private void setupList() {
        RecyclerView rcv = view.findViewById(R.id.rcvPatientList);
        rcv.setHasFixedSize(true);
        //rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new PatientListAdapter(new ArrayList<PatientDomain>());
        rcv.setAdapter(adapter);
        adapter.setOnItemClick(new PatientListAdapter.OnItemClick() {
            @Override
            public void onClick(PatientDomain patientDomain) {

            }
        });
    }

    @Override
    public void toolbarListener() {

    }

    private void getPatientListByFilter(Map<String, Object> map) {
        ApiController.getInstance().getPatientByFilter(getActivity(), map, new ACallback<PatientDomain>() {
            @Override
            public void response(List<PatientDomain> list) {
                adapter.setItems(list);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
