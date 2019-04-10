package ptt.vn.icaremobileapp.application;

import android.app.Application;

import java.util.Map;
import java.util.Objects;

import ptt.vn.icaremobileapp.api.Api;
import ptt.vn.icaremobileapp.api.ApiController;
import ptt.vn.icaremobileapp.model.sysapi.UrlModel;


/**
 * Created by kingpes on 8/18/18.
 */
public class MyApplication extends Application {
    private static Map<String, UrlModel> urlModelMap;

    public Map<String, UrlModel> getUrlModelMap() {
        return urlModelMap;
    }

    public void setUrlModelMap(Map<String, UrlModel> map) {
        urlModelMap = map;
    }

    public static String getUrl(String key) {
        if (urlModelMap != null && urlModelMap.get(key) != null) {
            return Objects.requireNonNull(urlModelMap.get(key)).getFullpath();
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Api.getInstance().create();


        ApiController.getInstance().getSysApi(this);

    }
}
