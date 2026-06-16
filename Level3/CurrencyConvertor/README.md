# Currency Converter

A modular, multi-class Java command-line application that performs real-time currency conversions by fetching current exchange rates from a live API.

---

## Project Overview
This application prompts the user to input a base currency, a target currency, and a monetary amount. It fetches the current exchange rate from a free public API, parses the response, performs the conversion, and displays the formatted result.

---

## Technologies Used
- **Java Standard Library (JDK 8+)**: The programming language and core runtime platform.
- **`HttpURLConnection`**: Fetches exchange rate data from the live API (`https://open.er-api.com/v6/latest/`) over HTTP GET.
- **Regular Expressions (Regex)**: Parses specific currency values out of the JSON response payload, avoiding any third-party library dependencies.
- **Console I/O (`Scanner`)**: Handles command-line user inputs and formats conversions.

---

## Setup & Run Guide

### Prerequisites
- Java Development Kit (JDK) 8 or later.

### How to Run
1. Open your terminal/command prompt and navigate to this folder.
2. Compile all source files:
   ```bash
   javac *.java
   ```
3. Run the application:
   ```bash
   java CurrencyConverter
   ```
