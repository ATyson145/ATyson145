import java.util.Date;
import java.text.SimpleDateFormat;

public class Expense {
    private Date date;
    private double amount;
    private String description;

    public Expense() {}

    public Expense(Date date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = dateFormat.format(date);
        return "Date: " + dateString + ", Amount: $" + amount + ", Description: " + description;
    }

}