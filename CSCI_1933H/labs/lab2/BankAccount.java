import java.util.Scanner;

public class BankAccount {
    private String name;
    private String password;
    private double balance;

    // constructor
    public BankAccount(String name, String password, double balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    // getter for this.balance
    public double getBalance(String enteredPW) {
        if (this.password.equals(enteredPW)) {
            return this.balance;
        } else {
            return -1;
        }
    }

    // setter for this.password
    public boolean setPassword(String oldPW, String newPW) {
        if (this.password.equals(oldPW)) {
            this.password = newPW;
            return true;
        } else {
            return false;
        }
    }

    public void withdraw(String attemptedPW, double amount) {
        // Only people with the right password and sufficient funds can withdraw
        if (password.equals(attemptedPW)) {
            balance = balance - amount;
        }
    }

    // transfers an amount from this bank account to another bank account
    public void transfer(BankAccount other, String enteredPW, double amount) {
        this.withdraw(enteredPW, amount);
        Scanner myScanner = new Scanner(System.in);

        String input = myScanner.nextLine();

        if (input.equals(other.password)) {

            other.deposit(input, amount);
        }
        // else {
        // this.deposit(), amount);
        // }

    }

    public void deposit(String attemptedPW, double amount) {
        if (this.password.equals(attemptedPW)) {
            this.balance = this.balance + amount;
        }
    }

    public static void main(String[] args) {

        BankAccount RohitAccount = new BankAccount("Rohit", "CSCI1933ROCKS!", 0);
        RohitAccount.deposit("CSCI1933ROCKS!", 100.50);
        System.out.println("My account's balance is: " + RohitAccount.getBalance("CSCI1933ROCKS!"));

        // System.out.print("Enter your password: ");
        // Scanner myScanner = new Scanner(System.in);
        // String enteredPW = myScanner.nextLine();
        // System.out.println("Your balance is: " + RohitAccount.getBalance(enteredPW));

        BankAccount DovolisAccount = new BankAccount("Dovolis", "1933PROFESSOR", 1000);

        // Scanner s = new Scanner(System.in);
        // String RohitAttemptPW = s.nextLine();
        DovolisAccount.transfer(RohitAccount, "CSCI1933ROCKS", 100);
        System.out.println("Dovolis Balance: " + DovolisAccount.getBalance("1933PROFESSOR")); // OG balance: 1000
        System.out.println("Rohit Balance: " + RohitAccount.getBalance("CSCI1933ROCKS!")); // OG balance: 100.5

        RohitAccount.transfer(DovolisAccount, "1933PROFESSOR", 10);
        System.out.println("Dovolis Balance: " + DovolisAccount.getBalance("1933PROFESSOR")); // OG balance: 1000
        System.out.println("Rohit Balance: " + RohitAccount.getBalance("CSCI1933ROCKS!")); // OG balance: 100.5
    }
}