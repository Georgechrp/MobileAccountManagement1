public class Main {
    public static void main(String[] args) {
        User user1 = new User("Georgechrp",
                "George", "Christopoulos", "Client");
        User user2 = new User("talepis",
                "Eyt8imios", "alepis", "seller");

        user2.setUsername("Talepisss");
        System.out.println(user2.getUsername());
    }
}