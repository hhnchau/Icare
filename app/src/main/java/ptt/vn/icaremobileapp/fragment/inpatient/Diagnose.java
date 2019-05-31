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

import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.DiagnoseAdapter;
import ptt.vn.icaremobileapp.alert.MyAlert;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.utils.Constant;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewDiagnoseAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.model.icd.IcdDomain;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDiagnose;
import ptt.vn.icaremobileapp.utils.Utils;

public class Diagnose extends BaseFragment {
    private View view;

    private List<InpatientDiagnose> lstDiagnose;
    private DiagnoseAdapter adapterDiagnose;
    private int offset = 0;
    private int limit = 1000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.diagnose, container, false);

        setupListDiagnose();

        getIcd(offset, limit);


        return view;
    }

    private void setupDiagnose(List<IcdDomain> lstAuto) {
        if (getActivity() != null) {
            final MyAutoCompleteTextView myAutoCompleteTextView = view.findViewById(R.id.acDiagnose);
            AutoCompleteTextViewDiagnoseAdapter adapterAuto = new AutoCompleteTextViewDiagnoseAdapter(getActivity(), lstAuto);
            myAutoCompleteTextView.setAdapter(adapterAuto);
            myAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), myAutoCompleteTextView);
                    myAutoCompleteTextView.setValue("");
                    IcdDomain icd = (IcdDomain) parent.getItemAtPosition(position);

                    boolean exist = false;
                    for (InpatientDiagnose item : lstDiagnose)
                        if (item.getIdicd() == icd.getId()) {
                            exist = true;
                            break;
                        }

                    if (!exist) {
                        InpatientDiagnose diagnose = new InpatientDiagnose();
                        diagnose.setActive(Constant.ACTIVE);
                        diagnose.setIdline(Utils.newGuid());
                        diagnose.setIdicd(icd.getId());
                        diagnose.setNameicdvn(icd.getName());
                        diagnose.setNameicdeng(icd.getNameen());
                        diagnose.setCode(icd.getCode());

                        lstDiagnose.add(diagnose);
                        adapterDiagnose.setItems(lstDiagnose);
                        adapterDiagnose.notifyDataSetChanged();
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
            public void onClick(final int p) {
                if (getActivity() != null) {
                    MyAlert.getInstance().show(getActivity(), getString(R.string.txt_delete_happening), getString(R.string.btn_delete), MyAlert.REB, getString(R.string.btn_cancel), MyAlert.WHITE, false, new MyAlert.OnAlertClickListener() {
                        @Override
                        public void onYes() {
                            /*
                             * DELETE
                             **/
                            InpatientDiagnose inpatientDiagnose = lstDiagnose.get(p);
                            inpatientDiagnose.setActive(Constant.DELETE);
                            if (Instruction.happeningDomain != null)
                                deleteDiagnose(Instruction.happeningDomain, p);
                        }

                        @Override
                        public void onNo() {

                        }
                    });
                }
            }
        });
    }

    private void getIcd(int _offset, int _limit) {
        ApiController.getInstance().getIcd(getActivity(), _offset, _limit,
                new ACallback<IcdDomain>() {
                    @Override
                    public void response(final List<IcdDomain> listIcd) {
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                public void run() {
                                    setupDiagnose(listIcd);
                                    syncIcd(listIcd);
                                }
                            });
                        }
                    }
                });
    }

    private void syncIcd(List<IcdDomain> lstIcd) {
        if (lstDiagnose != null) {
            for (InpatientDiagnose diagnose : lstDiagnose)
                for (IcdDomain icd : lstIcd)
                    if (diagnose.getIdicd() == icd.getId()) {
                        diagnose.setCode(icd.getCode());
                        break;
                    }


            adapterDiagnose.notifyDataSetChanged();
        }
    }

    public void deleteDiagnose(HappeningDomain happening, final int p) {
        ApiController.getInstance().saveHappening(getActivity(), happening, new Callback<HappeningDomain>() {
            @Override
            public void response(HappeningDomain happening) {
                adapterDiagnose.removeItem(p);
                Toast.makeText(getActivity(), getString(R.string.txt_delete_success), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void toolbarListener() {

    }
}
