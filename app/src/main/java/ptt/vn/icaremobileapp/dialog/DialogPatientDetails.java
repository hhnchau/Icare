package ptt.vn.icaremobileapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyTextView;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.utils.Utils;

public class DialogPatientDetails {
    private static DialogPatientDetails instance = null;

    public static DialogPatientDetails getInstance() {
        if (instance == null) {
            instance = new DialogPatientDetails();
        }
        return instance;
    }

    public interface OnClickListener {
        void onClickListener();
    }

    public void show(final Context context, final PatientDomain patientDomain, final OnClickListener onClickListener) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_patient_details);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        if (window != null) {

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorMain));
            }


            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.CENTER;
            window.setAttributes(wlp);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

            dialog.show();

            /*
             * VIEW
             */
            dialog.findViewById(R.id.frame).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.findViewById(R.id.tvEdit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                }
            });

            TextView tvName = dialog.findViewById(R.id.tvName);
            MyTextView tvCode = dialog.findViewById(R.id.tvCode);
            MyTextView tvBirthday = dialog.findViewById(R.id.tvBirthday);
            MyTextView tvIde = dialog.findViewById(R.id.tvIde);
            MyTextView tvHi = dialog.findViewById(R.id.tvHi);
            MyTextView tvPhone = dialog.findViewById(R.id.tvPhone);
            MyTextView tvGender = dialog.findViewById(R.id.tvGender);
            MyTextView tvJob = dialog.findViewById(R.id.tvJob);
            MyTextView tvBhi = dialog.findViewById(R.id.tvBhi);
            MyTextView tvAddr = dialog.findViewById(R.id.tvAddr);

            if (patientDomain != null) {

                tvName.setText(patientDomain.getPATIENTNAME());

                tvCode.setValues(patientDomain.getHospcode());
                tvBirthday.setValues(Utils.dateConvert(patientDomain.getBirthday(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyy));
                if (patientDomain.getLstPatientIde() != null && patientDomain.getLstPatientIde().size() > 0)
                    tvIde.setValues(patientDomain.getLstPatientIde().get(0).getCardid());
                if (patientDomain.getLstPatientHi() != null && patientDomain.getLstPatientHi().size() > 0)
                    tvHi.setValues(patientDomain.getLstPatientHi().get(0).getNohi());

                tvPhone.setValues(patientDomain.getPhone());
                tvGender.setValues(patientDomain.getGender());
                tvJob.setValues(patientDomain.getNamejob());
                if (patientDomain.getLstPatientBhi() != null && patientDomain.getLstPatientBhi().size() > 0)
                    tvBhi.setValues(patientDomain.getLstPatientBhi().get(0).getNobhi());

                if (patientDomain.getLstPatientAddr() != null && patientDomain.getLstPatientAddr().size() > 0)
                    tvAddr.setValues(patientDomain.getLstPatientAddr().get(0).getAddresfull());
            }
        }
    }
}
