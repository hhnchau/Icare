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
import ptt.vn.icaremobileapp.model.account.AccountResponse;
import ptt.vn.icaremobileapp.model.common.CateShareResponse;
import ptt.vn.icaremobileapp.model.common.CateSharehDomain;
import ptt.vn.icaremobileapp.model.filter.DataTypeOfValue;
import ptt.vn.icaremobileapp.model.filter.FieldName;
import ptt.vn.icaremobileapp.model.filter.FilterModel;
import ptt.vn.icaremobileapp.model.filter.Operation;
import ptt.vn.icaremobileapp.model.filter.Para;
import ptt.vn.icaremobileapp.model.icd.IcdResponse;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.HappeningResponse;
import ptt.vn.icaremobileapp.model.inpatient.HappeningSave;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientResponse;
import ptt.vn.icaremobileapp.model.filter.Service;
import ptt.vn.icaremobileapp.model.medexa.MedexaResponse;
import ptt.vn.icaremobileapp.model.patient.PatientResponse;
import ptt.vn.icaremobileapp.model.pharmacy.PhaInventoryResponse;
import ptt.vn.icaremobileapp.model.pharmacy.ResultResponse;
import ptt.vn.icaremobileapp.model.register.RegisterResponse;
import ptt.vn.icaremobileapp.model.serviceitem.MapPriceServiceItemResponse;
import ptt.vn.icaremobileapp.model.serviceitem.MapResultResponse;
import ptt.vn.icaremobileapp.model.serviceitem.ServiceItemResponse;
import ptt.vn.icaremobileapp.model.sysapi.SysApiModel;
import ptt.vn.icaremobileapp.model.sysapi.UrlModel;
import ptt.vn.icaremobileapp.utils.Constant;

