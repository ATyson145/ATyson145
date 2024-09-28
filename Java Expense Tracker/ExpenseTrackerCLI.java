import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExpenseTrackerCLI {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Add New Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid number.");
            }
        }
        scanner.close();
        System.out.println("Goodbye!");
    }

    private static void addExpense() {
        System.out.print("Enter the date (MM/DD/YYYY): ");
        String dateString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        }
        catch (ParseException e) {
            System.out.println("Invalid date format. Please use MM/DD/YYYY.");
            return;
        }

        System.out.println("Enter the amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter the description: ");
        String description = scanner.nextLine();

        Expense expense = new Expense(date, amount, description);
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }
        System.out.println("List of expenses: ");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}
