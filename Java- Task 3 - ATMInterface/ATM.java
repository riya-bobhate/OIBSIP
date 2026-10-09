import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("================================");
        System.out.println("        ATM INTERFACE");
        System.out.println("================================");

        Account account = login();

        if (account == null) {
            return;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Welcome, " + account.getUserId());

        showMenu(account);
    }

    private Account login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("\nEnter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            Account account = bank.login(userId, pin);

            if (account != null) {
                return account;
            }

            attempts++;

            System.out.println("Invalid User ID or PIN.");

            if (attempts < 3) {
                System.out.println(
                    "Attempts remaining: " + (3 - attempts)
                );
            }
        }

        System.out.println("\nToo many incorrect attempts.");
        System.out.println("Access denied.");

        return null;
    }

    private void showMenu(Account account) {

        while (true) {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.println("===============================");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {

                case 1:
                    account.showTransactionHistory();
                    break;

                case 2:
                    withdraw(account);
                    break;

                case 3:
                    deposit(account);
                    break;

                case 4:
                    transfer(account);
                    break;

                case 5:
                    System.out.println(
                        "Thank you for using the ATM."
                    );
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void withdraw(Account account) {

        System.out.print("Enter amount to withdraw: ");

        try {

            double amount =
                    Double.parseDouble(scanner.nextLine());

            if (amount <= 0) {
                System.out.println("Invalid amount.");
                return;
            }

            if (amount > account.getBalance()) {
                System.out.println("Insufficient Funds");
                return;
            }

            if (account.withdraw(amount)) {

                System.out.println("Withdrawal successful!");
                System.out.println(
                    "Updated Balance: " + account.getBalance()
                );
            }

        } catch (NumberFormatException e) {

            System.out.println("Please enter a valid amount.");
        }
    }

    private void deposit(Account account) {

        System.out.print("Enter amount to deposit: ");

        try {

            double amount =
                    Double.parseDouble(scanner.nextLine());

            if (account.deposit(amount)) {

                System.out.println("Deposit successful!");
                System.out.println(
                    "Updated Balance: " + account.getBalance()
                );

            } else {

                System.out.println("Invalid deposit amount.");
            }

        } catch (NumberFormatException e) {

            System.out.println("Please enter a valid amount.");
        }
    }

    private void transfer(Account account) {

        System.out.print("Enter receiver User ID: ");
        String receiverId = scanner.nextLine();

        System.out.print("Enter amount to transfer: ");

        try {

            double amount =
                    Double.parseDouble(scanner.nextLine());

            if (bank.transfer(account, receiverId, amount)) {

                System.out.println("Transfer successful!");
                System.out.println(
                    "Updated Balance: " + account.getBalance()
                );
            }

        } catch (NumberFormatException e) {

            System.out.println("Please enter a valid amount.");
        }
    }
}