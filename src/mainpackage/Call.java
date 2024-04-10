package mainpackage;

import java.time.LocalDateTime;

public class Call {
    private String callerPhoneNumber; //ο αριθμός τηλεφώνου του καλούντα
    private String receiverPhoneNumber;//ο αριθμός τηλεφώνου του καλούμενου
    private LocalDateTime startTime;// ώρα έναρξης της κλήσης
    private LocalDateTime endTime;// ώρα λήξης της κλήσης.

    public Call(String callerPhoneNumber, String receiverPhoneNumber, LocalDateTime startTime, LocalDateTime endTime) {
        this.callerPhoneNumber = callerPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCallerPhoneNumber() {
        return callerPhoneNumber;
    }

    public void setCallerPhoneNumber(String callerPhoneNumber) {
        this.callerPhoneNumber = callerPhoneNumber;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
