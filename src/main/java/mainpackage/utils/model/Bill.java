package mainpackage.utils.model;

public class Bill {
	private String usernameOfClient;
    private int billingMonth; // ΞΏ ΞΌΞ®Ξ½Ξ±Ο‚ Ο‡ΟΞ­Ο‰ΟƒΞ·Ο‚
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

    // Ξ†Ξ»Ξ»ΞµΟ‚ ΞΌΞ­ΞΈΞΏΞ΄ΞΏΞΉ Ξ³ΞΉΞ± Ο„ΞΏΞ½ Ο…Ο€ΞΏΞ»ΞΏΞ³ΞΉΟƒΞΌΟ Ο„ΞΏΟ… ΟƒΟ…Ξ½ΞΏΞ»ΞΉΞΊΞΏΟ ΞΊΟΟƒΟ„ΞΏΟ…Ο‚ ΞΊΞ»Ο€ ΞΌΟ€ΞΏΟΞΏΟΞ½ Ξ½Ξ± Ο€ΟΞΏΟƒΟ„ΞµΞΈΞΏΟΞ½ ΞµΞ΄Ο
}
