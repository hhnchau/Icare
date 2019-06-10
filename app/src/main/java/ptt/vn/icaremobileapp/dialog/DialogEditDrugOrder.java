package ptt.vn.icaremobileapp.dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.autocomplete.AutoCompleteTextViewAdapter;
import ptt.vn.icaremobileapp.autocomplete.MyAutoCompleteTextView;
import ptt.vn.icaremobileapp.custom.MyButton;
import ptt.vn.icaremobileapp.custom.MyInputTextOutline;
import ptt.vn.icaremobileapp.custom.MyInputTextOutlineDisable;
import ptt.vn.icaremobileapp.custom.MyNumPad;
import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDrugOrder;
import ptt.vn.icaremobileapp.utils.Helper;
import ptt.vn.icaremobileapp.utils.Utils;

import static java.security.AccessController.getContext;

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

    public void show(final Context context, final InpatientDrugOrder inpatientDrugOrder, List<CateSharelDomain> lstAutoDrugRoute, List<CateSharelDomain> lstDrugUnitUse, final OnClickListener onClickListener) {
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

            final ConstraintLayout constraintLayout = dialog.findViewById(R.id.detailView);
            ConstraintSet set = new ConstraintSet();

            final MyNumPad padMorning = new MyNumPad(context);
            padMorning.setId(View.generateViewId());
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            constraintLayout.addView(padMorning, lp);
            set.clone(constraintLayout);
            set.connect(padMorning.getId(), ConstraintSet.TOP, R.id.edtDrugMorning, ConstraintSet.BOTTOM, 0);
            set.connect(padMorning.getId(), ConstraintSet.START, R.id.edtDrugMorning, ConstraintSet.START, 0);
            set.applyTo(constraintLayout);
            padMorning.hide();
            padMorning.setOnListener(new MyNumPad.OnListener() {
                @Override
                public void onKey(CharSequence chr) {
                    appendText(context, edtDrugMorning, chr);
                }
            });


            final MyNumPad padAfter = new MyNumPad(context);
            padAfter.setId(View.generateViewId());
            lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            constraintLayout.addView(padAfter, lp);
            set.clone(constraintLayout);
            set.connect(padAfter.getId(), ConstraintSet.TOP, R.id.edtDrugAfter, ConstraintSet.BOTTOM, 0);
            set.connect(padAfter.getId(), ConstraintSet.START, R.id.edtDrugAfter, ConstraintSet.START, 0);
            set.applyTo(constraintLayout);
            padAfter.hide();
            padAfter.setOnListener(new MyNumPad.OnListener() {
                @Override
                public void onKey(CharSequence chr) {
                    appendText(context, edtDrugAfter, chr);
                }
            });


            final MyNumPad padDinner = new MyNumPad(context);
            padDinner.setId(View.generateViewId());
            lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            constraintLayout.addView(padDinner, lp);
            set.clone(constraintLayout);
            set.connect(padDinner.getId(), ConstraintSet.TOP, R.id.edtDrugDinner, ConstraintSet.BOTTOM, 0);
            set.connect(padDinner.getId(), ConstraintSet.END, R.id.edtDrugDinner, ConstraintSet.END, 0);
            set.applyTo(constraintLayout);
            padDinner.hide();
            padDinner.setOnListener(new MyNumPad.OnListener() {
                @Override
                public void onKey(CharSequence chr) {
                    appendText(context, edtDrugDinner, chr);
                }
            });


            final MyNumPad padEvening = new MyNumPad(context);
            padEvening.setId(View.generateViewId());
            lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            constraintLayout.addView(padEvening, lp);
            set.clone(constraintLayout);
            set.connect(padEvening.getId(), ConstraintSet.TOP, R.id.edtDrugEvening, ConstraintSet.BOTTOM, 0);
            set.connect(padEvening.getId(), ConstraintSet.END, R.id.edtDrugEvening, ConstraintSet.END, 0);
            set.applyTo(constraintLayout);
            padEvening.hide();
            padEvening.setOnListener(new MyNumPad.OnListener() {
                @Override
                public void onKey(CharSequence chr) {
                    appendText(context, edtDrugEvening, chr);
                }
            });


            final MyNumPad padNumber = new MyNumPad(context);
            padNumber.setId(View.generateViewId());
            lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            constraintLayout.addView(padNumber, lp);
            set.clone(constraintLayout);
            set.connect(padNumber.getId(), ConstraintSet.BOTTOM, R.id.edtDrugNumber, ConstraintSet.TOP, 0);
            set.connect(padNumber.getId(), ConstraintSet.START, R.id.edtDrugNumber, ConstraintSet.START, 0);
            set.applyTo(constraintLayout);
            padNumber.hide();
            padNumber.setOnListener(new MyNumPad.OnListener() {
                @Override
                public void onKey(CharSequence chr) {
                    appendText(context, edtDrugNumber, chr);
                }
            });


            final MyNumPad padTotal = new MyNumPad(context);
            padTotal.setId(View.generateViewId());
            lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            constraintLayout.addView(padTotal, lp);
            set.clone(constraintLayout);
            set.connect(padTotal.getId(), ConstraintSet.BOTTOM, R.id.edtDrugTotal, ConstraintSet.TOP, 0);
            set.connect(padTotal.getId(), ConstraintSet.START, R.id.edtDrugTotal, ConstraintSet.START, 0);
            set.applyTo(constraintLayout);
            padTotal.hide();
            padTotal.setOnListener(new MyNumPad.OnListener() {
                @Override
                public void onKey(CharSequence chr) {
                    appendText(context, edtDrugTotal, chr);
                }
            });


            MyInputTextOutlineDisable tvDrug = dialog.findViewById(R.id.tvDrug);
            tvDrug.setValues(inpatientDrugOrder.getNamedrug());
            MyInputTextOutlineDisable tvActiveIngre = dialog.findViewById(R.id.tvActiveIngre);
            tvActiveIngre.setValues(inpatientDrugOrder.getActivename());

            edtDrugMorning = dialog.findViewById(R.id.edtDrugMorning);
            edtDrugMorning.keyboardClose();
            edtDrugMorning.setText(inpatientDrugOrder.getQtymor());
            edtDrugMorning.setOnChangeFocusListener(new MyInputTextOutline.OnChangeFocus() {
                @Override
                public void onHas() {
                    Utils.keyboardClose(context, edtDrugMorning);
                    padMorning.show();
                }

                @Override
                public void onLost() {
                    padMorning.hide();

                }
            });


            edtDrugAfter = dialog.findViewById(R.id.edtDrugAfter);
            edtDrugAfter.keyboardClose();
            edtDrugAfter.setText(inpatientDrugOrder.getQtyaft());
            edtDrugAfter.setOnChangeFocusListener(new MyInputTextOutline.OnChangeFocus() {
                @Override
                public void onHas() {
                    Utils.keyboardClose(context, edtDrugAfter);
                    padAfter.show();
                }

                @Override
                public void onLost() {
                    padAfter.hide();
                    calculation();
                }
            });

            edtDrugDinner = dialog.findViewById(R.id.edtDrugDinner);
            edtDrugDinner.keyboardClose();
            edtDrugDinner.setText(inpatientDrugOrder.getQtydin());
            edtDrugDinner.setOnChangeFocusListener(new MyInputTextOutline.OnChangeFocus() {
                @Override
                public void onHas() {
                    Utils.keyboardClose(context, edtDrugDinner);
                    padDinner.show();
                }

                @Override
                public void onLost() {
                    padDinner.hide();
                    calculation();
                }
            });


            edtDrugEvening = dialog.findViewById(R.id.edtDrugEvening);
            edtDrugEvening.keyboardClose();
            edtDrugEvening.setText(inpatientDrugOrder.getQtynig());
            edtDrugEvening.setOnChangeFocusListener(new MyInputTextOutline.OnChangeFocus() {
                @Override
                public void onHas() {
                    Utils.keyboardClose(context, edtDrugEvening);
                    padEvening.show();
                }

                @Override
                public void onLost() {
                    padEvening.hide();
                    calculation();
                }
            });


            edtDrugNumber = dialog.findViewById(R.id.edtDrugNumber);
            edtDrugNumber.keyboardClose();
            edtDrugNumber.setText(String.valueOf(inpatientDrugOrder.getQtyday()));
            edtDrugNumber.setOnChangeFocusListener(new MyInputTextOutline.OnChangeFocus() {
                @Override
                public void onHas() {
                    Utils.keyboardClose(context, edtDrugNumber);
                    padNumber.show();
                }

                @Override
                public void onLost() {
                    padNumber.hide();
                    calculation();
                }
            });


            edtDrugTotal = dialog.findViewById(R.id.edtDrugTotal);
            edtDrugTotal.keyboardClose();
            edtDrugTotal.setText(String.valueOf((int) inpatientDrugOrder.getQty()));
            edtDrugTotal.setOnChangeFocusListener(new MyInputTextOutline.OnChangeFocus() {
                @Override
                public void onHas() {
                    Utils.keyboardClose(context, edtDrugTotal);
                    padTotal.show();
                }

                @Override
                public void onLost() {
                    padTotal.hide();
                    calculation();
                }
            });


            final MyInputTextOutline edtDrugReason = dialog.findViewById(R.id.edtDrugReason);
            edtDrugReason.setText(inpatientDrugOrder.getDesc());

            if (lstAutoDrugRoute != null) {
                final MyAutoCompleteTextView acpDrugRoute = dialog.findViewById(R.id.acDrugRoute);
                AutoCompleteTextViewAdapter adapterAutoDrugRoute = new AutoCompleteTextViewAdapter(context, lstAutoDrugRoute);
                acpDrugRoute.setAdapter(adapterAutoDrugRoute);
                acpDrugRoute.keyboardClose();
                acpDrugRoute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Utils.keyboardClose(parent.getContext(), acpDrugRoute);
                        inpatientDrugOrder.setIdroute(((CateSharelDomain) parent.getItemAtPosition(position)).getIdline());
                        inpatientDrugOrder.setRoutename(((CateSharelDomain) parent.getItemAtPosition(position)).getName());
                    }
                });

                acpDrugRoute.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        acpDrugRoute.showDropDown();
                    }
                });
                acpDrugRoute.setText(inpatientDrugOrder.getRoutename());
            }

            if (lstDrugUnitUse != null) {
                final MyAutoCompleteTextView acpDrugUnitUse = dialog.findViewById(R.id.acpDrugUnitUse);
                AutoCompleteTextViewAdapter adapterAutoDrugUnitUse = new AutoCompleteTextViewAdapter(context, lstDrugUnitUse);
                acpDrugUnitUse.setAdapter(adapterAutoDrugUnitUse);
                acpDrugUnitUse.keyboardClose();
                acpDrugUnitUse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Utils.keyboardClose(parent.getContext(), acpDrugUnitUse);
                        inpatientDrugOrder.setIdunituse(((CateSharelDomain) parent.getItemAtPosition(position)).getIdline());
                        inpatientDrugOrder.setUnitusename(((CateSharelDomain) parent.getItemAtPosition(position)).getName());
                    }
                });

                acpDrugUnitUse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        acpDrugUnitUse.showDropDown();
                    }
                });
                acpDrugUnitUse.setText(inpatientDrugOrder.getUnitusename());
            }


            MyButton myButton = dialog.findViewById(R.id.btnOk);
            myButton.setOnSelectedListener(new MyButton.OnListener() {
                @Override
                public void onClick() {

                    inpatientDrugOrder.setQtymor(edtDrugMorning.getText().toString());
                    inpatientDrugOrder.setQtyaft(edtDrugAfter.getText().toString());
                    inpatientDrugOrder.setQtydin(edtDrugDinner.getText().toString());
                    inpatientDrugOrder.setQtynig(edtDrugMorning.getText().toString());
                    inpatientDrugOrder.setQty(Long.parseLong(edtDrugTotal.getText().toString()));
                    inpatientDrugOrder.setQtyday(Integer.parseInt(edtDrugNumber.getText().toString()));
                    inpatientDrugOrder.setTotal((long) (inpatientDrugOrder.getQty() * inpatientDrugOrder.getPrice()));
                    inpatientDrugOrder.setDesc(edtDrugReason.getText().toString());

                    if (onClickListener != null)
                        onClickListener.onClickListener();
                    dialog.dismiss();

                }
            });
        }
    }

    private void calculation() {
        String number = edtDrugNumber.getText().toString();
        if (!TextUtils.isEmpty(number)) {

            String morning = edtDrugMorning.getText().toString();
            String after = edtDrugAfter.getText().toString();
            String dinner = edtDrugDinner.getText().toString();
            String evening = edtDrugEvening.getText().toString();

            edtDrugTotal.setText(Helper.calculateDrugTotal(number, morning, after, dinner, evening));
        }
    }

    private void appendText(Context context, MyInputTextOutline v, CharSequence chr) {
        Editable editable = v.getEditable();
        int start = v.getSelectionStart();
        int end = v.getSelectionEnd();

        if (chr.equals(context.getString(R.string.btn_clear))) {
            if (editable != null && start > 0) {
                editable.delete(start - 1, start);
            } else if (editable != null && start != end) {
                editable.delete(start, end);
            }
        } else if (chr.equals(context.getString(R.string.btn_done))) {
            v.clearFocus();
        } else {
            if (start != end) {
                editable.delete(start, end);
            }
            editable.insert(start, chr);
        }
    }
}
