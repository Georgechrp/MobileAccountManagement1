package mainpackage.users.model;

import mainpackage.utils.model.PhoneNumber;

public class Client extends User {
    private final String AFM;
    private PhoneNumber phoneNumber;
    private Double balance;
    public Client(String username, String name, String surname, String password, int role, String AFM, Double balance, PhoneNumber phoneNumber) {
        super(username, name, surname, password, role);
        this.AFM = AFM;
        setPhoneNumber(phoneNumber);
        this.balance = balance; // Αρχικό υπόλοιπο λογαριασμού
    }

    public String getAFM() {
        return AFM;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}