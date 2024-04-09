package mainpackage;

public class CreateUser {
    public static void main(String[] args) {
        Client.PhoneNumber phoneNumber1 = new Client.PhoneNumber("6939591919");
        Client client= new Client("FoivosD", "Foivos", "Delivorias", "client", "174590134", phoneNumber1);

        Seller seller = new Seller("Alex24", "Alex", "Papadopoulos", "seller", "Vodefone");

        Admin admin = new Admin("Georgechrp", "George", "Christopoulos", "Admin");

    }
}
