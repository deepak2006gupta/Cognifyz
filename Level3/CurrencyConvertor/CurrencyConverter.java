import java.io.IOException;
import java.util.Scanner;

/**
 * CurrencyConverter is the command-line interface entry point for the
 * multi-class currency converter application.
 */
public class CurrencyConverter {

    private static final String POPULAR_CURRENCIES = "USD, EUR, GBP, INR, JPY, CAD, AUD, CHF, CNY, ZAR";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeService exchangeService = new ExchangeService();
        boolean running = true;

        System.out.println("==================================================");
        System.out.println("          REAL-TIME CURRENCY CONVERTER           ");
        System.out.println("==================================================");
        System.out.println("Supported Currencies include standard ISO codes.");
        System.out.println("Popular: " + POPULAR_CURRENCIES);
        System.out.println("==================================================");

        while (running) {
            try {
                // 1. Get Base Currency
                System.out.print("\nEnter Base Currency (e.g., USD, EUR, INR): ");
                String baseCurrency = scanner.nextLine().trim().toUpperCase();
                if (!exchangeService.isValidCurrencyCode(baseCurrency)) {
                    System.out.println("Error: Invalid base currency format. Please enter a 3-letter currency code.");
                    continue;
                }

                // 2. Get Target Currency
                System.out.print("Enter Target Currency (e.g., EUR, INR, GBP): ");
                String targetCurrency = scanner.nextLine().trim().toUpperCase();
                if (!exchangeService.isValidCurrencyCode(targetCurrency)) {
                    System.out.println("Error: Invalid target currency format. Please enter a 3-letter currency code.");
                    continue;
                }

                // 3. Get Amount
                double amount = -1;
                while (amount <= 0) {
                    System.out.print("Enter amount in " + baseCurrency + ": ");
                    String amountInput = scanner.nextLine().trim();
                    try {
                        amount = Double.parseDouble(amountInput);
                        if (amount <= 0) {
                            System.out.println("Error: Amount must be greater than zero.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid number format. Please enter a valid positive number.");
                    }
                }

                // 4. Perform conversion via ExchangeService
                System.out.println("Fetching rates and converting...");
                ExchangeService.ConversionResult result = exchangeService.convert(baseCurrency, targetCurrency, amount);

                // 5. Display Results
                System.out.println("\n--------------------------------------------------");
                System.out.printf("  Exchange Rate (1 %s): %.4f %s\n",
                        result.getBaseCurrency(), result.getRate(), result.getTargetCurrency());
                System.out.printf("  Converted Amount:  %,.2f %s\n",
                        result.getAmount(), result.getBaseCurrency());
                System.out.printf("  Result:            %,.4f %s\n",
                        result.getConvertedAmount(), result.getTargetCurrency());
                System.out.println("--------------------------------------------------");

            } catch (IOException e) {
                System.out.println("\nNetwork Error: Unable to fetch live exchange rates.");
                System.out.println("Details: " + e.getMessage());
                System.out.println("Please check your internet connection and try again.");
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\nAn unexpected error occurred: " + e.getMessage());
            }

            // Ask user if they want to perform another conversion
            System.out.print("\nDo you want to perform another conversion? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                running = false;
            }
        }

        System.out.println("\n==================================================");
        System.out.println("   Thank you for using Real-Time Currency Converter!  ");
        System.out.println("==================================================");
        scanner.close();
    }
}
