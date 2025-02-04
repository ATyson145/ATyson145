import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExpenseTracker {
    private List<Expense> expenses;
    private ObjectMapper objectMapper;
    private File file;

    public ExpenseTracker(String fileName) {
        this.expenses = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
        this.file = new File("lib/src/main/java/", fileName);

        loadExpenses();
    }

    private void loadExpenses() {
        try {
            if (file.exists()) {
                expenses = objectMapper.readValue(file, new TypeReference<List<Expense>>() {
                });
            }
        }
        catch (IOException e) {
            System.err.println("Error loading expenses: " + e.getMessage());
        }
    }

    private void saveExpenses() {
        try {
            objectMapper.writeValue(file, expenses);
        }
        catch (IOException e) {
            System.err.println("Error saving expenses: " + e.getMessage());
        }
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        saveExpenses();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        saveExpenses();
    }

}
