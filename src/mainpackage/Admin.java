package mainpackage;

import java.util.ArrayList;

public class Admin extends User{
    private ArrayList<Seller> sellers;//πωλητές που διαχειρίζεται ο διαχειριστής
    private ArrayList<User> users;//όλοι οι χρήστες του συστήματος

    public Admin(String username, String name, String surname, String role) {
        super(username, name, surname, role);
        this.sellers = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
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

    // Μέθοδος για τη διαγραφή πωλητή
    public void deleteSeller(Seller seller) {
        sellers.remove(seller);
        users.remove(seller);
        System.out.println("Ο πωλητής " + seller.getUsername() + " διαγράφηκε.");
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
    public void createProgram(String programName) {
        System.out.println("Δημιουργήθηκε νέο πρόγραμμα: " + programName);
    }
}
