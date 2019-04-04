package ptt.vn.icaremobileapp.api;

import android.content.Context;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.alert.Alert;
import ptt.vn.icaremobileapp.loading.Loading;
import ptt.vn.icaremobileapp.log.MyLog;
import ptt.vn.icaremobileapp.model.filter.FilterModel;
import ptt.vn.icaremobileapp.model.filter.Method;
import ptt.vn.icaremobileapp.model.filter.Para;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.inpatient.ResultInpatient;


/**
 * Created by kingpes on 8/18/18.
 */
public class ApiController {

    private static ApiController instance = null;

    public static ApiController getInstance() {
        if (instance == null) {
            instance = new ApiController();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public void getFilter(final Context context, final int _offset, final int _limit, final Method method, final List<Para> lstPara, final ACallback aCallback) {
        Loading.getInstance().show(context);
        CompositeManager.add(Api.apiService.getFilter(new FilterModel(_offset, _limit, method, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ResultInpatient>() {

                    @Override
                    public void onNext(ResultInpatient result) {
                        if (result != null)
                            if (result.getCode() == 0 && aCallback != null)
                                aCallback.response((List<Object>) (List) result.getData());
                            else
                                MyLog.print(context, String.valueOf(result.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getFilter(context, _offset, _limit, method, lstPara, aCallback);
                            }
                        });

                        MyLog.print(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Loading.getInstance().hide();
                    }
                }));
    }


    /**
     * Save
     */
    public void save(final Context context, final InpatientDomain obj, final ACallback aCallback) {
        String url = Host.URL + "/InpatientService/";
        Loading.getInstance().show(context);
        CompositeManager.add(Api.apiService.save(url, obj)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Object>() {

                    @Override
                    public void onNext(Object result) {
                        if (result != null) ;

                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {

                            }
                        });

                        MyLog.print(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Loading.getInstance().hide();
                    }
                }));

    }

    private void connectAgain(Context context, final OnRetry onRetry) {
        Alert.getInstance().show(context, context.getString(R.string.txt_not_connect), context.getString(R.string.btn_yes), null, false, new Alert.OnAlertClickListener() {
            @Override
            public void onYes() {
                onRetry.request();
            }

            @Override
            public void onNo() {

            }
        });
    }

}
