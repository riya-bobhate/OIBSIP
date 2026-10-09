import java.util.ArrayList;

public class Account {

    private String userId;
    private String pin;
    private double balance;

    private ArrayList<Transaction> transactions;

    public Account(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;

        transactions = new ArrayList<>();

        transactions.add(
            new Transaction("Account", balance, "Initial balance")
        );
    }

    public String getUserId() {
        return userId;
    }

    public boolean checkPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {

        if (amount <= 0) {
            return false;
        }

        if (amount > balance) {
            return false;
        }

        balance = balance - amount;

        transactions.add(
            new Transaction("Withdrawal", amount, "Money withdrawn")
        );

        return true;
    }

    public boolean deposit(double amount) {

        if (amount <= 0) {
            return false;
        }

        balance = balance + amount;

        transactions.add(
            new Transaction("Deposit", amount, "Money deposited")
        );

        return true;
    }

    public void addTransferTransaction(double amount, String details) {

        transactions.add(
            new Transaction("Transfer", amount, details)
        );
    }

    public void showTransactionHistory() {

        System.out.println("\n----- Transaction History -----");

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        System.out.println("--------------------------------");
    }
}