package mainpackage.utils.model;

public class Bill {
	private String usernameOfClient;
    private int billingMonth; // ο μήνας χρέωσης
    private int numberOfCalls;
    private final String bill_id;
    
    public Bill(String bill_id, String usernameOfClient, int billingMonth, int numberOfCalls) {
    	this.bill_id =  bill_id;
    	setUsernameOfClient(usernameOfClient);
    	setBillingMonth(billingMonth);
    	setNumberOfCalls(numberOfCalls);
    }


	public int getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(int billingMonth) {
        this.billingMonth = billingMonth;
    }

   
	public String getUsernameOfClient() {
		return usernameOfClient;
	}

	public void setUsernameOfClient(String usernameOfClient) {
		this.usernameOfClient = usernameOfClient;
	}


	public String getBill_id() {
		return bill_id;
	}


	public int getNumberOfCalls() {
		return numberOfCalls;
	}


	public void setNumberOfCalls(int numberOfCalls) {
		this.numberOfCalls = numberOfCalls;
	}

    // Άλλες μέθοδοι για τον υπολογισμό του συνολικού κόστους κλπ μπορούν να προστεθούν εδώ
}
