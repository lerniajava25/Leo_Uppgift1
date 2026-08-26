package client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ElectricityPriceClient {
    //Add  https://www.elprisetjustnu.se/api/v1/prices/{ÅR}/{MÅNAD}-{DAG}_{ELOMRÅDE}.json as parameters

    //To get the correct date for API-endpoint
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public String fetchElectricityPrices() {
        HttpClient httpClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_3)
                .build();

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create("https://www.elprisetjustnu.se/api/v1/prices/2026/08-10_SE3.json"))
                .build();

        HttpResponse<String> response = null;

        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body() + LocalDateTime.now().format(dtf);
    }
}
