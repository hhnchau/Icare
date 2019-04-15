package ptt.vn.icaremobileapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Chau Huynh on 09/02/02017.
 */

public class Storage {
    private static Storage instance;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public static Storage getInstance(Context context) {
        if (instance == null) {
            instance = new Storage();
            preferences = context.getSharedPreferences("Storage", MODE_PRIVATE);
            editor = preferences.edit();
            editor.apply();
        }
        return instance;
    }

    public void setUserName(String userName) {
        editor.putString("user-name", userName);
        editor.commit();
    }

    public String getUserName() {
        return preferences.getString("user-name", "");
    }


}
