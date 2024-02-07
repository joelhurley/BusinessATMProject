import java.util.*;

public class Main {
    public static void main(String[] args) {

        // This creates a list that will be used to store all Users in.
        List<User> users = new ArrayList<>();

        // This registers a new user and stores that value in the User list.
        Login login = new Login(users);
        User newUser = login.registerUser();
        if (newUser != null) {
            System.out.println("User registration was successful!");
        }

        // Authenticates the current user and stores the result in a value called "currentUser".
        User currentUser = login.authenticateUser();

        // Checks to see if the current user has been authenticated, and if they have, display the menu to the user.
        if (currentUser != null) {
            Menu menu = new Menu(currentUser);
            menu.displayMenu();
        }
    }
}