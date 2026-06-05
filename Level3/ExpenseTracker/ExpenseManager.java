import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private static final String FILE_NAME = "expenses.txt";

    public static void saveExpenses(List<Expense> expenses) {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(FILE_NAME))) {

            for (Expense expense : expenses) {

                writer.write(
                        expense.getDate() + "," +
                        expense.getCategory() + "," +
                        expense.getAmount());

                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Expense> loadExpenses() {

        List<Expense> expenses = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return expenses;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                expenses.add(
                        new Expense(
                                parts[0],
                                parts[1],
                                Double.parseDouble(parts[2])
                        )
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return expenses;
    }
}