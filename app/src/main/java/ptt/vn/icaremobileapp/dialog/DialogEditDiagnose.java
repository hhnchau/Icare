package ptt.vn.icaremobileapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.custom.MyInputTextOutlineDisable;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDiagnose;

public class DialogEditDiagnose {
    private static DialogEditDiagnose instance = null;

    public static DialogEditDiagnose getInstance() {
        if (instance == null) {
            instance = new DialogEditDiagnose();
        }
        return instance;
    }

    public interface OnClickListener {
        void onClickListener(InpatientDiagnose diagnose);
    }

    public void show(final Context context, final InpatientDiagnose diagnose, final OnClickListener onClickListener) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_diagnose);
        dialog.setCancelable(false);
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
            dialog.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            MyInputTextOutlineDisable edtIcdCode = dialog.findViewById(R.id.edtIcdCode);
            final MyInputTextOutline edtIcdVn = dialog.findViewById(R.id.edtIcdVn);
            MyInputTextOutlineDisable edtIcdEn = dialog.findViewById(R.id.edtIcdEn);

            edtIcdCode.setContentView(context.getString(R.string.txt_diagnose_code), diagnose.getCode());
            edtIcdEn.setContentView(context.getString(R.string.txt_diagnose_name_en), diagnose.getNameicdeng());
            edtIcdVn.setCustomView(context.getString(R.string.txt_diagnose_name_vn), diagnose.getNameicdvn(), true);


            MyButton myButton = dialog.findViewById(R.id.btnOk);
            myButton.setOnSelectedListener(new MyButton.OnListener() {
                @Override
                public void onClick() {

                    diagnose.setNameicdvn(edtIcdVn.getText().toString().trim());

                    if (onClickListener != null)
                        onClickListener.onClickListener(diagnose);
                    dialog.dismiss();
                }
            });
        }
    }
}
