package ptt.vn.icaremobileapp.api;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.alert.Alert;
import ptt.vn.icaremobileapp.application.MyApplication;
import ptt.vn.icaremobileapp.loading.Loading;
import ptt.vn.icaremobileapp.log.MyLog;
import ptt.vn.icaremobileapp.model.BaseResult;
import ptt.vn.icaremobileapp.model.filter.DataTypeOfValue;
import ptt.vn.icaremobileapp.model.filter.FieldName;
import ptt.vn.icaremobileapp.model.filter.FilterModel;
import ptt.vn.icaremobileapp.model.filter.Method;
import ptt.vn.icaremobileapp.model.filter.Operation;
import ptt.vn.icaremobileapp.model.filter.Para;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.HappeningResponse;
import ptt.vn.icaremobileapp.model.inpatient.HappeningSave;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientResponse;
import ptt.vn.icaremobileapp.model.filter.Service;
import ptt.vn.icaremobileapp.model.patient.PatientResponse;
import ptt.vn.icaremobileapp.model.sysapi.SysApiModel;
import ptt.vn.icaremobileapp.model.sysapi.UrlModel;

import static ptt.vn.icaremobileapp.model.filter.Method.GetListPatient;


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

    public void getSysApi(final Context context) {
        CompositeManager.add(Api.apiService.getSysApi()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SysApiModel>() {
                    @Override
                    public void onNext(SysApiModel dataModel) {
                        List<UrlModel> lst = dataModel.getData();
                        Map<String, UrlModel> map = new HashMap<>();
                        for (UrlModel i : lst) {
                            for (UrlModel u : i.getLstApiValueObject()) {
                                map.put(u.getCode(), u);
                            }
                        }
                        ((MyApplication) context).setUrlModelMap(map);
                    }

                    @Override
                    public void onError(Throwable e) {
                        MyLog.print(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    @SuppressWarnings("unchecked")
    public void getInpatient(final Context context, final int _offset, final int _limit, final Method method, final int idmedexa, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.INPATIENT);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Host.SITERF));
        lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Host.SITERF));
        lstPara.add(new Para(FieldName.idmedexa, Operation.Equals, DataTypeOfValue.Int64, idmedexa));
        CompositeManager.add(Api.apiService.getInpatient(url + "filter", new FilterModel(_offset, _limit, method, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<InpatientResponse>() {

                    @Override
                    public void onNext(InpatientResponse inpatientModel) {
                        if (inpatientModel.getCode() == 0 && aCallback != null)
                            aCallback.response(inpatientModel.getData());
                        else
                            MyLog.print(context, String.valueOf(inpatientModel.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getInpatient(context, _offset, _limit, method, idmedexa, aCallback);
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

    @SuppressWarnings("unchecked")
    public void getPatientByPatId(final Context context, final List<InpatientDomain> lstInpatient, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.PAT);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }


        //Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        //lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Host.SITERF));
        //lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Host.SITERF));
        for (InpatientDomain item : lstInpatient)
            lstPara.add(new Para(FieldName.patid, Operation.Equals, DataTypeOfValue.Guid, item.getPatid()));
        CompositeManager.add(Api.apiService.getPatientByPatId(url + "filter", new FilterModel(0, 10000, GetListPatient, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<PatientResponse>() {

                    @Override
                    public void onNext(PatientResponse patientModel) {
                        if (patientModel.getCode() == 0 && aCallback != null)
                            aCallback.response(patientModel.getData());
                        else
                            MyLog.print(context, String.valueOf(patientModel.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getPatientByPatId(context, lstInpatient, aCallback);
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

    @SuppressWarnings("unchecked")
    public void getRegisterByIdLink(final Context context, final int _offset, final int _limit, final Method method, final int idmedexa, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.REG);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Host.SITERF));
        lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Host.SITERF));
        lstPara.add(new Para(FieldName.idmedexa, Operation.Equals, DataTypeOfValue.Int64, idmedexa));
        CompositeManager.add(Api.apiService.getInpatient(url + "filter", new FilterModel(_offset, _limit, method, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<InpatientResponse>() {

                    @Override
                    public void onNext(InpatientResponse inpatientModel) {
                        if (inpatientModel.getCode() == 0 && aCallback != null)
                            aCallback.response(inpatientModel.getData());
                        else
                            MyLog.print(context, String.valueOf(inpatientModel.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getInpatient(context, _offset, _limit, method, idmedexa, aCallback);
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

    @SuppressWarnings("unchecked")
    public void getHappening(final Context context, final int _offset, final int _limit, final Method method, final String idlink, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.INPATIENTHAPPENING);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Host.SITERF));
        lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Host.SITERF));
        lstPara.add(new Para(FieldName.idlink, Operation.Equals, DataTypeOfValue.Guid, idlink));
        CompositeManager.add(Api.apiService.getHappening(url + "filter", new FilterModel(_offset, _limit, method, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<HappeningResponse>() {

                    @Override
                    public void onNext(HappeningResponse happeningResponse) {
                        if (happeningResponse.getCode() == 0 && aCallback != null)
                            aCallback.response(happeningResponse.getData());
                        else
                            MyLog.print(context, String.valueOf(happeningResponse.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getHappening(context, _offset, _limit, method, idlink, aCallback);
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

    @SuppressWarnings("unchecked")
    public void saveHappening(final Context context, final HappeningDomain happening, final Callback callback) {
        String url = MyApplication.getUrl(Service.INPATIENTHAPPENING);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.getInstance().show(context);

        CompositeManager.add(Api.apiService.saveHappening(url, happening)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<HappeningSave>() {

                    @Override
                    public void onNext(HappeningSave save) {
                        if (save.getCode() == 0 && callback != null)
                            callback.response(save.getData());
                        else
                            MyLog.print(context, String.valueOf(save.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                saveHappening(context, happening, callback);
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


    @SuppressWarnings("unchecked")
    public void deleteHappening(final Context context, final HappeningDomain happening, final Callback callback) {
        String url = MyApplication.getUrl(Service.INPATIENTHAPPENING);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.getInstance().show(context);

        CompositeManager.add(Api.apiService.saveHappening(url, happening)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<HappeningSave>() {

                    @Override
                    public void onNext(HappeningSave save) {
                        if (save.getCode() == 0 && callback != null)
                            callback.response(happening);
                        else
                            MyLog.print(context, String.valueOf(save.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                saveHappening(context, happening, callback);
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
