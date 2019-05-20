package ptt.vn.icaremobileapp.fragment.receiving;

import android.app.DatePickerDialog;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.filter.FieldName;
import ptt.vn.icaremobileapp.model.hi.HiDomain;
import ptt.vn.icaremobileapp.model.shared.HiLiveData;
import ptt.vn.icaremobileapp.utils.Utils;

import static ptt.vn.icaremobileapp.model.filter.FieldName.district;
import static ptt.vn.icaremobileapp.model.filter.FieldName.ethnic;
import static ptt.vn.icaremobileapp.model.filter.FieldName.job;
import static ptt.vn.icaremobileapp.model.filter.FieldName.marr;
import static ptt.vn.icaremobileapp.model.filter.FieldName.nation;
import static ptt.vn.icaremobileapp.model.filter.FieldName.provincial;
import static ptt.vn.icaremobileapp.model.filter.FieldName.ward;

public class ReceivingInfo extends BaseFragment {
    private View view;

    private MyInputTextOutline edtPatientCode, edtPatientSex, edtPatientPassport, edtParentName,
            edtPatientName, edtPatientPhone, edtPatientStreet, edtPatientIde, edtPatientEmail, edtParentIde;
    private MyAutoCompleteTextView acpPatientDistrict, acpPatientProvince, acpPatientWard, acpPatientJob, acpPatientEthnic, acpPatientNation, acpPatientMarried;
    private int idPatientDistrict, idPatientProvince, idPatientWard, idPatientJob, idPatientEthnic, idPatientNation, idPatientMarried;
    private EditText edtDatetime;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.receiving_info, container, false);
        initView();

        getCateShare(district);
        getCateShare(ward);
        getCateShare(job);
        getCateShare(provincial);
        getCateShare(nation);
        getCateShare(marr);
        getCateShare(ethnic);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            HiLiveData hiLiveData = ViewModelProviders.of(getActivity()).get(HiLiveData.class);
            hiLiveData.getHiLiveData().observe(getActivity(), new Observer<HiDomain>() {
                @Override
                public void onChanged(@Nullable HiDomain hiDomain) {
                    updateView(hiDomain);
                }
            });
        }
    }

    private void updateView(HiDomain hiDomain) {
        if (hiDomain != null) {
            if (!TextUtils.isEmpty(hiDomain.getHoTen()))
                edtPatientName.setText(hiDomain.getHoTen());
            if (!TextUtils.isEmpty(hiDomain.getDiaChi()))
                edtPatientStreet.setText(hiDomain.getDiaChi());
        }
    }

    private void initView() {
        edtPatientCode = view.findViewById(R.id.edtPatientCode);
        edtPatientSex = view.findViewById(R.id.edtPatientSex);
        edtPatientPassport = view.findViewById(R.id.edtPatientPassport);
        edtParentName = view.findViewById(R.id.edtParentName);
        edtPatientName = view.findViewById(R.id.edtPatientName);
        edtPatientPhone = view.findViewById(R.id.edtPatientPhone);
        edtPatientStreet = view.findViewById(R.id.edtPatientStreet);
        edtPatientIde = view.findViewById(R.id.edtPatientIde);
        edtPatientEmail = view.findViewById(R.id.edtPatientEmail);
        edtParentIde = view.findViewById(R.id.edtParentIde);

        edtDatetime = view.findViewById(R.id.edtDateTime);
        view.findViewById(R.id.icDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar _calendar = Utils.dateStringConvert(edtDatetime.getText().toString(), Utils.ddMMyyyy);
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtDatetime.setText((dayOfMonth + "/" + month + "/" + year));
                    }
                }, _calendar.get(Calendar.YEAR), _calendar.get(Calendar.MONTH), _calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void setupDistrict(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpPatientDistrict = view.findViewById(R.id.acpPatientDistrict);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpPatientDistrict.setAdapter(adapter);
            acpPatientDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), acpPatientDistrict);
                    idPatientDistrict = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPatientDistrict.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPatientDistrict.showDropDown();
                }
            });
        }
    }

    private void setupProvince(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpPatientProvince = view.findViewById(R.id.acpPatientProvince);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpPatientProvince.setAdapter(adapter);
            acpPatientProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), acpPatientProvince);
                    idPatientProvince = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPatientProvince.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPatientProvince.showDropDown();
                }
            });
        }
    }

    private void setupWard(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpPatientWard = view.findViewById(R.id.acpPatientWard);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpPatientWard.setAdapter(adapter);
            acpPatientWard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), acpPatientWard);
                    idPatientWard = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPatientWard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPatientWard.showDropDown();
                }
            });
        }
    }

    private void setupJob(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpPatientJob = view.findViewById(R.id.acpPatientJob);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpPatientJob.setAdapter(adapter);
            acpPatientJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), acpPatientJob);
                    idPatientJob = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPatientJob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPatientJob.showDropDown();
                }
            });
        }
    }

    private void setupEthnic(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpPatientEthnic = view.findViewById(R.id.acpPatientEthnic);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpPatientEthnic.setAdapter(adapter);
            acpPatientEthnic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), acpPatientEthnic);
                    idPatientEthnic = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPatientEthnic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPatientEthnic.showDropDown();
                }
            });
        }
    }

    private void setupNation(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpPatientNation = view.findViewById(R.id.acpPatientNation);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpPatientNation.setAdapter(adapter);
            acpPatientNation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), acpPatientNation);
                    idPatientNation = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPatientNation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPatientNation.showDropDown();
                }
            });
        }
    }

    private void setupMarried(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpPatientMarried = view.findViewById(R.id.acpPatientMarried);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpPatientMarried.setAdapter(adapter);
            acpPatientMarried.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), acpPatientMarried);
                    idPatientMarried = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPatientMarried.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPatientMarried.showDropDown();
                }
            });
        }
    }

    public boolean validate() {
        Receiving.patientDomain.setFirstname("Huynh");
        Receiving.patientDomain.setLastname("Demo-Demo");
        Receiving.patientDomain.setSex(1);
        Receiving.patientDomain.setYearbr(1980);
        Receiving.patientDomain.setMonthbr(1);
        Receiving.patientDomain.setDaybr(1);
        Receiving.patientDomain.setPhone("0901010101");
        Receiving.patientDomain.setEmail("abc@gmail.com");
        Receiving.patientDomain.setIdnation(25124);
        Receiving.patientDomain.setIdethnic(25069);
        Receiving.patientDomain.setIdjob(25378);
        Receiving.patientDomain.setIsmarr(25385);
        Receiving.patientDomain.setFaname("Ten Ba Me");
        Receiving.patientDomain.setFacard("CMND BA ME");
        return true;
    }

    private void getCateShare(final FieldName fieldName) {
        ApiController.getInstance().getCateShare(getActivity(), fieldName,
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(List<CateSharelDomain> list) {
                        switch (fieldName) {
                            case district:
                                setupDistrict(list);
                                break;
                            case ward:
                                setupWard(list);
                                break;
                            case job:
                                setupJob(list);
                                break;
                            case provincial:
                                setupProvince(list);
                                break;
                            case nation:
                                setupNation(list);
                                break;
                            case ethnic:
                                setupEthnic(list);
                                break;
                            case marr:
                                setupMarried(list);
                                break;
                        }
                    }
                });
    }

    @Override
    public void toolbarListener() {

    }
}
