package mainpackage.utils.model;

public class PhoneNumber {
    private final String number; //ΞΏ ΞΌΞΏΞ½Ξ±Ξ΄ΞΉΞΊΟΟ‚ Ξ±ΟΞΉΞΈΞΌΟΟ‚ Ο„Ξ·Ξ»ΞµΟ†ΟΞ½ΞΏΟ…
    private Program program; //Ο„ΞΏ Ο€ΟΟΞ³ΟΞ±ΞΌΞΌΞ± Ο‡ΟΞ­Ο‰ΟƒΞ·Ο‚ (Ο€Ξ±ΞΊΞ­Ο„ΞΏ) Ο€ΞΏΟ… Ξ±Ξ½Ο„ΞΉΟƒΟ„ΞΏΞΉΟ‡ΞµΞ― ΟƒΟ„ΞΏΞ½ Ξ±ΟΞΉΞΈΞΌΟ Ο„Ξ·Ξ»ΞµΟ†ΟΞ½ΞΏΟ…
    
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