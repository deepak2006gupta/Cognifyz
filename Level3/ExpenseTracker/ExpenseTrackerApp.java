import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExpenseTrackerApp extends Application {

    private DatePicker datePicker;
    private ComboBox<String> categoryBox;
    private TextField amountField;

    private TableView<Expense> table;

    private ObservableList<Expense> expenseList;

    private Label totalLabel;

    @Override
    public void start(Stage stage) {

        expenseList = FXCollections.observableArrayList();

        List<Expense> loadedExpenses =
                ExpenseManager.loadExpenses();

        expenseList.addAll(loadedExpenses);

        Label title = new Label("Expense Tracker");
        title.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;"
        );

        datePicker = new DatePicker();
        datePicker.setPromptText("Select Date");

        categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll(
                "Food",
                "Travel",
                "Shopping",
                "Bills",
                "Entertainment",
                "Other"
        );
        categoryBox.setPromptText("Select Category");

        amountField = new TextField();
        amountField.setPromptText("Enter Amount");

        Button addButton =
                new Button("Add Expense");

        Button deleteButton =
                new Button("Delete Selected");

        table = new TableView<>();

        TableColumn<Expense, String> dateColumn =
                new TableColumn<>("Date");

        dateColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getDate()
                )
        );

        TableColumn<Expense, String> categoryColumn =
                new TableColumn<>("Category");

        categoryColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getCategory()
                )
        );

        TableColumn<Expense, Number> amountColumn =
                new TableColumn<>("Amount");

        amountColumn.setCellValueFactory(
                data -> new SimpleDoubleProperty(
                        data.getValue().getAmount()
                )
        );

        dateColumn.setPrefWidth(180);
        categoryColumn.setPrefWidth(220);
        amountColumn.setPrefWidth(180);

        table.getColumns().addAll(
                dateColumn,
                categoryColumn,
                amountColumn
        );

        table.setItems(expenseList);

        totalLabel = new Label();

        updateTotal();

        addButton.setOnAction(e -> addExpense());

        deleteButton.setOnAction(e -> deleteExpense());

        HBox buttonBox =
                new HBox(10, addButton, deleteButton);

        VBox root = new VBox(12);

        root.setPadding(new Insets(15));

        root.getChildren().addAll(

                title,

                new Label("Date"),
                datePicker,

                new Label("Category"),
                categoryBox,

                new Label("Amount"),
                amountField,

                buttonBox,

                table,

                totalLabel
        );

        Scene scene =
                new Scene(root, 650, 550);

        stage.setTitle("Expense Tracker");

        stage.setScene(scene);

        stage.show();
    }

    private void addExpense() {

        try {

            if (datePicker.getValue() == null) {

                showError(
                        "Please select a date."
                );
                return;
            }

            if (categoryBox.getValue() == null) {

                showError(
                        "Please select a category."
                );
                return;
            }

            String date =
                    datePicker.getValue()
                            .format(
                                    DateTimeFormatter.ofPattern(
                                            "dd-MM-yyyy"
                                    )
                            );

            String category =
                    categoryBox.getValue();

            double amount =
                    Double.parseDouble(
                            amountField.getText().trim()
                    );

            Expense expense =
                    new Expense(
                            date,
                            category,
                            amount
                    );

            expenseList.add(expense);

            ExpenseManager.saveExpenses(
                    expenseList
            );

            updateTotal();

            datePicker.setValue(null);
            categoryBox.setValue(null);
            amountField.clear();

        } catch (NumberFormatException e) {

            showError(
                    "Please enter a valid amount."
            );
        }
    }

    private void deleteExpense() {

        Expense selectedExpense =
                table.getSelectionModel()
                        .getSelectedItem();

        if (selectedExpense != null) {

            expenseList.remove(
                    selectedExpense
            );

            ExpenseManager.saveExpenses(
                    expenseList
            );

            updateTotal();
        }
    }

    private void updateTotal() {

        double total = 0;

        for (Expense expense : expenseList) {

            total += expense.getAmount();
        }

        totalLabel.setText(
                "Total Expenses: ₹" +
                String.format("%.2f", total)
        );
    }

    private void showError(String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle("Input Error");

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void main(String[] args) {

        launch(args);
    }
}