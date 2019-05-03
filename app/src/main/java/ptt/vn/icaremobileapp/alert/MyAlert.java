package ptt.vn.icaremobileapp.alert;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ptt.vn.icaremobileapp.R;


/**
 * Created by kingpes on 8/22/18.
 */

public class MyAlert {
    private static MyAlert instance = null;

    public static MyAlert getInstance() {
        if (instance == null) {
            instance = new MyAlert();
        }
        return instance;
    }

    public static final int BLUE = R.color.blue;
    public static final int REB = R.color.red;
    public static final int WHITE = R.color.wh;

    public void show(Context context, String message, String btnYes, int yesColor, String btnNo, int noColor, final boolean isOutSide, final OnAlertClickListener onAlertClickListener) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert);
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
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            dialog.show();

            /*
            * VIEW
            */
            ConstraintLayout frame = dialog.findViewById(R.id.frame);
            TextView tvMessage = dialog.findViewById(R.id.messageAlert);
            TextView tvNo = dialog.findViewById(R.id.btnNo);
            TextView tvYes = dialog.findViewById(R.id.btnYes);

            frame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isOutSide)
                        dialog.dismiss();
                }
            });

            tvMessage.setText(message);

            if (btnNo == null || btnNo.equals("")) {
                tvNo.setVisibility(View.GONE);
                tvYes.setBackgroundResource(R.drawable.radius_blue_bottom);
            } else {
                tvNo.setText(btnNo);
                tvNo.setBackgroundColor(ContextCompat.getColor(context, noColor));
            }

            if (btnYes == null || btnYes.equals("")) {
                tvYes.setVisibility(View.GONE);
                tvNo.setBackgroundResource(R.drawable.radius_white_bottom);
            } else {
                tvYes.setText(btnYes);
                tvYes.setBackgroundColor(ContextCompat.getColor(context, yesColor));
            }

            tvNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (onAlertClickListener == null)
                        return;
                    onAlertClickListener.onNo();
                }
            });

            tvYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (onAlertClickListener == null)
                        return;
                    onAlertClickListener.onYes();
                }
            });

        }
    }

    public interface OnAlertClickListener {
        void onYes();
        void onNo();
    }
}
