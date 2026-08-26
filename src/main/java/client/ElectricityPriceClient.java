package client;

import model.ElectricityPrice;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ElectricityPriceClient {
    //Add  https://www.elprisetjustnu.se/api/v1/prices/{ÅR}/{MÅNAD}-{DAG}_{ELOMRÅDE}.json as parameters
    String baseUri = "https://www.elprisetjustnu.se/api/v1/prices/";
    //To get the correct date for API-endpoint
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM-dd");
    String dateToday = LocalDate.now().format(dtf);


    public ElectricityPrice[] fetchElectricityPrices(String electricityArea) {
        HttpClient httpClient = HttpClient
                .newBuilder()
                .version(HttpClient.Version.HTTP_3)
                .build();

        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(baseUri + dateToday + "_" + electricityArea + ".json"))
                .build();

        HttpResponse<String> response = null;

        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        ElectricityPrice[] prices = mapper.readValue(response.body(), ElectricityPrice[].class);
        return prices;
    }
}
