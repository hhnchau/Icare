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
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.custom.MyInputText;
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
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
        void onClickListener(HappeningDomain happening);
    }

    public void show(final Context context, final HappeningDomain happening, final OnClickListener onClickListener) {
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
            dialog.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            final MyInputTextOutline edtHappening = dialog.findViewById(R.id.edtHappening);
            final EditText edtDatetime = dialog.findViewById(R.id.edtDateTime);
            final MyInputTextOutline edtCircuit = dialog.findViewById(R.id.edtCircuit);
            final MyInputTextOutline edtBloodMax = dialog.findViewById(R.id.edtBloodMax);
            final MyInputTextOutline edtBloodMin = dialog.findViewById(R.id.edtBloodMin);
            final MyInputTextOutline edtTemperature = dialog.findViewById(R.id.edtTemperature);
            final MyInputTextOutline edtHeartbeat = dialog.findViewById(R.id.edtHeartbeat);
            final MyInputTextOutline edtWeight = dialog.findViewById(R.id.edtWeight);
            ImageView icDatetime = dialog.findViewById(R.id.icDate);
            icDatetime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar _calendar = Utils.dateStringConvert(edtDatetime.getText().toString(), Utils.ddMMyyyy);
                    new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            Calendar cal = Calendar.getInstance();
                            edtDatetime.setText(dayOfMonth + "/" + month + "/" + year + " " + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE));
                        }
                    }, _calendar.get(Calendar.YEAR), _calendar.get(Calendar.MONTH), _calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });


            /*
             * UPDATE
             */
            if (happening != null) {
                edtHappening.setText(happening.getHappening());
                edtDatetime.setText(Utils.dateConvert(happening.getDatecreate(), Utils.ddMMyyyyTHHmmss, Utils.ddMMyyyyHHmm));
                edtCircuit.setText(String.valueOf(happening.getCircui()));
                edtBloodMax.setText(happening.getBlomax() + "");// + " / " + happening.getBlomin());
                edtTemperature.setText(String.valueOf(happening.getTemper()));
                edtHeartbeat.setText(String.valueOf(happening.getHeartb()));
                edtWeight.setText(String.valueOf(happening.getWeight()));
            } else {
                edtDatetime.setText(Utils.getCurrentDate(Utils.ddMMyyyyHHmm));
            }

            MyButton myButton = dialog.findViewById(R.id.btnOk);
            myButton.setOnSelectedListener(new MyButton.OnListener() {
                @Override
                public void onClick() {
                    HappeningDomain happen = new HappeningDomain();
                    if (happening != null) happen = happening;
                    happen.setHappening(edtHappening.getText().toString());
                    happen.setDatecreate(Utils.dateConvert(edtDatetime.getText().toString(), Utils.ddMMyyyyHHmm, Utils.ddMMyyyyTHHmmss));
                    happen.setIddoctor(1);

                    happen.setCircui(edtCircuit.getText().toString());

                    happen.setBlomax(Integer.parseInt(edtBloodMax.getText().toString()));
                    happen.setTemper(Float.parseFloat(edtTemperature.getText().toString()));
                    happen.setHeartb(Integer.parseInt(edtHeartbeat.getText().toString()));
                    happen.setWeight(Float.parseFloat(edtWeight.getText().toString()));

                    if (onClickListener != null)
                        onClickListener.onClickListener(happen);
                    dialog.dismiss();
                }
            });
        }
    }
}
