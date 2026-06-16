import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ApiClient handles network communication with the ExchangeRate-API.
 */
public class ApiClient {

    private static final String API_URL_BASE = "https://open.er-api.com/v6/latest/";
    private final int timeoutMs;

    public ApiClient() {
        this(5000); // 5 seconds default timeout
    }

    public ApiClient(int timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    /**
     * Fetches the exchange rates JSON for the given base currency.
     *
     * @param baseCurrency The 3-letter currency code (e.g. USD)
     * @return Raw JSON response string from the API
     * @throws IOException If a network timeout or error occurs
     */
    public String fetchLatestRates(String baseCurrency) throws IOException {
        String urlString = API_URL_BASE + baseCurrency.toUpperCase();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(timeoutMs);
        conn.setReadTimeout(timeoutMs);

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            }
        } else if (responseCode == 404 || responseCode == 403) {
            throw new IOException("Currency code not supported by API (HTTP " + responseCode + ").");
        } else {
            throw new IOException("Server returned HTTP " + responseCode);
        }
    }
}
