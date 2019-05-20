package ptt.vn.icaremobileapp.api;

import io.reactivex.Observable;
import ptt.vn.icaremobileapp.model.account.AccountResponse;
import ptt.vn.icaremobileapp.model.common.CateShareResponse;
import ptt.vn.icaremobileapp.model.hi.HiDomain;
import ptt.vn.icaremobileapp.model.hi.HiResponse;
import ptt.vn.icaremobileapp.model.icd.IcdResponse;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.HappeningResponse;
import ptt.vn.icaremobileapp.model.inpatient.HappeningSave;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientResponse;
import ptt.vn.icaremobileapp.model.medexa.MedexaHDomain;
import ptt.vn.icaremobileapp.model.medexa.MedexaResponse;
import ptt.vn.icaremobileapp.model.patient.PatientDomain;
import ptt.vn.icaremobileapp.model.patient.PatientResponse;
import ptt.vn.icaremobileapp.model.patient.PatientSave;
import ptt.vn.icaremobileapp.model.pharmacy.PhaInventoryResponse;
import ptt.vn.icaremobileapp.model.register.RegisterResponse;
import ptt.vn.icaremobileapp.model.serviceitem.MapPriceServiceItemResponse;
import ptt.vn.icaremobileapp.model.serviceitem.ServiceItemResponse;
import ptt.vn.icaremobileapp.model.sysapi.SysApiModel;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by kingpes on 8/18/18.
 */

public interface ApiService {

    @GET("/SysApiConfigService/1/0/0")
    Observable<SysApiModel> getSysApi();

    @GET()
    Observable<MedexaResponse> getMedexa(@Url String url);

    @GET()
    Observable<InpatientResponse> getInpatient(@Url String url, @Header("Content") String filterModel);

    @GET()
    Observable<PatientResponse> getPatientByPatId(@Url String url, @Header("Content") String filterModel);

    @GET()
    Observable<RegisterResponse> getRegisterByIdLink(@Url String url, @Header("Content") String filterModel);

    @GET()
    Observable<HappeningResponse> getHappening(@Url String url, @Header("Content") String filterModel);

    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST()
    Observable<HappeningSave> saveHappening(@Url String url, @Body HappeningDomain happening);

    @GET()
    Observable<ServiceItemResponse> getServiceItem(@Url String url, @Header("Content") String filterModel);

    @GET()
    Observable<PhaInventoryResponse> getPhaInventory(@Url String url, @Header("Content") String filterModel);

    @GET()
    Observable<IcdResponse> getIcd(@Url String url, @Header("Content") String filterModel);

    @GET()
    Observable<CateShareResponse> getCateShare(@Url String url, @Header("Content") String filterModel);

    @GET()
    Observable<MapPriceServiceItemResponse> getMapPriceServiceItem(@Url String url, @Header("Content") String filterModel);

    @GET()
    Observable<AccountResponse> login(@Url String url, @Header("Content") String filterModel);

    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST()
    Observable<HiResponse> getHiInfo(@Url String url, @Body HiDomain hiDomain);

    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST()
    Observable<PatientSave> saveReceiving(@Url String url, @Body PatientDomain patientDomain);




    @GET("/InpatientService/filter")
    Observable<Object> getTest(@Header("Content") String filterModel);


    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST("/PatientServices/executelist/GetNew")
    Observable<Object> getNew(@Body Object param);

    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST("/PatientServices/execute/Save")
    Observable<Object> savePatient(@Body Object form);

    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST("/PatientServices/executelist/GetList")
    Observable<Object> getList(@Body Object param);


    /*REGISTER*/
    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST("/RegisterServices/executelist/Get")
    Observable<Object> getRegister(@Body Object param);

    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST("/RegisterServices/executelist/GetLoadForm")
    Observable<Object> getLoadForm(@Body Object param);

    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST("/RegisterServices/executelist/GetDetail")
    Observable<Object> getDetail(@Body Object param);

    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST("/RegisterServices/execute/Save")
    Observable<Object> saveRegister(@Body Object register);


    /*HISTORY*/
    @GET()
    Observable<Object> getHistoryPatientOnHQ(@Url String url, @Header("intSiteRef") int intSiteRef, @Header("intPatID") int intPatID, @Header("strCMND") String strCMND, @Header("strBHYT") String strBHYT);

}







