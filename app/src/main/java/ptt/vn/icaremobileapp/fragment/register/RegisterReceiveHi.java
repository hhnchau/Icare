package ptt.vn.icaremobileapp.fragment.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.api.Callback;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewAdapter;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewHiListAdapter;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewHiRatioOtherAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.custom.MyInputTextOutlineDisable;
import ptt.vn.icaremobileapp.custom.MyInputTextOutlineMultiLineDisable;
import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.hi.HiDomain;
import ptt.vn.icaremobileapp.model.hi.HiRatioOtherDomain;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientHi;
import ptt.vn.icaremobileapp.model.register.RegisterDomain;
import ptt.vn.icaremobileapp.model.register.RegisterHi;
import ptt.vn.icaremobileapp.utils.Utils;

import static ptt.vn.icaremobileapp.model.filter.FieldName.examination;

public class RegisterReceiveHi extends BaseFragment {
    private View view;
    private MyAutoCompleteTextView acpInsuranceCode, acpDocumentType, acpDocument, acpOtherSource;
    private MyInputTextOutlineDisable tvInsuranceStart, tvInsuranceRatio, tvInsuranceRoutes, tvInsuranceEnd;
    private MyInputTextOutlineMultiLineDisable tvInsurancePlace;
    private PatientDomain patientDomain;
    private PatientHi patientHi;
    private RegisterHi registerHiRatio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register_receive_hi, container, false);
        if (getArguments() != null)
            patientDomain = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_PATIENT);

        if (patientDomain != null) {
            initView();
            getCateShare();

            List<PatientHi> lstPatientHi = patientDomain.getLstPatientHi();
            if (lstPatientHi != null && lstPatientHi.size() > 0) {
                patientHi = lstPatientHi.get(0);
                setupHi(lstPatientHi);
                acpInsuranceCode.setText(patientHi.getNohi());
                setView(patientHi);
            }
        }
        return view;
    }

    private void initView() {
        tvInsuranceStart = view.findViewById(R.id.tvInsuranceStart);
        tvInsuranceRatio = view.findViewById(R.id.tvInsuranceRatio);
        tvInsuranceRoutes = view.findViewById(R.id.tvInsuranceRoutes);
        tvInsuranceEnd = view.findViewById(R.id.tvInsuranceEnd);
        tvInsurancePlace = view.findViewById(R.id.tvInsurancePlace);
        acpOtherSource = view.findViewById(R.id.acpOtherSource);
    }

    private void setView(PatientHi patientHi) {
        if (patientHi != null) {
            tvInsuranceStart.setValues(Utils.dateConvert(patientHi.getStrday(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
            tvInsuranceEnd.setValues(Utils.dateConvert(patientHi.getEndday(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
            tvInsurancePlace.setText(patientHi.getHospitalname());
            if (patientDomain.getPatid() != null)
                getHiRatio(patientDomain.getPatid(), patientHi.getNohi(), 0);
            getHiRatioOther(patientHi.getNohi());
        }
    }

    private void setupHi(List<PatientHi> lst) {
        if (getActivity() != null) {
            acpInsuranceCode = view.findViewById(R.id.acpInsuranceCode);

            AutoCompleteTextViewHiListAdapter adapter = new AutoCompleteTextViewHiListAdapter(getActivity(), lst);
            acpInsuranceCode.setAdapter(adapter);
            acpInsuranceCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //acpDocumentType = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                    setView((PatientHi) parent.getItemAtPosition(position));
                }
            });

            acpInsuranceCode.keyboardClose();

            acpInsuranceCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.keyboardClose(v.getContext(), v);
                    acpInsuranceCode.showDropDown();
                }
            });
        }
    }

    private void setupDocumentType(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpDocumentType = view.findViewById(R.id.acpDocumentType);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpDocumentType.setAdapter(adapter);
            acpDocumentType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //acpDocumentType = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpDocumentType.keyboardClose();

            acpDocumentType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.keyboardClose(v.getContext(), v);
                    acpDocumentType.showDropDown();
                }
            });
        }
    }

    private void setupOtherSource(List<HiRatioOtherDomain> lst) {
        if (getActivity() != null) {


            AutoCompleteTextViewHiRatioOtherAdapter adapterOtherSource = new AutoCompleteTextViewHiRatioOtherAdapter(getActivity(), lst);
            acpOtherSource.setAdapter(adapterOtherSource);
            acpOtherSource.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //acpDocumentType = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpOtherSource.keyboardClose();

            acpOtherSource.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.keyboardClose(v.getContext(), v);
                    acpOtherSource.showDropDown();
                }
            });
        }
    }

    private void getCateShare() {
        ApiController.getInstance().getCateShare(getActivity(), examination,
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(List<CateSharelDomain> list) {
                        setupDocumentType(list);
                    }
                });
    }

    private void getHiRatio(String patid, String nohi, int typeexamination) {
        if (getActivity() != null)
            ApiController.getInstance().getHiRatio(getActivity(), patid, nohi, typeexamination, new Callback<RegisterDomain>() {
                @Override
                public void response(RegisterDomain registerDomain) {
                    if (registerDomain.getLstRegisterHi() != null && registerDomain.getLstRegisterHi().size() > 0) {
                        registerHiRatio = registerDomain.getLstRegisterHi().get(0);
                        tvInsuranceRatio.setValues(String.valueOf(registerHiRatio.getRatehi()));
                        tvInsuranceRoutes.setValues(registerHiRatio.getReasonname());
                    }
                }
            });
    }

    private void getHiRatioOther(String nohi) {
        if (getActivity() != null && nohi != null && nohi.length() > 3)
            ApiController.getInstance().getHiRatioOther(getActivity(), nohi.substring(0, 3), new ACallback<HiRatioOtherDomain>() {
                @Override
                public void response(final List<HiRatioOtherDomain> list) {
                    setupOtherSource(list);
                }
            });
    }

    public boolean validate() {
        boolean validate = true;

        if (TextUtils.isEmpty(acpInsuranceCode.getText())) {
            acpInsuranceCode.setError(".");
            validate = false;
        }

        if (!validate) return false;

        RegisterHi registerHi = new RegisterHi();
        registerHi.setIdline(patientHi.getIdline());
        if (registerHiRatio != null) {
            registerHi.setRatehi(registerHiRatio.getRatehi());
            registerHi.setRatepay(registerHiRatio.getRatepay());
            registerHi.setReason(registerHiRatio.getReason());
            registerHi.setReasonname(registerHiRatio.getReasonname());
            registerHi.setMinpay(registerHiRatio.getMinpay());
            registerHi.setMaxpay(registerHiRatio.getMaxpay());
            registerHi.setSalary(registerHiRatio.getSalary());
            registerHi.setRateother(registerHiRatio.getRateother());
            registerHi.setLevel(registerHiRatio.getLevel());
        }
        List<RegisterHi> lstRegisterHi = new ArrayList<>();
        lstRegisterHi.add(registerHi);
        Register.registerDomain.setLstRegisterHi(lstRegisterHi);

        return true;
    }

    @Override
    public void toolbarListener() {

    }
}
