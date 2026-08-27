package client;

import model.ElectricityPrice;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ElectricityPriceClient {
    String baseUri = "https://www.elprisetjustnu.se/api/v1/prices/";
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM-dd");

    public ElectricityPrice[] fetchElectricityPrices(String electricityArea)
            throws IOException, InterruptedException {
        String dateToday = LocalDate.now().format(dtf);

        HttpClient httpClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_3)
                .build();

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(baseUri + dateToday + "_" + electricityArea + ".json"))
                .timeout(Duration.ofSeconds(10))
                .build();

        HttpResponse<String> response = httpClient.send(
                httpRequest,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException(
                    "API returned HTTP status " + response.statusCode()
            );
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            ElectricityPrice[] prices = mapper.readValue(response.body(), ElectricityPrice[].class);

            if (prices == null || prices.length == 0) {
                throw new IOException("API returned no electricity prices");
            }
            return prices;
        } catch (JacksonException e) {
            throw new IOException("Could not parse electricity price data", e);
        }
    }
}
