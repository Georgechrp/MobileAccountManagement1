package mainpackage.utils.model;

public class PhoneNumber {
    private final String number; //ο μοναδικός αριθμός τηλεφώνου
    private Program program; //το πρόγραμμα χρέωσης (πακέτο) που αντιστοιχεί στον αριθμό τηλεφώνου
    
    public PhoneNumber(String number, Program program) {
        this.number = number;
        setProgram(program);
    }

    public String getNumber() {
        return number;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}