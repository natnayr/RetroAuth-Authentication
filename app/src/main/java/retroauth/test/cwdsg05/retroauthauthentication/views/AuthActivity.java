package retroauth.test.cwdsg05.retroauthauthentication.views;

import android.accounts.Account;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andretietz.retroauth.AuthenticationActivity;

import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retroauth.test.cwdsg05.retroauthauthentication.HashingUtils;
import retroauth.test.cwdsg05.retroauthauthentication.R;
import retroauth.test.cwdsg05.retroauthauthentication.data.client.AuthClient;
import retroauth.test.cwdsg05.retroauthauthentication.model.response.AuthResponse;
import retrofit2.Response;

/**
 * Created by cwdsg05 on 16/7/17.
 */

public class AuthActivity extends AuthenticationActivity implements Observer<Response<AuthResponse>> {

    @BindView(R.id.auth_login_email_edittext) public AppCompatEditText mLoginEmailEditText;
    @BindView(R.id.auth_login_password_edittext) public AppCompatEditText mLoginPasswdEditText;
    @BindView(R.id.auth_login_submit_btn) public LinearLayout mLoginSubmitButton;

    public final static String AUTH_MEMBER_EMAIL = "AUTH_MEMBER_EMAIL";
    public final static String AUTH_MEMBER_NAME = "AUTH_MEMBER_NAME";
    public final static String AUTH_MEMBER_TOKEN = "AUTH_MEMBER_TOKEN";
    public final static String AUTH_MEMBER_LOCALE = "AUTH_MEMBER_LOCALE";

    private final static String LOG_TAG = AuthActivity.class.getSimpleName();
    private String mPasswordHash;
    private Disposable disposableLoginUser;
    private AuthResponse authResponse;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_auth);

        ButterKnife.bind(this);

        mLoginSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    private void submit() {
        final String inputEmail = mLoginEmailEditText.getText().toString().trim();
        final String inputPassword = mLoginPasswdEditText.getText().toString().trim();

        //store password
        mPasswordHash = HashingUtils.hashSHA256(inputPassword);

        //do http call
        AuthClient authClient = AuthClient.getInstance();
        authClient.loginUser(inputEmail, inputPassword, MainActivity.getUniqueAndroidID(this))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this);
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        disposableLoginUser = d;
    }

    @Override
    public void onNext(@NonNull Response<AuthResponse> response) {
        if(response.isSuccessful()) {
            authResponse = response.body();
            Toast.makeText(this, "APP " + authResponse.getServer().getMessage() + " " +
                            authResponse.getMember().getName() +
                            " Token: " + authResponse.getAuthToken(),
                    Toast.LENGTH_LONG).show();

            final String token = authResponse.getAuthToken();
            final String user = authResponse.getMember().getName();
            final String email = authResponse.getMember().getEmail();

            if(token != null) {
                Account account = createOrGetAccount(user);
                storeToken(account, getRequestedTokenType(), token);
                storeUserData(account, getString(R.string.authentication_EMAIL), email);
                finalizeAuthentication(account);
            }


        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.e(LOG_TAG, e.getMessage(), e);
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onComplete() {
        Log.e(LOG_TAG, "APP onComplete");
    }

    @Override
    public void finish() {
        super.finish();
    }
}
