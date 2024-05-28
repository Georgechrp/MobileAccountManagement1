package mainpackage.utils.model;

import mainpackage.program.model.Program;

public class PhoneNumber {
    private String number; //ο μοναδικός αριθμός τηλεφώνου
    private Program program; //το πρόγραμμα χρέωσης (πακέτο) που αντιστοιχεί στον αριθμό τηλεφώνου

    public PhoneNumber(String number, Program program) {
        this.number = number;
        this.program = program;
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
