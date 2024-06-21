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