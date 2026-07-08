import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class transaction {
    private final String id;
    private final LocalDateTime dateTime;
    private final String type;
    private final double amount;
    private final String status;
    private final double postBalance;


    public transaction(String id, LocalDateTime dateTime, String type, double amount, String status, double postBalance) {
        this.id = id;
        this.dateTime = dateTime;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.postBalance = postBalance;
    }

    
    public void printDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.printf("[%s] ID: %s | %s | Amt: ₹%.2f | Status: %s | Bal: ₹%.2f%n", 
            dateTime.format(formatter), id, type, amount, status, postBalance);
    }
}
