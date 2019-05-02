package ptt.vn.icaremobileapp.fragment.inpatient;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.adapter.DiagnoseAdapter;
import ptt.vn.icaremobileapp.alert.Alert;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewAdapter;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewDiagnoseAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.icd.IcdDomain;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDiagnose;
import ptt.vn.icaremobileapp.utils.Constant;
import ptt.vn.icaremobileapp.utils.Utils;

import static ptt.vn.icaremobileapp.model.filter.FieldName.managstatus;
import static ptt.vn.icaremobileapp.model.filter.FieldName.results;

public class Resolved extends Fragment {
    private View view;
    private List<InpatientDiagnose> lstDiagnose;
    private DiagnoseAdapter adapterDiagnose;
    private int offset = 0;
    private int limit = 1000;
    private MyAutoCompleteTextView acpResult, acpStatus, acpAdvice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.resolved, container, false);
        setupListDiagnose();
        getIcd(offset, limit);
        setupDateDischarged();
        getStatus();
        getResult();
        getAdvice();

        MyButton btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnSelectedListener(new MyButton.OnListener() {
            @Override
            public void onClick() {
                Toast.makeText(getActivity(), acpAdvice.getValue(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
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
                                }
                            });
                        }
                    }
                });
    }

    private void setupDiagnose(List<IcdDomain> lstAuto) {
        if (getActivity() != null) {
            final MyAutoCompleteTextView myAutoCompleteTextView = view.findViewById(R.id.acpDiagnose);
            AutoCompleteTextViewDiagnoseAdapter adapterAuto = new AutoCompleteTextViewDiagnoseAdapter(getActivity(), lstAuto);
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
                        diagnose.setActive(Constant.ACTIVE);
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

    private void getStatus() {
        ApiController.getInstance().getCateShare(getActivity(), managstatus, new ACallback<CateSharelDomain>() {
            @Override
            public void response(List<CateSharelDomain> listStatus) {
                setupStatus(listStatus);
            }
        });
    }

    private void setupStatus(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpStatus = view.findViewById(R.id.acpStatus);
            AutoCompleteTextViewAdapter adapterAuto = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpStatus.setAdapter(adapterAuto);

            acpStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpStatus.showDropDown();
                }
            });
        }

    }

    private void getResult() {
        ApiController.getInstance().getCateShare(getActivity(), results, new ACallback<CateSharelDomain>() {
            @Override
            public void response(List<CateSharelDomain> listResult) {
                setupResult(listResult);
            }
        });
    }

    private void setupResult(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpResult = view.findViewById(R.id.acpResult);
            AutoCompleteTextViewAdapter adapterAuto = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpResult.setAdapter(adapterAuto);

            acpResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpResult.showDropDown();
                }
            });
        }

    }

    private void getAdvice() {
        ApiController.getInstance().getCateShare(getActivity(), results, new ACallback<CateSharelDomain>() {
            @Override
            public void response(List<CateSharelDomain> listAdvice) {
                setupAdvice(listAdvice);
            }
        });
    }

    private void setupAdvice(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpAdvice = view.findViewById(R.id.acpAdvice);
            AutoCompleteTextViewAdapter adapterAuto = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpAdvice.setAdapter(adapterAuto);

            acpAdvice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpAdvice.showDropDown();
                }
            });
        }

    }

    private void setupListDiagnose() {
        RecyclerView rcv = view.findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rcv.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        lstDiagnose = new ArrayList<>();

        adapterDiagnose = new DiagnoseAdapter(lstDiagnose);
        rcv.setAdapter(adapterDiagnose);

        adapterDiagnose.setOnItemClick(new DiagnoseAdapter.OnItemClick() {
            @Override
            public void onClick(final int p) {
                if (getActivity() != null) {
                    Alert.getInstance().show(getActivity(), getString(R.string.txt_delete_happening), getString(R.string.btn_delete), Alert.REB, getString(R.string.btn_cancel), Alert.WHITE, false, new Alert.OnAlertClickListener() {
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

    private void setupDateDischarged() {
        final EditText edtDatetime = view.findViewById(R.id.edtDateTime);
        ImageView icDatetime = view.findViewById(R.id.icDate);
        icDatetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar _calendar = Utils.dateStringConvert(edtDatetime.getText().toString(), Utils.ddMMyyyy);
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar cal = Calendar.getInstance();
                        edtDatetime.setText(dayOfMonth + "/" + month + "/" + year + " " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
                    }
                }, _calendar.get(Calendar.YEAR), _calendar.get(Calendar.MONTH), _calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtDatetime.setText(Utils.getCurrentDate(Utils.ddMMyyyyHHmm));

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
}
