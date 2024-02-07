import java.util.*;

public class Login {
    private List<User> users;
    private Scanner input;

    // Constructor that initialises Login with a list of stored users.
    public Login(List<User> users) {
        this.users = users;
        this.input = new Scanner(System.in);
    }

    // Method that is used to authenticate a user by their username and password.
    public User authenticateUser() {
        System.out.print("Enter your username: ");
        String username = input.nextLine();

        User user = findUserByUsername(username);

        if (user != null) {
            for (int attempt = 1; attempt <= 3; attempt++) {
                System.out.print("Enter your password (Attempt " + attempt + " of 3: ");
                String password = input.nextLine();

                if (user.getPassword().equals(password)) {
                    return user;
                }
                else {
                    System.out.println("Incorrect password. Please try again.");
                }
            }

            System.out.println("Too many incorrect attempts. Login failed.");
        }
        else {
            System.out.println("User not found. Login failed. Application exiting.");
        }

        return null;
    }

    // Method that is used to register a new user with a username and password, based on user input.
    public User registerUser() {
        System.out.print("Enter a username: ");
        String newUsername = input.nextLine();

        System.out.print("Enter a password: ");
        String newPassword = input.nextLine();

        User newUser = new User(newUsername, newPassword, List.of());
        users.add(newUser);

        return newUser;
    }

    // Method that is used to find users by their username. This is used to authenticate users.
    private User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}