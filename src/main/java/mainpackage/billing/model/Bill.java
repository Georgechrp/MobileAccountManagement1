package mainpackage.billing.model;

import mainpackage.calls.model.Call;
import mainpackage.utils.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
public class Bill {
    private String billingMonth; // ο μήνας χρέωσης
    private PhoneNumber phoneNumber;
    private List<Call> calls;//μια λίστα με τις κλήσεις που αντιστοιχούν στον συγκεκριμένο αριθμό τηλεφώνου για τον συγκεκριμένο μήνα χρέωσης.

    public Bill(String billingMonth, PhoneNumber phoneNumber) {
        this.billingMonth = billingMonth;
        this.phoneNumber = phoneNumber;
        this.calls = new ArrayList<>();
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(String billingMonth) {
        this.billingMonth = billingMonth;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void addCall(Call call) {
        calls.add(call);
    }

    // Άλλες μέθοδοι για τον υπολογισμό του συνολικού κόστους κλπ μπορούν να προστεθούν εδώ
}
