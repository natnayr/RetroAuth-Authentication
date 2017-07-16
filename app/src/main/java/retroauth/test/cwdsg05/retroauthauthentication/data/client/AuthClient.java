package retroauth.test.cwdsg05.retroauthauthentication.data.client;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retroauth.test.cwdsg05.retroauthauthentication.data.APIServices;
import retroauth.test.cwdsg05.retroauthauthentication.model.request.LoginRequest;
import retroauth.test.cwdsg05.retroauthauthentication.model.response.AuthResponse;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cwdsg05 on 16/7/17.
 */

public class AuthClient {

    private static final String LOG_TAG = AuthClient.class.getSimpleName();
    private static AuthClient instance;
    private APIServices apiServices;
    private Retrofit retrofit;

    public AuthClient(){
        final Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();

        //Http Inteceptor
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .baseUrl(APIServices.API_LIVE_BASE_URL + APIServices.LIVE_STAGE)
                .build();

        this.apiServices = retrofit.create(APIServices.class);
    }


    public static AuthClient getInstance() {
        if (instance == null) {
            instance = new AuthClient();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public Observable<Response<AuthResponse>> loginUser(String email, String password, String deviceID) {

        //no token needed thus Retrofit instance can be built at constructor

        return apiServices.postLoginUser(new LoginRequest(email, password, deviceID));
    }
}
