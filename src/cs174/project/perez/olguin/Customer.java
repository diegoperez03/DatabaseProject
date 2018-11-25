package cs174.project.perez.olguin;

import java.util.ArrayList;

public class Customer {
    String taxId;
    String name;
    String address;
    String pin;

    public  Customer(String id, String nameCustomer, String addressCustomer,String pinNumber){
        this.taxId = id;
        this.name=nameCustomer;
        this.address = addressCustomer;
        this.pin = pinNumber;

    }
}
