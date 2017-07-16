package retroauth.test.cwdsg05.retroauthauthentication.oauth;

import android.content.Context;

import com.andretietz.retroauth.AuthenticationService;

import retroauth.test.cwdsg05.retroauthauthentication.R;

/**
 * Created by cwdsg05 on 24/3/17.
 */

public class RetroAuthService extends AuthenticationService {

    @Override
    public String getLoginAction(Context context) {
        return context.getString(R.string.authentication_ACTION);
    }
}
