package mainpackage.utils.model;

public class Bill {
	private String usernameOfClient;
    private String billingMonth; // ο μήνας χρέωσης
    private PhoneNumber phoneNumber;
    
    public Bill(String usernameOfClient, String billingMonth, PhoneNumber phoneNumber) {
    	setUsernameOfClient(usernameOfClient);
    	setBillingMonth(billingMonth);
    	setPhoneNumber(phoneNumber);
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

	public String getUsernameOfClient() {
		return usernameOfClient;
	}

	public void setUsernameOfClient(String usernameOfClient) {
		this.usernameOfClient = usernameOfClient;
	}

    // Άλλες μέθοδοι για τον υπολογισμό του συνολικού κόστους κλπ μπορούν να προστεθούν εδώ
}
