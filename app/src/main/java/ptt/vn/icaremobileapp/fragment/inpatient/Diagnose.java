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
import ptt.vn.icaremobileapp.adapter.DiagnoseAdapter;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Host;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewDiagnoseAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.model.icd.IcdDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDiagnose;
import ptt.vn.icaremobileapp.utils.Utils;

public class Diagnose extends BaseFragment {
    private View view;
    private List<IcdDomain> lstAuto;
    private AutoCompleteTextViewDiagnoseAdapter adapterAuto;
    private List<InpatientDiagnose> lstDiagnose;
    private DiagnoseAdapter adapterDiagnose;
    private int offset = 0;
    private int limit = 1000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diagnose, container, false);

        setupDiagnose();
        setupListDiagnose();

        getIcd(offset, limit);


        return view;
    }

    private void setupDiagnose() {
        if (getActivity() != null) {
            final MyAutoCompleteTextView myAutoCompleteTextView = view.findViewById(R.id.acDiagnose);

            lstAuto = new ArrayList<>();
            adapterAuto = new AutoCompleteTextViewDiagnoseAdapter(getActivity(), lstAuto);
            myAutoCompleteTextView.setAdapter(adapterAuto);
            myAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    IcdDomain icd = (IcdDomain) parent.getItemAtPosition(position);

                    boolean exist = false;
                    for (InpatientDiagnose item : lstDiagnose)
                        if (item.getIdicd() == icd.getId()) {
                            exist = true;
                            break;
                        }

                    if (!exist) {
                        InpatientDiagnose diagnose = new InpatientDiagnose();
                        diagnose.setActive(Host.ACTIVE);
                        diagnose.setIdline(Utils.newGuid());
                        diagnose.setIdicd(icd.getId());
                        diagnose.setNameicdvn(icd.getName());
                        diagnose.setNameicdeng(icd.getNameen());
                        diagnose.setCode(icd.getCode());

                        lstDiagnose.add(diagnose);
                        adapterDiagnose.setItems(lstDiagnose);
                        adapterDiagnose.notifyDataSetChanged();
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

    private void setupListDiagnose() {
        RecyclerView rcv = view.findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        if (Instruction.happeningDomain != null)
            lstDiagnose = Instruction.happeningDomain.getLstInpatientDiagnose();

        adapterDiagnose = new DiagnoseAdapter(lstDiagnose);
        rcv.setAdapter(adapterDiagnose);

        adapterDiagnose.setOnItemClick(new DiagnoseAdapter.OnItemClick() {
            @Override
            public void onClick(int p) {

            }
        });
    }

    private void getIcd(int _offset, int _limit) {
        ApiController.getInstance().getIcd(getActivity(), _offset, _limit,
                new ACallback<IcdDomain>() {
                    @Override
                    public void response(List<IcdDomain> listIcd) {
                        lstAuto = listIcd;
                        adapterAuto.setItems(lstAuto);
                        adapterAuto.notifyDataSetChanged();
                    }
                });
    }
}
