package ptt.vn.icaremobileapp.fragment.register;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import ptt.vn.icaremobileapp.api.ACallback;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewAdapter;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewDiscountListAdapter;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewPriceListAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.custom.MyInputTextOutlineMultiLine;
import ptt.vn.icaremobileapp.fragment.BaseFragment;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.discount.DiscountHDomain;
import ptt.vn.icaremobileapp.model.filter.FieldName;
import ptt.vn.icaremobileapp.model.serviceitem.MapPriceServiceItemHDomain;
import ptt.vn.icaremobileapp.utils.Utils;

import static ptt.vn.icaremobileapp.model.filter.FieldName.formco;
import static ptt.vn.icaremobileapp.model.filter.FieldName.introd;
import static ptt.vn.icaremobileapp.model.filter.FieldName.pricelist;
import static ptt.vn.icaremobileapp.model.filter.FieldName.priorityobject;
import static ptt.vn.icaremobileapp.model.filter.FieldName.regobject;
import static ptt.vn.icaremobileapp.model.filter.FieldName.typrec;


public class RegisterReceive extends BaseFragment {
    private View view;
    private RegisterReceiveHi frgHi;
    private RegisterReceiveBhi frgBhi;
    private MyAutoCompleteTextView acpFormality, acpPriorityObject, acpPriceType, acpPlaceIntroduce, acpReceiveType, acpPatientObject, acpDiscount;
    private MyInputTextOutlineMultiLine edtSymptom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register_receive, container, false);
        frgHi = new RegisterReceiveHi();
        frgBhi = new RegisterReceiveBhi();

        replaceFragment(frgBhi);

        getCateShare(formco);
        getCateShare(typrec);
        getCateShare(regobject);
        getCateShare(priorityobject);
        getCateShare(introd);
        getPriceListServiceItem();
        getDiscountList();

        return view;
    }

    private void replaceFragment(final Fragment frg) {
        if (getActivity() != null) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frameInsurance, frg, frg.getClass().getName())
                            .commit();
                }
            });
        }
    }

    private void setupFormality(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpFormality = view.findViewById(R.id.acpFormality);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpFormality.setAdapter(adapter);
            acpFormality.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //idHospitalName = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpFormality.keyboardClose();

            acpFormality.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpFormality.showDropDown();
                }
            });
        }
    }

    private void setupReceiveType(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpReceiveType = view.findViewById(R.id.acpReceiveType);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpReceiveType.setAdapter(adapter);
            acpReceiveType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //idHospitalName = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpReceiveType.keyboardClose();

            acpReceiveType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpReceiveType.showDropDown();
                }
            });
        }
    }

    private void setupPatientObject(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpPatientObject = view.findViewById(R.id.acpPatientObject);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpPatientObject.setAdapter(adapter);
            acpPatientObject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //idHospitalName = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPatientObject.keyboardClose();

            acpPatientObject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPatientObject.showDropDown();
                }
            });
        }
    }

    private void setupPriorityObject(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpPriorityObject = view.findViewById(R.id.acpObjectReceive);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpPriorityObject.setAdapter(adapter);
            acpPriorityObject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //idHospitalName = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPriorityObject.keyboardClose();

            acpPriorityObject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPriorityObject.showDropDown();
                }
            });
        }
    }

    private void setupPriceType(List<MapPriceServiceItemHDomain> lst) {
        if (getActivity() != null) {
            acpPriceType = view.findViewById(R.id.acpPriceType);

            AutoCompleteTextViewPriceListAdapter adapter = new AutoCompleteTextViewPriceListAdapter(getActivity(), lst);
            acpPriceType.setAdapter(adapter);
            acpPriceType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //idHospitalName = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPriceType.keyboardClose();

            acpPriceType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPriceType.showDropDown();
                }
            });
        }
    }

    private void setupPlaceIntroduce(List<CateSharelDomain> lst) {
        if (getActivity() != null) {
            acpPlaceIntroduce = view.findViewById(R.id.acpPlaceIntroduce);

            AutoCompleteTextViewAdapter adapter = new AutoCompleteTextViewAdapter(getActivity(), lst);
            acpPlaceIntroduce.setAdapter(adapter);
            acpPlaceIntroduce.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //idHospitalName = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpPlaceIntroduce.keyboardClose();

            acpPlaceIntroduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpPlaceIntroduce.showDropDown();
                }
            });
        }
    }

    private void setupDiscount(List<DiscountHDomain> lst) {
        if (getActivity() != null) {
            acpDiscount = view.findViewById(R.id.acpDiscount);

            AutoCompleteTextViewDiscountListAdapter adapter = new AutoCompleteTextViewDiscountListAdapter(getActivity(), lst);
            acpDiscount.setAdapter(adapter);
            acpDiscount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //idHospitalName = ((CateSharelDomain) parent.getItemAtPosition(position)).getIdline();
                }
            });

            acpDiscount.keyboardClose();

            acpDiscount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    acpDiscount.showDropDown();
                }
            });
        }
    }

    private void getCateShare(final FieldName fieldName) {
        ApiController.getInstance().getCateShare(getActivity(), fieldName,
                new ACallback<CateSharelDomain>() {
                    @Override
                    public void response(List<CateSharelDomain> list) {
                        switch (fieldName) {
                            case formco:
                                setupFormality(list);
                                break;
                            case typrec:
                                setupReceiveType(list);
                                break;
                            case regobject:
                                setupPatientObject(list);
                                break;
                            case priorityobject:
                                setupPriorityObject(list);
                                break;
                            case introd:
                                setupPlaceIntroduce(list);
                                break;
                        }
                    }
                });
    }

    private void getPriceListServiceItem() {
        if (getActivity() != null)
            ApiController.getInstance().getPriceListServiceItem(getActivity(), new ACallback<MapPriceServiceItemHDomain>() {
                @Override
                public void response(final List<MapPriceServiceItemHDomain> list) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                setupPriceType(list);
                            }
                        });
                    }
                }
            });
    }

    private void getDiscountList() {
        if (getActivity() != null)
            ApiController.getInstance().getDiscountList(getActivity(), new ACallback<DiscountHDomain>() {
                @Override
                public void response(final List<DiscountHDomain> list) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                setupDiscount(list);
                            }
                        });
                    }
                }
            });
    }

    public boolean validate() {
        boolean validate = true;
//        if (!TextUtils.isEmpty(edtHiCode.getText())) {
//            if (TextUtils.isEmpty(edtHiStart.getText())) {
//                edtHiStart.setError(".");
//                validate = false;
//            }
//            if (TextUtils.isEmpty(edtHiEnd.getText())) {
//                edtHiEnd.setError(".");
//                validate = false;
//            }
//
//            if (TextUtils.isEmpty(acpHospitalName.getText())) {
//                acpHospitalName.setError(".");
//                validate = false;
//            }
//
//            if (TextUtils.isEmpty(acpHiObject.getText())) {
//                acpHiObject.setError(".");
//                validate = false;
//            }
//
//            PatientHi patientHi = new PatientHi();
//            patientHi.setIdline(Utils.newGuid());
//            patientHi.setNohi(edtHiCode.getText().toString());
//            patientHi.setStrday(Utils.dateConvert(edtHiStart.getText().toString(), Utils.ddMMyyyyHHmm, Utils.ddMMyyyyTHHmmss));
//            patientHi.setEndday(Utils.dateConvert(edtHiEnd.getText().toString(), Utils.ddMMyyyyHHmm, Utils.ddMMyyyyTHHmmss));
//            patientHi.setIdlivpla(idHiObject);
//            patientHi.setIdhospital(idHospitalName);
//            patientHi.setAddres(edtHiAddress.getText().toString());
//            patientHi.setTime5y(Utils.dateConvert(edtHi5y.getText().toString(), Utils.ddMMyyyyHHmm, Utils.ddMMyyyyTHHmmss));
//
//            List<PatientHi> lstPatientHi = new ArrayList<>();
//            lstPatientHi.add(patientHi);
//            Receiving.patientDomain.setLstPatientHi(lstPatientHi);
//        }

        return validate;
    }

    @Override
    public void toolbarListener() {

    }
}
