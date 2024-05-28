package mainpackage.users.model;

import java.util.ArrayList;

public class Seller extends User{
    private final String company;
    private ArrayList<Client> clients;

    public Seller(String username, String name, String surname, String password, String role, String company) {
        super(username, name, surname, password, role);
        this.company = company;
        this.clients = new ArrayList<>();
    }

    public String getCompany() {
        return company;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    // Μέθοδος για την προσθήκη νέου πελάτη
    public void addClient(Client client) {
        clients.add(client);
        System.out.println("Ο πελάτης " + client.getUsername() + " προστέθηκε επιτυχώς.");
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
