import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ExchangeRateParser parses currency exchange rates from JSON API responses.
 */
public class ExchangeRateParser {

    /**
     * Parses the exchange rate for the target currency from the given JSON string.
     *
     * @param json           The JSON response containing rates
     * @param targetCurrency The 3-letter currency code to parse (e.g. EUR)
     * @return The exchange rate as a double
     * @throws IllegalArgumentException If JSON is malformed or currency is unsupported
     */
    public double getExchangeRate(String json, String targetCurrency) throws IllegalArgumentException {
        // Validate if the JSON states result is success
        if (!json.contains("\"result\":\"success\"") && !json.contains("\"result\": \"success\"")) {
            throw new IllegalArgumentException("API response indicates failure.");
        }

        // Find the rates block: "rates":{ ... }
        int ratesIndex = json.indexOf("\"rates\"");
        if (ratesIndex == -1) {
            throw new IllegalArgumentException("Exchange rates not found in API response.");
        }

        int openBrace = json.indexOf("{", ratesIndex);
        if (openBrace == -1) {
            throw new IllegalArgumentException("Invalid rates format in API response.");
        }

        int closeBrace = json.indexOf("}", openBrace);
        if (closeBrace == -1) {
            throw new IllegalArgumentException("Invalid rates format in API response.");
        }

        String ratesSubstring = json.substring(openBrace + 1, closeBrace);

        // Pattern matching: "TARGET":rate (rate can be integer, floating point, or exponential format)
        String patternStr = "\"" + targetCurrency.toUpperCase() + "\"\\s*:\\s*(\\d+(\\.\\d+)?([eE][+-]?\\d+)?)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(ratesSubstring);

        if (matcher.find()) {
            try {
                return Double.parseDouble(matcher.group(1));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Failed to parse rate value for " + targetCurrency);
            }
        } else {
            throw new IllegalArgumentException("Unsupported or invalid target currency: '" + targetCurrency + "'");
        }
    }
}
