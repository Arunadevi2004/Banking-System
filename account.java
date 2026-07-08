import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private ArrayList<transaction> transactionHistory;

    public static String generateAccountNumber() {
        String prefix = "JMAD";
        Random random = new Random();
        long digits = 100_000_000_000L + (long)(random.nextDouble() * 900_000_000_000L);
        return prefix + digits;
    }

    account(String accountHolderName, double amount){
        this.accountNumber=generateAccountNumber();
        this.accountHolderName=accountHolderName;
        this.balance=amount;
        transactionHistory=new ArrayList<>();
    }


    public void setacc_num(String accountNumber){
        this.accountNumber=accountNumber;
    }
    public void setname(String name){
        accountHolderName=name;
    }
    public void setbalance(double balance){
        this.balance=balance;
    }


    public String getacc_num(){
        return accountNumber;
    } 
    public String getname(){
        return accountHolderName;
    }
    public double getbalance(){
        return balance;
    }


    public String display() {
        return "Account[ACCOUNT NUMBER= " +  accountNumber + ", ACCOUNT HOLDER NAME = " + accountHolderName + ", BALANCE =" + balance +"]";
    }


    public static String generateCleanUUIDTransactionId() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public void withdraw(double amount) {
        String txId = generateCleanUUIDTransactionId();
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            transactionHistory.add(new transaction(txId, LocalDateTime.now(), "Withdrawal", amount, "Successful", this.balance));
            System.out.println("Withdrawal successful!");
        } else {
            transactionHistory.add(new transaction(txId, LocalDateTime.now(), "Withdrawal", amount, "Failed", this.balance));
            System.out.println("Withdrawal failed: Insufficient funds or invalid amount.");
        }
    }


    public void deposit(double amount) {
        String txId = generateCleanUUIDTransactionId();
        if (amount > 0) {
            this.balance += amount;
            transactionHistory.add(new transaction(txId, LocalDateTime.now(), "Deposit", amount, "Successful", this.balance));
            System.out.println("Deposit successful!");
        } else {
            transactionHistory.add(new transaction(txId, LocalDateTime.now(), "Deposit", amount, "Failed", this.balance));
            System.out.println("Deposit failed: Invalid amount.");
        }
    }

    public void addTransferLog(String type, double amount) {
        String txId = generateCleanUUIDTransactionId();
        transactionHistory.add(new transaction(txId, LocalDateTime.now(), type, amount, "Successful", this.balance));
    }


    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (transaction tx : transactionHistory) {
            tx.printDetails();
        }
    }

}
