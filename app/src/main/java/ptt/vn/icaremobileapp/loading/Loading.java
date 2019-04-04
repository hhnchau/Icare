package ptt.vn.icaremobileapp.loading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.image.Image;


/**
 * Created by kingpes on 9/6/18.
 */

public class Loading {
    private static Loading instance = null;

    public static Loading getInstance(){
        if (instance == null){
            instance = new Loading();
        }
        return instance;
    }

    private Dialog dialog;

    public void show(final Context context){
        if (context != null){
            dialog = new Dialog(context, R.style.MyLoading);
            dialog.setOwnerActivity((Activity)context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.loading);
            dialog.setCancelable(false);

            Window window = dialog.getWindow();
            if (window != null){
                WindowManager.LayoutParams wlp = window.getAttributes();
                //wlp.windowAnimations = R.style.DialogSlideUp;
                wlp.gravity = Gravity.CENTER;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.show();

                 /*
                 * View
                */
                ImageView imageView = dialog.findViewById(R.id.imgLoading);
                Image.getInstance().load(R.raw.loading, imageView);

            }

        }
    }

    public void hide(){
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
