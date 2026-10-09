import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts;

    public Bank() {

        accounts = new ArrayList<>();

        accounts.add(
            new Account("user1609", "1234", 10000.0)
        );

        accounts.add(
            new Account("user123", "5678", 15000.0)
        );
    }

    public Account login(String userId, String pin) {

        for (Account account : accounts) {

            if (account.getUserId().equals(userId)
                    && account.checkPin(pin)) {

                return account;
            }
        }

        return null;
    }

    public Account findAccount(String userId) {

        for (Account account : accounts) {

            if (account.getUserId().equals(userId)) {
                return account;
            }
        }

        return null;
    }

    public boolean transfer(Account sender, String receiverId, double amount) {

        Account receiver = findAccount(receiverId);

        if (receiver == null) {
            System.out.println("Receiver account not found.");
            return false;
        }

        if (sender.getUserId().equals(receiverId)) {
            System.out.println("You cannot transfer money to your own account.");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return false;
        }

        if (amount > sender.getBalance()) {
            System.out.println("Insufficient Funds");
            return false;
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        sender.addTransferTransaction(
            amount,
            "Transferred to " + receiverId
        );

        receiver.addTransferTransaction(
            amount,
            "Received from " + sender.getUserId()
        );

        return true;
    }
}