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
        // Check if the seller already exists
        boolean sellerExists = false;
        for (Seller existingSeller : sellers) {
            if (existingSeller.getUsername().equals(username)) {
                sellerExists = true;
                break;
            }
        }

        if (!sellerExists) {
            Seller newSeller = new Seller(username, name, surname, "seller", company);
            sellers.add(newSeller);
            users.add(newSeller);
            System.out.println("Δημιουργήθηκε νέος πωλητής: " + username);
        } else {
            System.out.println("Ο πωλητής με το όνομα χρήστη " + username + " υπάρχει ήδη.");
        }
    }

    // Μέθοδος για τη διαγραφή πωλητή
    public void deleteSeller(Seller seller) {
        // Check if the seller exists
        if (sellers.contains(seller)) {
            sellers.remove(seller);
            users.remove(seller);
            System.out.println("Ο πωλητής " + seller.getUsername() + " διαγράφηκε.");
        } else {
            System.out.println("Ο πωλητής " + seller.getUsername() + " δεν υπάρχει στη λίστα.");
        }
    }

    // Μέθοδος για τη δημιουργία νέου χρήστη
    public void createUser(String username, String name, String surname, String role) {
        // Flag to check if user already exists
        boolean userExists = false;

        // Iterate through existing users
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(username)) {
                userExists = true;
                break;
            }
        }

        // If user does not exist, add the new user
        if (!userExists) {
            User newUser = new User(username, name, surname, role);
            users.add(newUser);
            System.out.println("Δημιουργήθηκε νέος χρήστης: " + username);
        } else {
            System.out.println("Ο χρήστης με το όνομα χρήστη " + username + " υπάρχει ήδη.");
        }
    }

    // Μέθοδος για τη διαγραφή χρήστη
    public void deleteUser(User userThis) {
        // Check if the user exists
        if (users.contains(userThis)) {
            users.remove(userThis);
            System.out.println("Ο χρήστης " + userThis.getUsername() + " διαγράφηκε.");
        } else {
            System.out.println("Ο χρήστης " + userThis.getUsername() + " δεν υπάρχει στη λίστα.");
        }
    }

    // Μέθοδος για τη δημιουργία νέου προγράμματος
    public void createProgram(String programName) {
        System.out.println("Δημιουργήθηκε νέο πρόγραμμα: " + programName);
    }
}
