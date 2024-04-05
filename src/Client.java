public class Client extends User {
    private final String AFM;
    private PhoneNumber phoneNumber;

    public Client(String username, String name, String surname, String role, String AFM, PhoneNumber phoneNumber) {
        super(username, name, surname, role);
        this.AFM = AFM;
        this.phoneNumber = phoneNumber;
    }

    public String getAFM() {
        return AFM;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void register() {
        super.register();
        System.out.println("ΑΦΜ: " + AFM);
        System.out.println("Τηλέφωνο: " + phoneNumber.getNumber());
    }

    // Υποθέτουμε ότι η υποκλάση PhoneNumber είναι η εξής:
     public class PhoneNumber {
         private String number;

         public PhoneNumber(String number) {
             this.number = number;
         }

         public String getNumber() {
             return number;
         }
     }
}
