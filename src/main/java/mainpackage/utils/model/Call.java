package mainpackage.utils.model;

import java.time.LocalDateTime;

public class Call {
    private final String callerPhoneNumber; //ΞΏ Ξ±ΟΞΉΞΈΞΌΟΟ‚ Ο„Ξ·Ξ»ΞµΟ†ΟΞ½ΞΏΟ… Ο„ΞΏΟ… ΞΊΞ±Ξ»ΞΏΟΞ½Ο„Ξ±
    private final String receiverPhoneNumber;//ΞΏ Ξ±ΟΞΉΞΈΞΌΟΟ‚ Ο„Ξ·Ξ»ΞµΟ†ΟΞ½ΞΏΟ… Ο„ΞΏΟ… ΞΊΞ±Ξ»ΞΏΟΞΌΞµΞ½ΞΏΟ…
    private final LocalDateTime startTime;// ΟΟΞ± Ξ­Ξ½Ξ±ΟΞΎΞ·Ο‚ Ο„Ξ·Ο‚ ΞΊΞ»Ξ®ΟƒΞ·Ο‚
    private final LocalDateTime endTime;// ΟΟΞ± Ξ»Ξ®ΞΎΞ·Ο‚ Ο„Ξ·Ο‚ ΞΊΞ»Ξ®ΟƒΞ·Ο‚.
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