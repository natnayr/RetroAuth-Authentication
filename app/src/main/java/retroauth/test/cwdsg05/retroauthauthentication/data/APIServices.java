package retroauth.test.cwdsg05.retroauthauthentication.data;

import com.andretietz.retroauth.Authenticated;

import io.reactivex.Observable;
import retroauth.test.cwdsg05.retroauthauthentication.model.request.LoginRequest;
import retroauth.test.cwdsg05.retroauthauthentication.model.response.AuthResponse;
import retroauth.test.cwdsg05.retroauthauthentication.model.response.LoanListResponse;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by cwdsg05 on 16/7/17.
 */

public interface APIServices {

    public static final String API_LIVE_BASE_URL = "http://192.168.1.9:3000/";
    public static final String LIVE_STAGE = "api/v2/";
    public static final String LIVE_DOCS = "docs";

    public static final String API_SITE_CONFIG_SG = "sg";
    public static final String API_SITE_CONFIG_ID = "id";

    @POST("oauth/login")
    @Headers({"Content-type: application/json"})
    Observable<Response<AuthResponse>> postLoginUser(@Body LoginRequest data);

    @Authenticated
    @GET("loans/loan_listing")
    @Headers({"Content-type: application/json"})
    Observable<Response<LoanListResponse>> getLoansList(@Query("device_id") String deviceId, @Query("site_config") String siteConfig);


}
