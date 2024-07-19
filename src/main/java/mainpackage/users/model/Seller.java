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
}