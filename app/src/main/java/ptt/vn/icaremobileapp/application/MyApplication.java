package ptt.vn.icaremobileapp.application;

import android.app.Application;
import ptt.vn.icaremobileapp.api.Api;


/**
 * Created by kingpes on 8/18/18.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Api.getInstance().create();

    }
}
