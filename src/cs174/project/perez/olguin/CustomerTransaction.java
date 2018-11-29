package cs174.project.perez.olguin;

import oracle.sql.TIMESTAMP;

import java.util.Date;

public class CustomerTransaction {
    String acctId;
    String acctType;
    String transcType;
    Double amount;
    String closed;
    TIMESTAMP date;

    public CustomerTransaction(String id, String accountType,
                               String transactionType, Double Amount,
                               String closedAccount, TIMESTAMP time ){
        this.acctId  =id;
        this.acctType = accountType;
        this.transcType = transactionType;
        this.amount = Amount;
        this.closed = closedAccount;
        this.date = time;
    }


}
