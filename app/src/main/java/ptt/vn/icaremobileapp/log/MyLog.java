package ptt.vn.icaremobileapp.log;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kingpes on 8/18/18.
 */

public class MyLog {
    private static final String TAG = "LOG --> ";

    public static void print(Context context, String s) {
        boolean log = true;
        if (log) {
            Log.d(TAG, s);
        }
        //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}


