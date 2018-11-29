package cs174.project.perez.olguin;

public class Account {
    String acctId;
    String acctType;
    String bankName;
    Double interest;
    String closed;
    String taxId;

    public  Account(String id, String type, String bankname , Double interest, String closed , String customerId){
        this.acctId = id;
        this.acctType = type;
        this.bankName = bankname;
        this.interest = interest;
        this.closed = closed;
        this.taxId = customerId;
    }
}
