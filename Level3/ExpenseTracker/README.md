# Expense Tracker

A simple JavaFX-based desktop application for managing daily expenses. Users can add expenses by selecting a date, category, and amount, view all recorded expenses in a table, delete entries, and track total spending. Expense data is stored locally in a text file for persistence.

## Features

* Add expenses with date, category, and amount
* View expenses in a table
* Delete selected expenses
* Automatic total expense calculation
* Local file-based storage

## Project Files

```text
Expense.java
ExpenseManager.java
ExpenseTrackerApp.java
Expenses.txt
```

## Requirements

* Java JDK 24 or later
* JavaFX SDK 26 or later

Download the JavaFX SDK from:

https://gluonhq.com/products/javafx/

## Compile

Replace `<path-to-javafx-lib>` with the path to your JavaFX SDK's `lib` folder.

```bash
javac --module-path "<path-to-javafx-lib>" --add-modules javafx.controls *.java
```

## Run

```bash
java --module-path "<path-to-javafx-lib>" --add-modules javafx.controls ExpenseTrackerApp
```

## Author

Deepak Prasad Gupta
Java Developer Intern – Cognifyz Technologies
