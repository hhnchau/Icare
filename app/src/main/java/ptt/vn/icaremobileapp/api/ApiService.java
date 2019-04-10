package ptt.vn.icaremobileapp.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import ptt.vn.icaremobileapp.model.RestResult;
import ptt.vn.icaremobileapp.model.inpatient.HappeningDomain;
import ptt.vn.icaremobileapp.model.inpatient.HappeningResponse;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.inpatient.InpatientResponse;
import ptt.vn.icaremobileapp.model.patient.PatientResponse;
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
    Observable<InpatientResponse> getInpatient(@Url String url, @Header("Content") String filterModel);

    @GET()
    Observable<PatientResponse> getPatientByPatId(@Url String url, @Header("Content") String filterModel);

    @GET()
    Observable<HappeningResponse> getHappening(@Url String url, @Header("Content") String filterModel);

    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST()
    Observable<ResponseBody> saveHappening(@Url String url, @Body HappeningDomain happening);








    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST()
    Observable<Object> save(@Url String url, @Body InpatientDomain form);


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







