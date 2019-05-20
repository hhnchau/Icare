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
import ptt.vn.icaremobileapp.model.inpatient.InpatientServiceOrder;
import ptt.vn.icaremobileapp.utils.Utils;

public class DialogEditServiceItem {
    private static DialogEditServiceItem instance = null;

    public static DialogEditServiceItem getInstance() {
        if (instance == null) {
            instance = new DialogEditServiceItem();
        }
        return instance;
    }

    public interface OnClickListener {
        void onClickListener();
    }

    public void show(final Context context, final InpatientServiceOrder serviceItem, final OnClickListener onClickListener) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_serviceitem);
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

            MyInputTextOutlineDisable tvUnit = dialog.findViewById(R.id.tvUnit);
            tvUnit.setValues(serviceItem.getNameunit());

            MyInputTextOutlineDisable tvPrice = dialog.findViewById(R.id.tvPrice);
            tvPrice.setValues(Utils.formatCurrency(serviceItem.getPrice()));

            MyInputTextOutlineDisable tvPriceInsurance = dialog.findViewById(R.id.tvPriceInsurance);
            tvPriceInsurance.setValues(Utils.formatCurrency(serviceItem.getPricehi()));

            MyInputTextOutlineDisable tvSurcharge = dialog.findViewById(R.id.tvSurcharge);
            tvSurcharge.setValues(Utils.formatCurrency(serviceItem.getPrice()));

            final MyInputTextOutline tvNumber = dialog.findViewById(R.id.tvNumber);
            tvNumber.setText(String.valueOf(serviceItem.getQty()));

            MyInputTextOutlineDisable tvTotal = dialog.findViewById(R.id.tvTotal);
            tvTotal.setValues(Utils.formatCurrency(serviceItem.getPrice()));

            final MyInputTextOutline tvNote = dialog.findViewById(R.id.tvNote);
            tvNote.setText(serviceItem.getDescrp());

            MyButton myButton = dialog.findViewById(R.id.btnOk);
            myButton.setOnSelectedListener(new MyButton.OnListener() {
                @Override
                public void onClick() {

                    serviceItem.setQty(Integer.parseInt(tvNumber.getText().toString()));
                    serviceItem.setDescrp(tvNote.getText().toString());

                    if (onClickListener != null)
                        onClickListener.onClickListener();
                    dialog.dismiss();
                }
            });
        }
    }
}
