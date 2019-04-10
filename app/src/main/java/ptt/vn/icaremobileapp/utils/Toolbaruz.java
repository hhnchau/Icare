package ptt.vn.icaremobileapp.utils;

import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.TextView;

import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.enums.Fragmentez;

public class Toolbaruz {
    public static void setToolbar(FragmentManager.BackStackEntry backStackEntry, TextView toolbarTile, ImageView toolbarLeft, ImageView toolbarRight) {
        String name = backStackEntry.getName();
        if (name != null) {
            if (name.equals(Fragmentez.INPATIENT_LIST.name())) {
                toolbarTile.setText("DS Noi Tru");
                toolbarLeft.setImageResource(R.drawable.ic_menu);
                toolbarRight.setImageResource(R.drawable.ic_uncheck);
            } else if (name.equals(Fragmentez.HAPPENING.name())) {
                toolbarTile.setText("Dien Bien");
                toolbarLeft.setImageResource(R.drawable.ic_back);
                toolbarRight.setImageResource(0);
            } else if (name.equals(Fragmentez.INSTRUCTION.name())) {
                toolbarTile.setText("Y Lenh");
                toolbarLeft.setImageResource(R.drawable.ic_back);
                toolbarRight.setImageResource(0);
            } else if (name.equals(Fragmentez.DASHBOARD.name())) {
                toolbarTile.setText("Dashboard");
                toolbarLeft.setImageResource(R.drawable.ic_menu);
                toolbarRight.setImageResource(0);
            }
        }
    }
}
