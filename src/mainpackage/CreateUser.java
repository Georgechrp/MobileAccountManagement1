package mainpackage;

public class CreateUser {
    public static void main(String[] args) {
        Client.PhoneNumber phoneNumber1 = new Client.PhoneNumber("6939591919");
        Client client1= new Client("FoivosD", "Foivos", "Delivorias", "client", "174590134", phoneNumber1);
        client1.register();
        client1.displayAccount();
        client1.displayCallHistory();
        client1.payBill(50.00);


        Seller seller1 = new Seller("Alex24", "Alex", "Papadopoulos", "seller", "Vodefone");
        seller1.addClient(client1);
        seller1.issueInvoice(client1, 40.00);
        seller1.changeClientProgram(client1, "Combbo max");
        System.out.println("All clients of seller 1: " + seller1.getClients());


        Admin admin = new Admin("Georgechrp", "George", "Christopoulos", "Admin");
        admin.createSeller("Basilis40", "Basilis", "Tsitsanis", "Wind");

    }
}
