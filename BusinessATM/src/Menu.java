import java.util.*;

public class Menu {
    private User user;

    public Menu(User user) {
        this.user = user;
    }

    // A method used to display the menu and process the user's choices.
    public void displayMenu() {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            // Displays all the options to the user.
            System.out.println("\n1. Check Balance");
            System.out.println("2. Make Withdrawal");
            System.out.println("3. Deposit Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Create New Account");
            System.out.println("6. Exit The Application");

            System.out.println();
            System.out.print("Enter the number of your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    user.getAccounts().forEach(Account::displayBalance);
                    break;
                case 2:
                    makeWithdrawal();
                    break;
                case 3:
                    makeDeposit();
                    break;
                case 4:
                    makeTransfer();
                    break;
                case 5:
                    createNewAccount();
                    break;
                case 6:
                    System.out.println("Exiting the application.");
                    System.out.println("=======================");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("=======================");
            }
        } while (choice != 6);
    }

    // Method that displays a menu for withdrawing money from an account.
    private void makeWithdrawal() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the name of the account you want to withdraw from: ");
        String accountType = input.nextLine();

        Optional<Account> accountOptional = user.getAccounts().stream()
                .filter(account -> account.getType().equalsIgnoreCase(accountType))
                .findFirst();

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            System.out.print("Enter the amount you would like to withdraw: £");
            double withdrawAmount = input.nextDouble();
            account.withdraw(withdrawAmount);
        }
        else {
            System.out.println("=======================");
            System.out.println("Error: Account not found.");
            System.out.println("=======================");
        }
    }

    // Method that displays a menu for depositing money into an account.
    private void makeDeposit() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the name of the account you want to deposit into: ");
        String accountType = input.nextLine();

        Optional<Account> accountOptional = user.getAccounts().stream()
                .filter(account -> account.getType().equalsIgnoreCase(accountType))
                .findFirst();

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            System.out.print("Enter the amount you want to deposit: £");
            double depositAmount = input.nextDouble();
            account.deposit(depositAmount);
            System.out.println("=======================");
            System.out.println("Deposit successful. New balance: £" + account.getBalance());
            System.out.println("=======================");
        }
        else {
            System.out.println("=======================");
            System.out.println("Error: Account not found.");
            System.out.println("=======================");
        }
    }

    // Method that displays a menu for transfering money between accounts.
    private void makeTransfer() {
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter the name of account you want to transfer money from: ");
        String sourceAccountTransfer = input.nextLine();

        Optional<Account> sourceAccountOptional = user.getAccounts().stream()
                .filter(account -> account.getType().equalsIgnoreCase(sourceAccountTransfer))
                .findFirst();

        if (sourceAccountOptional.isPresent()) {
            System.out.print("Enter the name of the account you want to transfer money to: ");
            String targetAccountTransfer = input.nextLine();

            Optional<Account> targetAccountOptional = user.getAccounts().stream()
                    .filter(account -> account.getType().equalsIgnoreCase(targetAccountTransfer))
                    .findFirst();

            if (targetAccountOptional.isPresent()) {
                Account sourceAccount = sourceAccountOptional.get();
                Account targetAccount = targetAccountOptional.get();

                System.out.print("Enter the amount you would like to transfer: £");
                double amount = input.nextDouble();
                sourceAccount.transfer(targetAccount, amount);
            }
            else {
                System.out.println("=======================");
                System.out.println("Error: Target account not found.");
                System.out.println("=======================");
            }
        }
        else {
            System.out.println("=======================");
            System.out.println("Error: Source account not found.");
            System.out.println("=======================");
        }
    }

    // Method that displays a menu for the user to create a new account.
    private void createNewAccount() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nChoose an account type you want to create:");
        System.out.println("1. Small Business - Overdraft limit: £1000");
        System.out.println("2. Community - Overdraft limit: £2500");
        System.out.println("3. Client - Overdraft limit: £1500");

        System.out.println();
        System.out.print("Enter the number of the account you want to create: ");
        int choice = input.nextInt();
        System.out.println();

        String accountType;
        double overdraftLimit;

        switch (choice) {
            case 1:
                accountType = "Small Business";
                overdraftLimit = 1000;
                break;
            case 2:
                accountType = "Community";
                overdraftLimit = 2500;
                break;
            case 3:
                accountType = "Client";
                overdraftLimit = 1500;
                break;
            default:
                System.out.println("=======================");
                System.out.println("Invalid choice. No account was created.");
                System.out.println("=======================");
                return;
        }

        // This adds a number onto the end of the account name, so if the user wants to create
        // multiple of the same accounts, there is a way of distinguishing which account is which.
        // This is useful for determining which account you want to deposit/withdraw/transfer money to
        // when you have multiple of the same type of account.
        String finalAccountType = accountType;
        int maxNumber = user.getAccounts().stream()
                .filter(account -> account.getType().startsWith(finalAccountType))
                .map(account -> {
                    String name = account.getType();
                    int number = 1;
                    int index = name.lastIndexOf(' ');
                    if (index != -1){
                        try {
                            number = Integer.parseInt(name.substring(index + 1));
                        }
                        catch (NumberFormatException ignored) {
                        }
                    }
                    return number;
                })
                .max(Integer::compare)
                .orElse(0);

        accountType += " " + (maxNumber + 1);

        Account newAccount = new Account(accountType, overdraftLimit);
        user.addAccount(newAccount);
        System.out.println("=======================");
        System.out.println("New " + accountType + " account was created successfully!");
        System.out.println("=======================");
    }
}