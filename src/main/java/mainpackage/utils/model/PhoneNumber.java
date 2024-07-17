package mainpackage.utils.model;

public class PhoneNumber {
    private final String number; //ο μοναδικός αριθμός τηλεφώνου
    private int programId; //το πρόγραμμα χρέωσης (πακέτο) που αντιστοιχεί στον αριθμό τηλεφώνου
    
    public PhoneNumber(String number, int programId) {
        this.number = number;
        this.programId = programId;
    }

    public String getNumber() {
        return number;
    }

    public int getProgramId() {
        return programId;
    }

    
}