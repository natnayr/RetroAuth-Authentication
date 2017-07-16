package retroauth.test.cwdsg05.retroauthauthentication.data.client;

import android.content.Context;

import com.andretietz.retroauth.AndroidAuthenticationHandler;
import com.andretietz.retroauth.AndroidTokenType;
import com.andretietz.retroauth.Retroauth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retroauth.test.cwdsg05.retroauthauthentication.data.APIServices;
import retroauth.test.cwdsg05.retroauthauthentication.model.response.LoanListResponse;
import retroauth.test.cwdsg05.retroauthauthentication.oauth.RetroAuthProvider;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cwdsg05 on 16/7/17.
 */

public class LoanClient {

    private static final String LOG_TAG = LoanClient.class.getSimpleName();

    private static LoanClient instance;
    private Retrofit retrofit;
    private APIServices apiServices;


    public LoanClient(){
        final Gson gson = new GsonBuilder().serializeNulls().create();

//        //Http Inteceptor
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


        RetroAuthProvider provider = new RetroAuthProvider();

        retrofit = new Retroauth.Builder<>(AndroidAuthenticationHandler.create(provider,
                AndroidTokenType.Factory.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient)
                .baseUrl(APIServices.API_LIVE_BASE_URL + APIServices.LIVE_STAGE)
                .build();

        this.apiServices = retrofit.create(APIServices.class);
    }

    public Retrofit getRetrofit(){ return retrofit; }

    public static LoanClient getInstance(){
        if(instance == null){
            instance = new LoanClient();
        }
        return instance;
    }

    public Observable<Response<LoanListResponse>> getLiveLoans(String deviceId){
        return apiServices.getLoansList(deviceId, APIServices.API_SITE_CONFIG_ID);
    }
}
