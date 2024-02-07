public class Account {
    private String type;
    private double balance;
    private double overdraftLimit;

    // Initialises a new account with account type and overdraft limit.
    public Account(String type, double overdraftLimit) {
        this.type = type;
        this.overdraftLimit = overdraftLimit;
    }

    // Returns the type of account.
    public String getType() {
        return type;
    }

    // Returns the balance of the account.
    public double getBalance() {
        return balance;
    }

    // Returns the overdraft limit of the account.
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    // Method used to deposit an amount of money, specified by the user, into an account.
    public void deposit(double amount) {
        balance += amount;
    }

    // Method used to withdraw an amount of money, specified by the user, from an account.
    // It also takes into account the overdraft limit of the account.
    public void withdraw (double amount) {
        if (balance - amount >= -overdraftLimit) {
            balance -= amount;
            System.out.println("=======================");
            System.out.println("Withdrawal successful. Your new balance is £" + balance);
            System.out.println("=======================");
        }
        else {
            System.out.println("=======================");
            System.out.println("Insufficient funds for withdrawal.");
            System.out.println("=======================");
        }
    }


    // Method used to transfer an amount of money, specified by the user, to an account.
    // It also takes into account the overdraft limit of the account.
    public void transfer(Account targetAccount, double amount) {
        if (balance - amount >= -overdraftLimit) {
            balance -= amount;
            targetAccount.deposit(amount);
            System.out.println("=======================");
            System.out.println("Transfer successful. Your new balance in the source account is £" + balance);
            System.out.println("=======================");
        }
        else {
            System.out.println("=======================");
            System.out.println("Insufficient funds for transfer.");
            System.out.println("=======================");
        }
    }

    // A method that is used to display the account balance. It shows the user the account type,
    // the account balance and the overdraft limit for the account.
    public void displayBalance() {
        System.out.println();
        System.out.println("=======================");
        System.out.println("Account Type: " + type);
        System.out.println("Balance: £" + balance);
        System.out.println("Overdraft Limit: £" + overdraftLimit);
        System.out.println("=======================");
    }
}