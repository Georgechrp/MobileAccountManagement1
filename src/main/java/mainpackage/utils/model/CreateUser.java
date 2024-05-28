package mainpackage.utils.model;

import mainpackage.calls.model.Call;
import mainpackage.program.model.Program;
import mainpackage.users.model.Admin;
import mainpackage.users.model.Client;
import mainpackage.users.model.Seller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateUser {
    public static void main(String[] args) {
        Program program1 =  new Program("Student DIY", 600, 8.0, 2.0);
        Program program2 =  new Program("Student call to all", 400, 5.0, 3.0);
        Program program3 =  new Program("Unlimited calls", 3000, 10.0, 3.0);

        PhoneNumber phoneNumber1 = new PhoneNumber("6939591919", program1);
        PhoneNumber phoneNumber2 = new PhoneNumber("6946469044", program2);
        PhoneNumber phoneNumber3 = new PhoneNumber("6946395044", program3);


        LocalDate date1 = LocalDate.of(2000, 10, 23);
        LocalTime time1 = LocalTime.of(12, 20, 0);
        LocalDateTime localDateTime1 = LocalDateTime.of(date1, time1);
        LocalDate date2 = LocalDate.of(2000, 10, 23);
        LocalTime time2 = LocalTime.of(13, 20, 0);
        LocalDateTime localDateTime2 = LocalDateTime.of(date2, time2);

        Client client1= new Client("FoivosD", "Foivos", "Delivorias", "client", "174590134", phoneNumber1);
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
        admin.createClient("Stelios563", "Stelios", "Stauros", "client", "175837492", phoneNumber3);

        Call call1 = new Call(phoneNumber1.getNumber(), phoneNumber2.getNumber(), localDateTime1, localDateTime2);

        client1.addCall(call1);



        ArrayList<Client> obj1 = admin.getClients();
        obj1.get(0).addCall(call1);
        admin.setClients(obj1);

        admin.deleteClient(admin.getClients().get(0));
        admin.deleteSeller(admin.getSellers().get(0));

        admin.callProgram(program1);

        try {
            ArrayList<Seller> Sel = admin.getSellers();
            System.out.println("Name of fifth seller : " + Sel.get(5).getName());
        }catch(IndexOutOfBoundsException e)
        {
            System.out.println("Error");
            e.printStackTrace();
        }


    }
}