import static ptt.vn.icaremobileapp.model.filter.Method.GetAccount;
import static ptt.vn.icaremobileapp.model.filter.Method.GetHappeningInDepartment;
import static ptt.vn.icaremobileapp.model.filter.Method.GetInpatientInDepartment;
import static ptt.vn.icaremobileapp.model.filter.Method.GetInvDrug;
import static ptt.vn.icaremobileapp.model.filter.Method.GetListPatient;
import static ptt.vn.icaremobileapp.model.filter.Method.GetPriceList;
import static ptt.vn.icaremobileapp.model.filter.Method.GetRegisterbyIdlink;


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
    public void getMedexa(final Context context, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.SPECIALIST);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        //Loading.getInstance().show(context);

        CompositeManager.add(Api.apiService.getMedexa(url + "0/0/0")
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MedexaResponse>() {
                    @Override
                    public void onNext(MedexaResponse response) {
                        if (response.getCode() == 0 && aCallback != null)
                            aCallback.response(response.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Loading.getInstance().hide();
                        MyLog.print(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //Loading.getInstance().hide();
                    }
                }));
    }

    @SuppressWarnings("unchecked")
    public void getInpatient(final Context context, final int _offset, final int _limit, final int idmedexa, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.INPATIENT);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        lstPara.add(new Para(FieldName.idmedexa, Operation.Equals, DataTypeOfValue.Int64, idmedexa));
        CompositeManager.add(Api.apiService.getInpatient(url + "filter", new FilterModel(_offset, _limit, GetInpatientInDepartment, lstPara).toString())
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
                                getInpatient(context, _offset, _limit, idmedexa, aCallback);
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
        //lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        //lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
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
    public void getRegisterByIdLink(final Context context, final List<InpatientDomain> lstInpatient, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.REG);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        //Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        //lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        //lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        for (InpatientDomain item : lstInpatient)
            lstPara.add(new Para(FieldName.idlink, Operation.Equals, DataTypeOfValue.Guid, item.getIdlink()));

        CompositeManager.add(Api.apiService.getRegisterByIdLink(url + "filter", new FilterModel(0, 10000, GetRegisterbyIdlink, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<RegisterResponse>() {

                    @Override
                    public void onNext(RegisterResponse response) {
                        if (response.getCode() == 0 && aCallback != null)
                            aCallback.response(response.getData());
                        else
                            MyLog.print(context, String.valueOf(response.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getRegisterByIdLink(context, lstInpatient, aCallback);
                            }
                        });

                        MyLog.print(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //Loading.getInstance().hide();
                    }
                }));
    }

    @SuppressWarnings("unchecked")
    public void getHappening(final Context context, final int _offset, final int _limit, final String idlink, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.INPATIENTHAPPENING);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        lstPara.add(new Para(FieldName.idlink, Operation.Equals, DataTypeOfValue.Guid, idlink));
        CompositeManager.add(Api.apiService.getHappening(url + "filter", new FilterModel(_offset, _limit, GetHappeningInDepartment, lstPara).toString())
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
                                getHappening(context, _offset, _limit, idlink, aCallback);
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

        //url = "http://172.16.0.21:7770/InpatientHappeningService/";

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

    @SuppressWarnings("unchecked")
    public void getServiceItem(final Context context, final int _offset, final int _limit, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.SERVITEM);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        CompositeManager.add(Api.apiService.getServiceItem(url + "filter", new FilterModel(_offset, _limit, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ServiceItemResponse>() {

                    @Override
                    public void onNext(ServiceItemResponse response) {
                        if (response.getCode() == 0 && aCallback != null)
                            aCallback.response(response.getData());
                        else
                            MyLog.print(context, String.valueOf(response.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getServiceItem(context, _offset, _limit, aCallback);
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
    public void getMapPriceServiceItem(final Context context, final int _offset, final int _limit, final int _idPrice, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.SERVITEM);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }


        //Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        lstPara.add(new Para(FieldName.id, Operation.Equals, DataTypeOfValue.Int64, _idPrice));
        CompositeManager.add(Api.apiService.getMapPriceServiceItem(url + "filter", new FilterModel(_offset, _limit, GetPriceList, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MapPriceServiceItemResponse>() {

                    @Override
                    public void onNext(MapPriceServiceItemResponse response) {
                        if (response.getCode() == 0 && aCallback != null) {
                            List<MapResultResponse> resultResponse = response.getData();
                            if (resultResponse != null && resultResponse.size() > 0)
                                aCallback.response(resultResponse.get(0).getResult().get(0).getLstPriceServiceValueObject());
                            else
                                MyLog.print(context, String.valueOf(response.getCode()));
                        } else
                            MyLog.print(context, String.valueOf(response.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getMapPriceServiceItem(context, _offset, _limit, _idPrice, aCallback);
                            }
                        });

                        MyLog.print(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //Loading.getInstance().hide();
                    }
                }));
    }


    @SuppressWarnings("unchecked")
    public void getPhaInventory(final Context context, final int _offset, final int _limit, final int _idStore, final int _isHi, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.OCLINIC);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }


        Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        lstPara.add(new Para(FieldName.idstore, Operation.Equals, DataTypeOfValue.Int64, _idStore));
        lstPara.add(new Para(FieldName.ishi, Operation.Equals, DataTypeOfValue.Int64, _isHi));
        CompositeManager.add(Api.apiService.getPhaInventory(url + "filter", new FilterModel(_offset, _limit, GetInvDrug, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<PhaInventoryResponse>() {

                    @Override
                    public void onNext(PhaInventoryResponse response) {
                        if (response.getCode() == 0 && aCallback != null) {
                            List<ResultResponse> resultResponse = response.getData();
                            if (resultResponse != null && resultResponse.size() > 0)
                                aCallback.response(resultResponse.get(0).getResult());
                            else
                                MyLog.print(context, String.valueOf(response.getCode()));
                        } else
                            MyLog.print(context, String.valueOf(response.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getPhaInventory(context, _offset, _limit, _idStore, _isHi, aCallback);
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
    public void getIcd(final Context context, final int _offset, final int _limit, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.ICD);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        CompositeManager.add(Api.apiService.getIcd(url + "filter", new FilterModel(_offset, _limit, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<IcdResponse>() {

                    @Override
                    public void onNext(IcdResponse response) {
                        if (response.getCode() == 0 && aCallback != null)
                            aCallback.response(response.getData());
                        else
                            MyLog.print(context, String.valueOf(response.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getIcd(context, _offset, _limit, aCallback);
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
    public void getDrugRoute(final Context context, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.CATE);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        //Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        //lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        //lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        lstPara.add(new Para(FieldName.routedrug, Operation.Equals, DataTypeOfValue.String, null));
        CompositeManager.add(Api.apiService.getCateShare(url + "filter", new FilterModel(lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CateShareResponse>() {

                    @Override
                    public void onNext(CateShareResponse response) {
                        if (response.getCode() == 0 && aCallback != null) {
                            List<CateSharehDomain> lstCateShareh = response.getData();
                            if (lstCateShareh != null && lstCateShareh.size() > 0)
                                aCallback.response(lstCateShareh.get(0).getLstCateShareDetail());
                        } else
                            MyLog.print(context, String.valueOf(response.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getDrugRoute(context, aCallback);
                            }
                        });

                        MyLog.print(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //Loading.getInstance().hide();
                    }
                }));
    }


    @SuppressWarnings("unchecked")
    public void getDrugUnitUse(final Context context, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.CATE);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        //Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        //lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        //lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        lstPara.add(new Para(FieldName.unitusedrug, Operation.Equals, DataTypeOfValue.String, null));
        CompositeManager.add(Api.apiService.getCateShare(url + "filter", new FilterModel(lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CateShareResponse>() {

                    @Override
                    public void onNext(CateShareResponse response) {
                        if (response.getCode() == 0 && aCallback != null) {
                            List<CateSharehDomain> lstCateShareh = response.getData();
                            if (lstCateShareh != null && lstCateShareh.size() > 0)
                                aCallback.response(lstCateShareh.get(0).getLstCateShareDetail());
                        } else
                            MyLog.print(context, String.valueOf(response.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getDrugUnitUse(context, aCallback);
                            }
                        });

                        MyLog.print(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //Loading.getInstance().hide();
                    }
                }));
    }


    @SuppressWarnings("unchecked")
    public void getHappeningType(final Context context, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.CATE);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        //Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        //lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        //lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        lstPara.add(new Para(FieldName.typemedicalchart, Operation.Equals, DataTypeOfValue.String, null));
        CompositeManager.add(Api.apiService.getCateShare(url + "filter", new FilterModel(lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CateShareResponse>() {

                    @Override
                    public void onNext(CateShareResponse response) {
                        if (response.getCode() == 0 && aCallback != null) {
                            List<CateSharehDomain> lstCateShareh = response.getData();
                            if (lstCateShareh != null && lstCateShareh.size() > 0)
                                aCallback.response(lstCateShareh.get(0).getLstCateShareDetail());
                        } else
                            MyLog.print(context, String.valueOf(response.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getDrugRoute(context, aCallback);
                            }
                        });

                        MyLog.print(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //Loading.getInstance().hide();
                    }
                }));
    }


    @SuppressWarnings("unchecked")
    public void getObject(final Context context, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.CATE);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        //Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        //lstPara.add(new Para(FieldName.siterf, Operation.Equals, DataTypeOfValue.Int64, Constant.SITERF));
        //lstPara.add(new Para(FieldName.active, Operation.Equals, DataTypeOfValue.Int64, Constant.ACTIVE));
        lstPara.add(new Para(FieldName.regobject, Operation.Equals, DataTypeOfValue.String, null));
        CompositeManager.add(Api.apiService.getCateShare(url + "filter", new FilterModel(lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CateShareResponse>() {

                    @Override
                    public void onNext(CateShareResponse response) {
                        if (response.getCode() == 0 && aCallback != null) {
                            List<CateSharehDomain> lstCateShareh = response.getData();
                            if (lstCateShareh != null && lstCateShareh.size() > 0)
                                aCallback.response(lstCateShareh.get(0).getLstCateShareDetail());
                        } else
                            MyLog.print(context, String.valueOf(response.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                getDrugRoute(context, aCallback);
                            }
                        });

                        MyLog.print(context, e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        //Loading.getInstance().hide();
                    }
                }));
    }


    @SuppressWarnings("unchecked")
    public void login(final Context context, final String user, final String pass, final ACallback aCallback) {
        String url = MyApplication.getUrl(Service.ACCOUNT);
        if (url == null) {
            Toast.makeText(context, context.getString(R.string.txt_service_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        Loading.getInstance().show(context);
        final List<Para> lstPara = new ArrayList<>();
        lstPara.add(new Para(FieldName.username, Operation.Equals, DataTypeOfValue.String, user));
        lstPara.add(new Para(FieldName.password, Operation.Equals, DataTypeOfValue.String, pass));
        CompositeManager.add(Api.apiService.login(url + "filter", new FilterModel(0, 1, GetAccount, lstPara).toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<AccountResponse>() {

                    @Override
                    public void onNext(AccountResponse response) {
                        if (response.getCode() == 0 && aCallback != null)
                            aCallback.response(response.getData());
                        else
                            MyLog.print(context, String.valueOf(response.getCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Loading.getInstance().hide();

                        connectAgain(context, new OnRetry() {
                            @Override
                            public void request() {
                                login(context, user, pass, aCallback);
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
        Alert.getInstance().show(context, context.getString(R.string.txt_not_connect), context.getString(R.string.btn_again), Alert.BLUE, null, 0, false, new Alert.OnAlertClickListener() {
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
