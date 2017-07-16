package retroauth.test.cwdsg05.retroauthauthentication.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retroauth.test.cwdsg05.retroauthauthentication.model.Loan;
import retroauth.test.cwdsg05.retroauthauthentication.model.Server;

/**
 * Created by cwdsg05 on 16/7/17.
 */

public class LoanListResponse {
    @SerializedName("server")
    @Expose
    public Server server;

    @SerializedName("loans")
    @Expose
    public List<Loan> loans;

}
