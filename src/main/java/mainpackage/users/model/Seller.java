package mainpackage.users.model;

public class Seller extends User{
    private final String company;

    public Seller(String username, String name, String surname, String password, int role, String company) {
        super(username, name, surname, password, role);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    // Μέθοδος για την έκδοση λογαριασμού πελάτη
    public void issueInvoice(Client client, double amount) {
        System.out.println("Έκδοση λογαριασμού πελάτη " + client.getUsername() + " για ποσό " + amount);
    }

    // Μέθοδος για την αλλαγή προγράμματος πελάτη
    public void changeClientProgram(Client client, String newProgram) {
        System.out.println("Ο πελάτης " + client.getUsername() + " άλλαξε πρόγραμμα σε " + newProgram);
    }
}
