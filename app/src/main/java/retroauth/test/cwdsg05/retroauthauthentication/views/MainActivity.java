package retroauth.test.cwdsg05.retroauthauthentication.views;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andretietz.retroauth.AuthAccountManager;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retroauth.test.cwdsg05.retroauthauthentication.R;
import retroauth.test.cwdsg05.retroauthauthentication.data.client.LoanClient;
import retroauth.test.cwdsg05.retroauthauthentication.model.response.LoanDetailResponse;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_center_display) TextView mCenterDisplay;
    @BindView(R.id.main_invalid_token) Button mInvalidButton;
    @BindView(R.id.main_pull_data) Button mPullData;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private AuthAccountManager authAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authAccountManager = new AuthAccountManager();

        ButterKnife.bind(this);
        mPullData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoanClient.getInstance()
                        .getLoanDetails(5180, getUniqueAndroidID(MainActivity.this))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Response<LoanDetailResponse>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) { }

                            @Override
                            public void onNext(@NonNull Response<LoanDetailResponse> response) {
                                if(response.isSuccessful()){
                                    LoanDetailResponse loanDetailResponse = response.body();
                                    mCenterDisplay.setText(loanDetailResponse.getLoan().getLoanId());
                                }else{
                                    if(response.code() >= 400){
                                        mCenterDisplay.setText(response.message());

                                    }
                                }
                                Toast.makeText(MainActivity.this, response.body().getServer().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                Log.e(LOG_TAG, "ERROR: " + e.getMessage(), e);
                                Toast.makeText(MainActivity.this, e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onComplete() {
                                Log.d(LOG_TAG, "APP getLoanDetails Rx onComplete");
                            }
                        });
            }
        });


        mInvalidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account activeAccount = authAccountManager
                        .getActiveAccount(getString(R.string.authentication_ACCOUNT));
                if(activeAccount != null) {
                    authAccountManager.removeActiveAccount(getString(R.string.authentication_ACCOUNT));
                }
            }
        });
    }

    public static String getUniqueAndroidID(Context context){
        return Build.SERIAL + Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }


}
