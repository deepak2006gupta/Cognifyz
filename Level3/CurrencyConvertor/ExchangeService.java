import java.io.IOException;

/**
 * ExchangeService orchestrates the retrieval and parsing of exchange rates,
 * validating inputs, and calculating converted currency values.
 */
public class ExchangeService {

    private final ApiClient apiClient;
    private final ExchangeRateParser rateParser;

    public ExchangeService() {
        this(new ApiClient(), new ExchangeRateParser());
    }

    public ExchangeService(ApiClient apiClient, ExchangeRateParser rateParser) {
        this.apiClient = apiClient;
        this.rateParser = rateParser;
    }

    /**
     * Validates and performs a currency conversion.
     *
     * @param baseCurrency   The base currency code (e.g. USD)
     * @param targetCurrency The target currency code (e.g. EUR)
     * @param amount         The amount in the base currency to convert
     * @return A ConversionResult holding rates and final converted amount.
     * @throws IOException              If API retrieval fails (network/timeout)
     * @throws IllegalArgumentException If currencies are invalid or parsing fails
     */
    public ConversionResult convert(String baseCurrency, String targetCurrency, double amount)
            throws IOException, IllegalArgumentException {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (!isValidCurrencyCode(baseCurrency)) {
            throw new IllegalArgumentException("Invalid base currency code format.");
        }
        if (!isValidCurrencyCode(targetCurrency)) {
            throw new IllegalArgumentException("Invalid target currency code format.");
        }

        // Fetch live exchange rates JSON payload
        String ratesJson = apiClient.fetchLatestRates(baseCurrency);

        // Parse conversion rate
        double rate = rateParser.getExchangeRate(ratesJson, targetCurrency);

        // Perform calculation
        double convertedAmount = amount * rate;

        return new ConversionResult(baseCurrency, targetCurrency, amount, rate, convertedAmount);
    }

    /**
     * Checks if a currency code string conforms to the 3-letter ISO format.
     */
    public boolean isValidCurrencyCode(String code) {
        return code != null && code.matches("^[A-Z]{3}$");
    }

    /**
     * Inner class to represent structured conversion output.
     */
    public static class ConversionResult {
        private final String baseCurrency;
        private final String targetCurrency;
        private final double amount;
        private final double rate;
        private final double convertedAmount;

        public ConversionResult(String base, String target, double amount, double rate, double convertedAmount) {
            this.baseCurrency = base;
            this.targetCurrency = target;
            this.amount = amount;
            this.rate = rate;
            this.convertedAmount = convertedAmount;
        }

        public String getBaseCurrency() { return baseCurrency; }
        public String getTargetCurrency() { return targetCurrency; }
        public double getAmount() { return amount; }
        public double getRate() { return rate; }
        public double getConvertedAmount() { return convertedAmount; }
    }
}
