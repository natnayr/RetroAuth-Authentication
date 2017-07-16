package retroauth.test.cwdsg05.retroauthauthentication.views;

import android.os.Bundle;

import com.andretietz.retroauth.AuthenticationActivity;

import retroauth.test.cwdsg05.retroauthauthentication.R;

/**
 * Created by cwdsg05 on 16/7/17.
 */

public class AuthActivity extends AuthenticationActivity {

    public final static String AUTH_MEMBER_EMAIL = "AUTH_MEMBER_EMAIL";
    public final static String AUTH_MEMBER_NAME = "AUTH_MEMBER_NAME";
    public final static String AUTH_MEMBER_TOKEN = "AUTH_MEMBER_TOKEN";
    public final static String AUTH_MEMBER_LOCALE = "AUTH_MEMBER_LOCALE";


    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_auth);



    }



}
