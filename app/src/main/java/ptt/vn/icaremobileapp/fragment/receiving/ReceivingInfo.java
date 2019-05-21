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

import java.util.ArrayList;
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
import ptt.vn.icaremobileapp.model.patient.PatientAdrr;
import ptt.vn.icaremobileapp.model.patient.PatientIde;
import ptt.vn.icaremobileapp.model.shared.HiLiveData;
import ptt.vn.icaremobileapp.utils.Helper;
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

    private MyInputTextOutline edtPatientCode, edtPatientPassport, edtParentName,
            edtPatientName, edtPatientPhone, edtPatientStreet, edtPatientIde, edtPatientEmail, edtParentIde;
    private MyAutoCompleteTextView acpPatientDistrict, acpPatientProvince, acpPatientWard, acpPatientJob, acpPatientEthnic, acpPatientNation, acpPatientMarried, acpPatientSex;
    private List<CateSharelDomain> lstPatientDistrict, lstPatientProvince, lstPatientWard, lstPatientJob, lstPatientEthnic, lstPatientNation, lstPatientMarried, lstPatientSex;
    private int idPatientDistrict, idPatientProvince, idPatientWard, idPatientJob, idPatientEthnic, idPatientNation, idPatientMarried, idPatientSex;
    private EditText edtPatientBirthday;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.receiving_info, container, false);
        initView();
        setupSex();
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
            if (!TextUtils.isEmpty(hiDomain.getGioiTinh())) {
                acpPatientSex.setText(hiDomain.getGioiTinh());
                idPatientSex = Helper.getIdByName(lstPatientSex, hiDomain.getGioiTinh());
            }
            if (!TextUtils.isEmpty(hiDomain.getNgaySinh()))
                edtPatientBirthday.setText(hiDomain.getNgaySinh());
        }
    }

    private void initView() {
        edtPatientCode = view.findViewById(R.id.edtPatientCode);
        edtPatientPassport = view.findViewById(R.id.edtPatientPassport);
        edtParentName = view.findViewById(R.id.edtParentName);
        edtPatientName = view.findViewById(R.id.edtPatientName);
        edtPatientPhone = view.findViewById(R.id.edtPatientPhone);
        edtPatientStreet = view.findViewById(R.id.edtPatientStreet);
        edtPatientIde = view.findViewById(R.id.edtPatientIde);
        edtPatientEmail = view.findViewById(R.id.edtPatientEmail);
        edtParentIde = view.findViewById(R.id.edtParentIde);

        edtPatientBirthday = view.findViewById(R.id.edtPatientBirthday);
        view.findViewById(R.id.icDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar _calendar = Utils.dateStringConvert(edtPatientBirthday.getText().toString(), Utils.ddMMyyyy);
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtPatientBirthday.setText((dayOfMonth + "/" + month + "/" + year));
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

    private void setupSex() {
        if (getActivity() != null) {
            acpPatientSex = view.findViewById(R.id.acpPatientSex);

            lstPatientSex = new ArrayList<>();
            lstPatientSex.add(new CateSharelDomain(1, "Nam"));
            lstPatientSex.add(new CateSharelDomain(0, "Nữ"));

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lstPatientSex);
            acpPatientSex.setAdapter(adapter);
            acpPatientSex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), acpPatientSex);
                    idPatientSex = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPatientSex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPatientSex.showDropDown();
                }
            });
        }
    }

    public boolean validate() {
        boolean validate = true;
        if (TextUtils.isEmpty(edtPatientName.getText())) {
            edtPatientName.setError(".");
            validate = false;
        }
        if (TextUtils.isEmpty(acpPatientSex.getText())) {
            acpPatientSex.setError(".");
            validate = false;
        }
        if (TextUtils.isEmpty(edtPatientBirthday.getText())) {
            edtPatientBirthday.setError(".");
            validate = false;
        }
        if (TextUtils.isEmpty(edtPatientBirthday.getText())) {
            edtPatientBirthday.setError(".");
            validate = false;
        }
        if (!validate) return false;

        String[] name = Helper.splitName(edtPatientName.getText().toString());
        Receiving.patientDomain.setFirstname(name[0]);
        Receiving.patientDomain.setLastname(name[1]);

        Receiving.patientDomain.setSex(idPatientSex);

        String[] date = Helper.splitDate(edtPatientBirthday.getText().toString());
        if (!TextUtils.isEmpty(date[0]))
            Receiving.patientDomain.setDaybr(Integer.parseInt(date[0]));
        else
            Receiving.patientDomain.setDaybr(null);
        if (!TextUtils.isEmpty(date[1]))
            Receiving.patientDomain.setMonthbr(Integer.parseInt(date[1]));
        else
            Receiving.patientDomain.setMonthbr(null);
        Receiving.patientDomain.setYearbr(Integer.parseInt(date[2]));

        Receiving.patientDomain.setPhone(edtPatientPhone.getText().toString());
        Receiving.patientDomain.setEmail(edtPatientEmail.getText().toString());
        Receiving.patientDomain.setIdnation(idPatientNation);
        Receiving.patientDomain.setIdethnic(idPatientEthnic);
        Receiving.patientDomain.setIdjob(idPatientJob);
        Receiving.patientDomain.setIsmarr(idPatientMarried);
        Receiving.patientDomain.setFaname(edtParentName.getText().toString());
        Receiving.patientDomain.setFacard(edtParentIde.getText().toString());

        PatientAdrr patientAdrr = new PatientAdrr();
        String address =
                edtPatientStreet.getText() + " " +
                        acpPatientWard.getText() + " " +
                        acpPatientDistrict.getText() + " " +
                        acpPatientProvince.getText();
        patientAdrr.setAddresfull(address);
        patientAdrr.setIddistric(idPatientDistrict);
        patientAdrr.setIdprovin(idPatientProvince);
        patientAdrr.setIdward(idPatientWard);
        patientAdrr.setStreet(edtPatientStreet.getText().toString());
        List<PatientAdrr> lstPatientAdrr = new ArrayList<>();
        lstPatientAdrr.add(patientAdrr);
        Receiving.patientDomain.setLstPatientAddr(lstPatientAdrr);

        PatientIde patientIde = new PatientIde();
        patientIde.setCardid(edtPatientIde.getText().toString());
        List<PatientIde> lstPatientIde = new ArrayList<>();
        lstPatientIde.add(patientIde);
        Receiving.patientDomain.setLstPatientIde(lstPatientIde);
        return true;
    }

    private void getCateShare(final FieldName fieldName) {
        ApiController.getInstance().getCateShare(getActivity(), fieldName,
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(List<CateSharelDomain> list) {
                        switch (fieldName) {
                            case district:
                                lstPatientDistrict = list;
                                setupDistrict(lstPatientDistrict);
                                break;
                            case ward:
                                lstPatientWard = list;
                                setupWard(lstPatientWard);
                                break;
                            case job:
                                lstPatientJob = list;
                                setupJob(lstPatientJob);
                                break;
                            case provincial:
                                lstPatientProvince = list;
                                setupProvince(lstPatientProvince);
                                break;
                            case nation:
                                lstPatientNation = list;
                                setupNation(lstPatientNation);
                                break;
                            case ethnic:
                                lstPatientEthnic = list;
                                setupEthnic(lstPatientEthnic);
                                break;
                            case marr:
                                lstPatientMarried = list;
                                setupMarried(lstPatientMarried);
                                break;
                        }
                    }
                });
    }


    @Override
    public void toolbarListener() {

    }
}
