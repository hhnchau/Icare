package ptt.vn.icaremobileapp.fragment.receiving;

import android.app.DatePickerDialog;
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


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ptt.vn.icaremobileapp.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.custom.MyInputText;
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.custom.MyInputTextOutlineMultiLine;
import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.filter.FieldName;
import ptt.vn.icaremobileapp.model.hi.HiDomain;
import ptt.vn.icaremobileapp.model.patient.PatientHi;
import ptt.vn.icaremobileapp.model.shared.HiLiveData;
import ptt.vn.icaremobileapp.utils.Helper;
import ptt.vn.icaremobileapp.utils.Utils;

import static ptt.vn.icaremobileapp.model.filter.FieldName.hospital;
import static ptt.vn.icaremobileapp.model.filter.FieldName.livpla;


public class ReceivingHi extends BaseFragment {
    private View view;

    private MyInputTextOutline edtHiCode;
    private MyInputTextOutlineMultiLine edtHiAddress;
    private MyAutoCompleteTextView acpHospitalName, acpHiObject;
    private int idHospitalName, idHiObject;
    private List<CateSharelDomain> lstHospitalName, lstHiObject;
    private EditText edtHiStart, edtHiEnd, edtHi5y;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.receiving_hi, container, false);
        initView();

        getCateShare(hospital);
        getCateShare(livpla);


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
            if (!TextUtils.isEmpty(hiDomain.getMaThe()))
                edtHiCode.setText(hiDomain.getMaThe());
            if (!TextUtils.isEmpty(hiDomain.getGtTheTu()))
                edtHiStart.setText(hiDomain.getGtTheTu());
            if (!TextUtils.isEmpty(hiDomain.getGtTheDen()))
                edtHiEnd.setText(hiDomain.getGtTheDen());
            if (!TextUtils.isEmpty(hiDomain.getMaKV())) {
                acpHiObject.setText(hiDomain.getMaKV());
                idHiObject = Helper.getIdByName(lstHiObject, hiDomain.getMaKV());
            }
            if (!TextUtils.isEmpty(hiDomain.getNgayDu5Nam()))
                edtHi5y.setText(hiDomain.getNgayDu5Nam());
            if (!TextUtils.isEmpty(hiDomain.getMaDKBDMoi())) {
                acpHospitalName.setText(hiDomain.getMaDKBDMoi());
                idHospitalName = Helper.getIdByName(lstHospitalName, hiDomain.getMaDKBDMoi());
            }
            if (!TextUtils.isEmpty(hiDomain.getDiaChi()))
                edtHiAddress.setText(hiDomain.getDiaChi());
        }
    }

    private void initView() {
        edtHiCode = view.findViewById(R.id.edtHiCode);
        edtHiAddress = view.findViewById(R.id.edtHiAddress);

        edtHiStart = view.findViewById(R.id.edtHiStart);
        view.findViewById(R.id.icDateStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar _calendar = Utils.dateStringConvert(edtHiStart.getText().toString(), Utils.ddMMyyyy);
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtHiStart.setText((dayOfMonth + "/" + month + "/" + year));
                    }
                }, _calendar.get(Calendar.YEAR), _calendar.get(Calendar.MONTH), _calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtHiEnd = view.findViewById(R.id.edtHiEnd);
        view.findViewById(R.id.icDateEnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar _calendar = Utils.dateStringConvert(edtHiEnd.getText().toString(), Utils.ddMMyyyy);
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtHiEnd.setText((dayOfMonth + "/" + month + "/" + year));
                    }
                }, _calendar.get(Calendar.YEAR), _calendar.get(Calendar.MONTH), _calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtHi5y = view.findViewById(R.id.edtHi5y);
        view.findViewById(R.id.icDate5y).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar _calendar = Utils.dateStringConvert(edtHi5y.getText().toString(), Utils.ddMMyyyy);
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtHi5y.setText((dayOfMonth + "/" + month + "/" + year));
                    }
                }, _calendar.get(Calendar.YEAR), _calendar.get(Calendar.MONTH), _calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void setupHospital(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpHospitalName = view.findViewById(R.id.acpHospitalName);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpHospitalName.setAdapter(adapter);
            acpHospitalName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), acpHospitalName);
                    idHospitalName = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpHospitalName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpHospitalName.showDropDown();
                }
            });
        }
    }

    private void setupHiObject(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpHiObject = view.findViewById(R.id.acpHiObject);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpHiObject.setAdapter(adapter);
            acpHiObject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Utils.keyboardClose(parent.getContext(), acpHiObject);
                    idHiObject = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpHiObject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpHiObject.showDropDown();
                }
            });
        }
    }

    public boolean validate() {
        boolean validate = true;
        if (!TextUtils.isEmpty(edtHiCode.getText())) {
            if (TextUtils.isEmpty(edtHiStart.getText())) {
                edtHiStart.setError(".");
                validate = false;
            }
            if (TextUtils.isEmpty(edtHiEnd.getText())) {
                edtHiEnd.setError(".");
                validate = false;
            }

            if (TextUtils.isEmpty(acpHospitalName.getText())) {
                acpHospitalName.setError(".");
                validate = false;
            }

            if (TextUtils.isEmpty(acpHiObject.getText())) {
                acpHiObject.setError(".");
                validate = false;
            }

            PatientHi patientHi = new PatientHi();
            patientHi.setIdline(Utils.newGuid());
            patientHi.setNohi(edtHiCode.getText().toString());
            patientHi.setStrday(Utils.dateConvert(edtHiStart.getText().toString(), Utils.ddMMyyyyHHmm, Utils.ddMMyyyyTHHmmss));
            patientHi.setEndday(Utils.dateConvert(edtHiEnd.getText().toString(), Utils.ddMMyyyyHHmm, Utils.ddMMyyyyTHHmmss));
            patientHi.setIdlivpla(idHiObject);
            patientHi.setIdhospital(idHospitalName);
            patientHi.setAddres(edtHiAddress.getText().toString());
            patientHi.setTime5y(Utils.dateConvert(edtHi5y.getText().toString(), Utils.ddMMyyyyHHmm, Utils.ddMMyyyyTHHmmss));

            List<PatientHi> lstPatientHi = new ArrayList<>();
            lstPatientHi.add(patientHi);
            Receiving.patientDomain.setLstPatientHi(lstPatientHi);
        }

        return validate;
    }

    private void getCateShare(final FieldName fieldName) {
        ApiController.getInstance().getCateShare(getActivity(), fieldName,
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(List<CateSharelDomain> list) {
                        switch (fieldName) {
                            case livpla:
                                lstHiObject = list;
                                setupHiObject(lstHiObject);
                                break;
                            case hospital:
                                lstHospitalName = list;
                                setupHospital(lstHospitalName);
                                break;
                        }
                    }
                });
    }

    @Override
    public void toolbarListener() {

    }
}
