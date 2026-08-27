package client;

import model.ElectricityPrice;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
                .build();

        HttpResponse<String> response = httpClient.send(
                httpRequest,
                HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        ElectricityPrice[] prices = mapper.readValue(response.body(), ElectricityPrice[].class);
        return prices;
    }
}
