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
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.custom.MyInputTextOutlineDisable;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDrugOrder;
import ptt.vn.icaremobileapp.utils.Utils;

public class DialogEditDrugOrder {
    private static DialogEditDrugOrder instance = null;

    public static DialogEditDrugOrder getInstance() {
        if (instance == null) {
            instance = new DialogEditDrugOrder();
        }
        return instance;
    }


    private MyInputTextOutline edtDrugMorning, edtDrugAfter, edtDrugDinner, edtDrugEvening, edtDrugNumber, edtDrugTotal;

    public interface OnClickListener {
        void onClickListener();
    }

    public void show(final Context context, final InpatientDrugOrder inpatientDrugOrder, final OnClickListener onClickListener) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_drugorder);
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


            MyInputTextOutlineDisable tvDrug = dialog.findViewById(R.id.tvDrug);
            tvDrug.setValues(inpatientDrugOrder.getNamedrug());
            MyInputTextOutlineDisable tvActiveIngre = dialog.findViewById(R.id.tvActiveIngre);
            tvActiveIngre.setValues(inpatientDrugOrder.getActivename());

            edtDrugMorning = dialog.findViewById(R.id.edtDrugMorning);
            edtDrugMorning.setText(inpatientDrugOrder.getQtymor());
            edtDrugMorning.registerNumPadKeyboard(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });
            edtDrugMorning.setOnLostFocusListener(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });


            edtDrugAfter = dialog.findViewById(R.id.edtDrugAfter);
            edtDrugAfter.setText(inpatientDrugOrder.getQtyaft());
            edtDrugAfter.registerNumPadKeyboard(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });
            edtDrugAfter.setOnLostFocusListener(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });

            edtDrugDinner = dialog.findViewById(R.id.edtDrugDinner);
            edtDrugDinner.setText(inpatientDrugOrder.getQtydin());
            edtDrugDinner.registerNumPadKeyboard(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });
            edtDrugDinner.setOnLostFocusListener(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });


            edtDrugEvening = dialog.findViewById(R.id.edtDrugEvening);
            edtDrugEvening.setText(inpatientDrugOrder.getQtynig());
            edtDrugEvening.registerNumPadKeyboard(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });
            edtDrugEvening.setOnLostFocusListener(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });


            edtDrugNumber = dialog.findViewById(R.id.edtDrugNumber);
            edtDrugNumber.setText(String.valueOf(inpatientDrugOrder.getQty()));
            edtDrugNumber.registerNumPadKeyboard(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });
            edtDrugNumber.setOnLostFocusListener(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });


            edtDrugTotal = dialog.findViewById(R.id.edtDrugTotal);
            edtDrugTotal.setText(String.valueOf(inpatientDrugOrder.getTotal()));
            edtDrugTotal.registerNumPadKeyboard(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });
            edtDrugTotal.setOnLostFocusListener(new MyInputTextOutline.OnLostFocus() {
                @Override
                public void onLost() {
                    calculation();
                }
            });


            MyInputTextOutline edtDrugReason = dialog.findViewById(R.id.edtDrugReason);
            edtDrugReason.setText(inpatientDrugOrder.getDesc());

            MyButton myButton = dialog.findViewById(R.id.btnOk);
            myButton.setOnSelectedListener(new MyButton.OnListener() {
                @Override
                public void onClick() {

                    inpatientDrugOrder.setQtymor(edtDrugMorning.getText().toString());
                    inpatientDrugOrder.setQtyaft(edtDrugAfter.getText().toString());
                    inpatientDrugOrder.setQtydin(edtDrugDinner.getText().toString());
                    inpatientDrugOrder.setQtynig(edtDrugMorning.getText().toString());
                    inpatientDrugOrder.setTotal(Long.parseLong(edtDrugTotal.getText().toString()));
                    inpatientDrugOrder.setQty(Long.parseLong(edtDrugNumber.getText().toString()));
                    inpatientDrugOrder.setDesc(edtDrugMorning.getText().toString());

                    if (onClickListener != null)
                        onClickListener.onClickListener();
                    dialog.dismiss();
                }
            });
        }
    }

    private void calculation() {

    }
}
