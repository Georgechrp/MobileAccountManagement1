package mainpackage.users.model;

import mainpackage.program.model.Program;
import mainpackage.utils.model.PhoneNumber;

import java.util.ArrayList;

public class Admin extends User{
    private ArrayList<Seller> sellers;//πωλητές που διαχειρίζεται ο διαχειριστής
    private ArrayList<Client> clients;//πελάτες που διαχειρίζεται ο διαχειριστής
    private ArrayList<User> users;//όλοι οι χρήστες του συστήματος

    private ArrayList<Program> programs;
    public Admin(String username, String name, String surname, String role) {
        super(username, name, surname, role);
        this.sellers = new ArrayList<>();
        this.users = new ArrayList<>();
        this.programs = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }
    public ArrayList<Client> getClients() {
        return clients;
    }
    public void setSellers(ArrayList<Seller> sellers) {
        this.sellers = sellers;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    // Μέθοδος για τη δημιουργία νέου πωλητή
    public void createSeller(String username, String name, String surname, String company) {
        Seller newSeller = new Seller(username, name, surname, "seller", company);
        sellers.add(newSeller);
        users.add(newSeller);
        System.out.println("Δημιουργήθηκε νέος πωλητής: " + username);
    }
    public void createClient(String username, String name, String surname, String role, String AFM, PhoneNumber phoneNumber) {
        Client newClient = new Client(username, name, surname, "Client", AFM, phoneNumber);
        clients.add(newClient);
        users.add(newClient);
        System.out.println("Δημιουργήθηκε νέος πελάτης: " + username);
    }

    // Μέθοδος για τη διαγραφή πωλητή
    public void deleteSeller(Seller seller) {
        sellers.remove(seller);
        users.remove(seller);
        System.out.println("Ο πωλητής " + seller.getUsername() + " διαγράφηκε.");
    }
    public void deleteClient(Client client) {
        clients.remove(client);
        users.remove(client);
        System.out.println("Ο πελάτης " + client.getUsername() + " διαγράφηκε.");
    }

    // Μέθοδος για τη δημιουργία νέου χρήστη
    public void createUser(String username, String name, String surname, String role) {
        User newUser = new User(username, name, surname, role);
        users.add(newUser);
        System.out.println("Δημιουργήθηκε νέος χρήστης: " + username);
    }

    // Μέθοδος για τη διαγραφή χρήστη
    public void deleteUser(User userThis) {
        users.remove(userThis);
        System.out.println("Ο χρήστης " + userThis.getUsername() + " διαγράφηκε.");
    }

    // Μέθοδος για τη δημιουργία νέου προγράμματος
    public void callProgram(Program programName) {

        System.out.println("Δημιουργήθηκε νέο πρόγραμμα: " + programName.getName());

    }

    public ArrayList<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(ArrayList<Program> programs) {
        this.programs = programs;
    }
}
