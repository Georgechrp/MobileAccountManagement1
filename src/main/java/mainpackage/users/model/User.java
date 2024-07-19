package mainpackage.users.model;

public class User {
    // Ξ§Ξ±ΟΞ±ΞΊΟ„Ξ·ΟΞΉΟƒΟ„ΞΉΞΊΞ¬ Ο„Ξ·Ο‚ ΞΊΞ»Ξ¬ΟƒΞ·Ο‚ Users
    private String username;
    private String name;
    private String surname;
    private String password;
    private int role; // Ξ™Ξ΄ΞΉΟΟ„Ξ·Ο„Ξ±: client, seller, administrator

    // Constructor Ο„Ξ·Ο‚ ΞΊΞ»Ξ¬ΟƒΞ·Ο‚ Users
    public User(String username, String name, String surname, String password, int role) {
        setUsername(username);
        setName(name);
        setSurname(surname);
        setRole(role);
        setPassword(password);
        // Ξ‘ΟΞΎΞ·ΟƒΞ· Ο„ΞΏΟ… ΞΌΞµΟ„ΟΞ·Ο„Ξ® ΞΊΞ±Ο„Ξ¬ Ξ­Ξ½Ξ± ΞΊΞ¬ΞΈΞµ Ο†ΞΏΟΞ¬ Ο€ΞΏΟ… ΞΊΞ±Ξ»ΞµΞ―Ο„Ξ±ΞΉ ΞΏ constructor
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
}
