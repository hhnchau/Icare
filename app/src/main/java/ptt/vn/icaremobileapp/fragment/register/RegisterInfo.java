package ptt.vn.icaremobileapp.fragment.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import ptt.vn.icaremobileapp.activity.MainActivity;
import ptt.vn.icaremobileapp.custom.MyInputTextOutlineDisable;
import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentuz;
import ptt.vn.icaremobileapp.model.filter.Purpose;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.utils.Utils;


public class RegisterInfo extends BaseFragment {
    private View view;
    private EditText edtPatientCode;
    private MyInputTextOutlineDisable tvPatientSex, tvPatientBirthday, tvPatientNation, tvPatientProvince, tvPatientWard, tvPatientJob, tvPatientPassport, tvParentName,
            tvPatientName, tvPatientMarried, tvPatientPhone, tvPatientEthnic, tvPatientDistrict, tvPatientStreet, tvPatientIde, tvPatientEmail, tvParentIde;

    private PatientDomain patientDomain = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register_info, container, false);
        if (getArguments() != null)
            patientDomain = getArguments().getParcelable(Fragmentuz.BUNDLE_KEY_PATIENT);

        initView();
        setView(patientDomain);

        return view;
    }

    private void setView(PatientDomain patientDomain) {
        if (patientDomain != null) {
            edtPatientCode.setText(patientDomain.getHospcode());
            tvPatientSex.setValues(patientDomain.getGender());
            tvPatientBirthday.setValues(Utils.dateConvert(patientDomain.getBirthday(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
            tvPatientNation.setValues(patientDomain.getNamenation());
            tvPatientJob.setValues(patientDomain.getNamejob());
            tvPatientPassport.setValues(patientDomain.getPaport());
            tvParentName.setValues(patientDomain.getFaname());
            tvPatientName.setValues(patientDomain.getPATIENTNAME());
            tvPatientMarried.setValues(patientDomain.getMarried());
            tvPatientPhone.setValues(patientDomain.getPhone());
            tvPatientEthnic.setValues(patientDomain.getNameethnic());
            tvPatientEmail.setValues(patientDomain.getEmail());
            tvParentIde.setValues(patientDomain.getFacard());
            if (patientDomain.getLstPatientAddr() != null && patientDomain.getLstPatientAddr().size() > 0) {
                tvPatientProvince.setValues(patientDomain.getLstPatientAddr().get(0).getNameprovin());
                tvPatientWard.setValues(patientDomain.getLstPatientAddr().get(0).getNameward());
                tvPatientDistrict.setValues(patientDomain.getLstPatientAddr().get(0).getNamedistric());
                tvPatientStreet.setValues(patientDomain.getLstPatientAddr().get(0).getNofhus());
            }
            if (patientDomain.getLstPatientIde() != null && patientDomain.getLstPatientIde().size() > 0)
                tvPatientIde.setValues(patientDomain.getLstPatientIde().get(0).getCardid());
        }
    }

    private void initView() {
        edtPatientCode = view.findViewById(R.id.edtPatientCode);
        tvPatientSex = view.findViewById(R.id.tvPatientSex);
        tvPatientBirthday = view.findViewById(R.id.tvPatientBirthday);
        tvPatientNation = view.findViewById(R.id.tvPatientNation);
        tvPatientProvince = view.findViewById(R.id.tvPatientProvince);
        tvPatientWard = view.findViewById(R.id.tvPatientWard);
        tvPatientJob = view.findViewById(R.id.tvPatientJob);
        tvPatientPassport = view.findViewById(R.id.tvPatientPassport);
        tvParentName = view.findViewById(R.id.tvParentName);
        tvPatientName = view.findViewById(R.id.tvPatientName);
        tvPatientMarried = view.findViewById(R.id.tvPatientMarried);
        tvPatientPhone = view.findViewById(R.id.tvPatientPhone);
        tvPatientEthnic = view.findViewById(R.id.tvPatientEthnic);
        tvPatientDistrict = view.findViewById(R.id.tvPatientDistrict);
        tvPatientStreet = view.findViewById(R.id.tvPatientStreet);
        tvPatientIde = view.findViewById(R.id.tvPatientIde);
        tvPatientEmail = view.findViewById(R.id.tvPatientEmail);
        tvParentIde = view.findViewById(R.id.tvParentIde);

        ImageView icSearch = view.findViewById(R.id.icSearch);
        icSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoFragment();
            }
        });
    }

    private void gotoFragment() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).gotoFragment(Fragmentez.PATIENT_LIST, null);
        }
    }

    public boolean validate() {
        if (patientDomain == null) return false;
        Register.registerDomain.setPatcode(patientDomain.getPatcode());
        Register.registerDomain.setPatid(patientDomain.getPatid());
        Register.registerDomain.setPurpos(Purpose.NgoaiTru.name());
        if (patientDomain.getLstPatientAddr() != null && patientDomain.getLstPatientAddr().size() > 0)
            Register.registerDomain.setIdaddr(patientDomain.getLstPatientAddr().get(0).getIdline());
        if (patientDomain.getLstPatientIde() != null && patientDomain.getLstPatientIde().size() > 0)
            Register.registerDomain.setIdide(patientDomain.getLstPatientIde().get(0).getIdline());

        return true;
    }

    @Override
    public void toolbarListener() {

    }
}
