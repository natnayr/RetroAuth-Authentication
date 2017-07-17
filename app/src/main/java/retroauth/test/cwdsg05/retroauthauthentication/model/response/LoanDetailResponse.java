package retroauth.test.cwdsg05.retroauthauthentication.model.response;

/**
 * Created by cwdsg05 on 18/11/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retroauth.test.cwdsg05.retroauthauthentication.model.Loan;
import retroauth.test.cwdsg05.retroauthauthentication.model.Server;

public class LoanDetailResponse {

    @SerializedName("server")
    @Expose
    private Server server;

    @SerializedName("loan")
    @Expose
    private Loan loan;

    @SerializedName("first_repayment")
    @Expose
    private String firstRepayment;

    @SerializedName("last_repayment")
    @Expose
    private String lastRepayment;

    public Server getServer(){
        return server;
    }

    public void setServer(Server server){
        this.server = server;
    }

    public Loan getLoan(){
        return loan;
    }

    public void setLoan(Loan loan){
        this.loan = loan;
    }

    public String getFirstRepayment() {
        return firstRepayment;
    }

    public void setFirstRepayment(String firstRepayment) {
        this.firstRepayment = firstRepayment;
    }

    public String getLastRepayment() {
        return lastRepayment;
    }

    public void setLastRepayment(String lastRepayment) {
        this.lastRepayment = lastRepayment;
    }

}
