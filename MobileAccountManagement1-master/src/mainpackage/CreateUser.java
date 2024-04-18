package mainpackage;

public class CreateUser {
    public static void main(String[] args) {
        Program program1 =  new Program("Student DIY", 600, 8.0, 2.0);
        Program program2 =  new Program("Student call to all", 400, 5.0, 3.0);
        Program program3 =  new Program("Unlimited calls", 3000, 10.0, 3.0);

        PhoneNumber phoneNumber1 = new PhoneNumber("6939591919", program1);
        PhoneNumber phoneNumber2 = new PhoneNumber("6946469044", program2);

        Client client1= new Client(null, "Foivos", "Delivorias", "client", "174590134", phoneNumber1);
        client1.register();
        client1.displayAccount();
        client1.displayCallHistory();
        client1.payBill(50.00);
        Client client2= new Client("Papakon73", "Basilis", "Papakonstadinou", "client", "174590134", phoneNumber2);

        Seller seller1 = new Seller("Alex24", "Alex", "Papadopoulos", "seller", "Vodafone");
        seller1.addClient(client1);
        seller1.issueInvoice(client1, 40.00);
        seller1.changeClientProgram(client1, program3.toString());
        System.out.println("All clients of seller 1: " + seller1.getClients());


        Admin admin = new Admin("Georgechrp", "George", "Christopoulos", "Admin");
        admin.createSeller("Basilis40", "Basilis", "Tsitsanis", "Wind");

    }
}
