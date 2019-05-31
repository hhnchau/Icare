package ptt.vn.icaremobileapp.utils;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.TextView;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.fragment.inpatient.Happening;
import ptt.vn.icaremobileapp.fragment.inpatient.HappeningFrame;
import ptt.vn.icaremobileapp.fragment.inpatient.Inpatient;
import ptt.vn.icaremobileapp.fragment.inpatient.Instruction;
import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;

public class Toolbaruz {
    public static void setToolbar(Context context, FragmentManager.BackStackEntry backStackEntry, TextView toolbarTile, ImageView toolbarLeft, ImageView toolbarRight) {
        String name = backStackEntry.getName();
        if (name != null) {
            if (name.equals(HappeningFrame.class.getName())) {
                toolbarTile.setText(context.getString(R.string.screen_inpatient_happening));
                toolbarLeft.setImageResource(R.drawable.ic_back);
                toolbarRight.setImageResource(0);
            } else if (name.equals(Instruction.class.getName())) {
                toolbarTile.setText(context.getString(R.string.screen_inpatient_instruction));
                toolbarLeft.setImageResource(R.drawable.ic_back);
                toolbarRight.setImageResource(0);
            }
        }
    }

    public static void setToolbar(Context context, Fragmentez fzg, TextView toolbarTile, ImageView toolbarLeft, ImageView toolbarRight) {
        switch (fzg) {
            case DASHBOARD:
                toolbarTile.setText(context.getString(R.string.screen_dashboard));
                toolbarLeft.setImageResource(R.drawable.ic_menu);
                toolbarRight.setImageResource(0);
                break;
            case INPATIENT:
                toolbarTile.setText(context.getString(R.string.screen_inpatient_list));
                toolbarLeft.setImageResource(R.drawable.ic_menu);
                toolbarRight.setImageResource(0);
                break;
            case RECEIVING:
                toolbarTile.setText(context.getString(R.string.screen_receiving));
                toolbarLeft.setImageResource(R.drawable.ic_menu);
                toolbarRight.setImageResource(R.drawable.ic_qr);
                break;
            case REGISTER:
                toolbarTile.setText(context.getString(R.string.screen_register));
                toolbarLeft.setImageResource(R.drawable.ic_menu);
                toolbarRight.setImageResource(0);
                break;
            case PATIENT_LIST:
                toolbarTile.setText(context.getString(R.string.screen_patient_list));
                toolbarLeft.setImageResource(R.drawable.ic_menu);
                toolbarRight.setImageResource(0);
                break;
        }
    }
}
