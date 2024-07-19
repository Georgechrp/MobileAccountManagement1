package mainpackage.utils.model;

public class Program {
	private int id;
    private String name;//Ο„ΞΏ ΟΞ½ΞΏΞΌΞ± Ο„ΞΏΟ… Ο€ΟΞΏΞ³ΟΞ¬ΞΌΞΌΞ±Ο„ΞΏΟ‚
    private int minutes;// Ο„Ξ± Ξ»ΞµΟ€Ο„Ξ¬ ΞΏΞΌΞΉΞ»Ξ―Ξ±Ο‚ Ο€ΞΏΟ… Ο€ΟΞΏΟƒΟ†Ξ­ΟΞΏΞ½Ο„Ξ±ΞΉ
    private double baseCharge;//Ο„ΞΏ Ο€Ξ¬Ξ³ΞΉΞΏ ΞΊΟΟƒΟ„ΞΏΟ‚ Ο„ΞΏΟ… Ο€ΟΞΏΞ³ΟΞ¬ΞΌΞΌΞ±Ο„ΞΏΟ‚
    private double additionalCharge;//Ο„ΞΏ ΞµΟ€ΞΉΟ€Ξ»Ξ­ΞΏΞ½ ΞΊΟΟƒΟ„ΞΏΟ‚ Ξ³ΞΉΞ± Ο‡ΟΞ­Ο‰ΟƒΞ· Ο€Ξ­ΟΞ±Ξ½ Ο„ΞΏΟ… Ο€Ξ±Ξ³Ξ―ΞΏΟ….

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