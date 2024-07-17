package mainpackage.users.model;

import mainpackage.utils.model.PhoneNumber;

public class Client extends User {
    private final String AFM;
    private String phoneNumber;
    private Double balance;
    public Client(String username, String name, String surname, String password, int role, String AFM, Double balance, String phoneNumber) {
        super(username, name, surname, password, role);
        this.AFM = AFM;
        this.phoneNumber = phoneNumber;
        this.balance = balance; // Αρχικό υπόλοιπο λογαριασμού
    }

    public String getAFM() {
        return AFM;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
   
    
   
}
