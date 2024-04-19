package mainpackage.program;

public class Program {
    private String name;//το όνομα του προγράμματος
    private int minutes;// τα λεπτά ομιλίας που προσφέρονται
    private double baseCharge;//το πάγιο κόστος του προγράμματος
    private double additionalCharge;//το επιπλέον κόστος για χρέωση πέραν του παγίου.

    public Program(String name, int minutes, double baseCharge, double additionalCharge) {
        this.name = name;
        this.minutes = minutes;
        this.baseCharge = baseCharge;
        this.additionalCharge = additionalCharge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public double getBaseCharge() {
        return baseCharge;
    }

    public void setBaseCharge(double baseCharge) {
        this.baseCharge = baseCharge;
    }

    public double getAdditionalCharge() {
        return additionalCharge;
    }

    public void setAdditionalCharge(double additionalCharge) {
        this.additionalCharge = additionalCharge;
    }

}
