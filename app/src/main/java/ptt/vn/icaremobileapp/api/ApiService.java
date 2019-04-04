package ptt.vn.icaremobileapp.api;

import io.reactivex.Observable;
import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;
import ptt.vn.icaremobileapp.model.inpatient.ResultInpatient;
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

    @GET("/InpatientService/filter")
    Observable<ResultInpatient> getFilter(@Header("Content") String filterModel);

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







