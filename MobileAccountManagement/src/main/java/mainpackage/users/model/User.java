package mainpackage.users.model;

public class User {
    // Χαρακτηριστικά της κλάσης Users
    private String username;
    private String name;
    private String surname;
    private String password;
    private int role; // Ιδιότητα: client, seller, administrator

    // Constructor της κλάσης Users
    public User(String username, String name, String surname, String password, int role) {
        setUsername(username);
        setName(name);
        setSurname(surname);
        setRole(role);
        setPassword(password);
        // Αύξηση του μετρητή κατά ένα κάθε φορά που καλείται ο constructor
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    // Μέθοδος εγγραφής νέου χρήστη
    public void register() {
        // Υλοποιήστε τη λειτουργία εγγραφής εδώ
        System.out.println("Ο χρήστης " + username + " εγγράφηκε επιτυχώς.");
    }

    // Μέθοδος σύνδεσης χρήστη
    public void login() {
        // Υλοποιήστε τη λειτουργία σύνδεσης εδώ
        System.out.println("Ο χρήστης " + username + " συνδέθηκε επιτυχώς.");
    }

    // Μέθοδος αποσύνδεσης χρήστη
    public void logout() {
        // Υλοποιήστε τη λειτουργία αποσύνδεσης εδώ
        System.out.println("Ο χρήστης " + username + " αποσυνδέθηκε επιτυχώς.");
    }

}