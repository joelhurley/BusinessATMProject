import java.util.*;

public class User {
    private String username;
    private String password;
    private List<Account> accounts;

    // Constructor that initialises a new User with username, password and accounts.
    public User(String username, String password, List<Account> accounts) {
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>(accounts);
    }

    // Used to return the username of the user.
    public String getUsername() {
        return username;
    }

    // Used to return the password of the user.
    public String getPassword () {
        return password;
    }

    // Used to return a list of all the accounts of the user.
    public List<Account> getAccounts() {
        return accounts;
    }

    // Used to add a new account to the list of the User's accounts.
    public void addAccount(Account account) {
        accounts.add(account);
    }
}