package mainpackage.utils.model;

import java.time.LocalDateTime;

public class Call {
    private final String callerPhoneNumber; //ο αριθμός τηλεφώνου του καλούντα
    private final String receiverPhoneNumber;//ο αριθμός τηλεφώνου του καλούμενου
    private final LocalDateTime startTime;// ώρα έναρξης της κλήσης
    private final LocalDateTime endTime;// ώρα λήξης της κλήσης.
    private final String callId;
    
    public Call(String callerPhoneNumber, String receiverPhoneNumber, LocalDateTime startTime, LocalDateTime endTime, String callId) {
        this.callerPhoneNumber = callerPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.startTime = startTime;
        this.endTime = endTime;
		this.callId = callId;
        
    }

    public String getCallerPhoneNumber() {
        return callerPhoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

	public String getCallId() {
		return callId;
	}
}