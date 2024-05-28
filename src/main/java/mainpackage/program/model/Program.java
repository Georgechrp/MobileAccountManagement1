package mainpackage.program.model;

public class Program {
	private int id;
    private String name;//το όνομα του προγράμματος
    private int minutes;// τα λεπτά ομιλίας που προσφέρονται
    private double baseCharge;//το πάγιο κόστος του προγράμματος
    private double additionalCharge;//το επιπλέον κόστος για χρέωση πέραν του παγίου.

    public Program(int id, String name, int minutes, double baseCharge, double additionalCharge) {
        setId(id);
        setName(name);
        setMinutes(minutes);
        setBaseCharge(baseCharge);
        setAdditionalCharge(additionalCharge);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
