package ptt.vn.icaremobileapp.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.custom.MyInputText;
import ptt.vn.icaremobileapp.utils.Utils;

public class DialogNewHappening {
    private static DialogNewHappening instance = null;

    public static DialogNewHappening getInstance() {
        if (instance == null) {
            instance = new DialogNewHappening();
        }
        return instance;
    }

    public interface OnClickListener {
        void onClickListener(int p);
    }

    public void show(final Context context, OnClickListener onClickListener) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_new_happening);
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
//            dialog.findViewById(R.id.frame).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });

            MyInputText tvDate = dialog.findViewById(R.id.tvDoctor);
            tvDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Calendar _calendar = Utils.dateStringConvert("20/2/2019");
                    int i = _calendar.get(Calendar.YEAR);
                    int ii = _calendar.get(Calendar.MONTH);
                    int iii = _calendar.get(Calendar.DAY_OF_MONTH);


                    String s = Utils.dateFormat(_calendar, Utils.ddMMyyyy);

                    new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, month);
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        }
                    }, _calendar.get(Calendar.YEAR), _calendar.get(Calendar.MONTH), _calendar.get(Calendar.DAY_OF_MONTH)).show();







                }
            });


            MyButton myButton = dialog.findViewById(R.id.btnOk);
            myButton.setOnSelectedListener(new MyButton.OnListener() {
                @Override
                public void onClick() {
                    Toast.makeText(context, "Nhấn", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });


        }
    }

}
