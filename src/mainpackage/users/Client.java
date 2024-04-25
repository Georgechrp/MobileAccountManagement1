package mainpackage.users;

import mainpackage.calls.Call;
import mainpackage.utils.PhoneNumber;

import java.util.ArrayList;

public class Client extends User {
    private final String AFM;
    private PhoneNumber phoneNumber;
    private double balance;
    private ArrayList<Call> callHistory;
    public Client(String username, String name, String surname, String role, String AFM, PhoneNumber phoneNumber) {
        super(username, name, surname, role);
        this.AFM = AFM;
        this.phoneNumber = phoneNumber;
        this.balance = 0.0; // Αρχικό υπόλοιπο λογαριασμού
        this.callHistory = new ArrayList<>();

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public ArrayList<Call> getCallHistory() {
        return callHistory;
    }
    // Προσθήκη ενός τηλεφωνικού κλήση στο ιστορικό
    public void addCall(Call callDetails) {
        callHistory.add(callDetails);
    }
    @Override
    public void register() {
        super.register();
        System.out.println("ΑΦΜ: " + AFM);
        System.out.println("Τηλέφωνο: " + phoneNumber.getNumber());
    }

    // Εμφάνιση λογαριασμού
    public void displayAccount() {
        System.out.println("Όνομα: " + getName() + " " + getSurname());
        System.out.println("Τηλέφωνο: " + phoneNumber.getNumber());
        System.out.println("ΑΦΜ: " + AFM);
        System.out.println("Υπόλοιπο λογαριασμού: " + balance);
    }

    // Εκτύπωση του ιστορικού των κλήσεων
    public void displayCallHistory() {
        System.out.println("Ιστορικό κλήσεων:");
        for (Call call : callHistory) {
            System.out.println(call);
        }
    }

    // Μέθοδος εξόφλησης λογαριασμού
    public void payBill(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Επιτυχής εξόφληση. Νέο υπόλοιπο: " + balance);
        } else {
            System.out.println("Η εξόφληση απέτυχε ή το ποσό είναι μη έγκυρο.");
        }
    }
}
