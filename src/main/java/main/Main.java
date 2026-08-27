
package main;

import client.ElectricityPriceClient;
import model.ElectricityPrice;
import service.ElectricityPriceService;

public class Main {
    static void main() {
        ElectricityPriceClient client = new ElectricityPriceClient();
        ElectricityPriceService priceService = new ElectricityPriceService();
        ElectricityPrice[] prices = null;
        String choice = "";
        String electricityArea = "";
        String[] validAreas = {"SE1", "SE2", "SE3", "SE4"};

        while (!choice.equalsIgnoreCase("e")) {
            IO.println("Elpriser – Analysverktyg");
            IO.println("1. Välj elområde (SE1, SE2, SE3, SE4)");
            IO.println("2. Min, Max och Medelpris");
            IO.println("3. Sortera priser (lägst till högst)");
            IO.println("4. Bästa laddningstid (4h sammanhängande)");
            IO.println("e. Avsluta");

            choice = IO.readln("Välj ett alternativ");

            if (electricityArea.isEmpty() && (choice.equals("2") || choice.equals("3") || choice.equals("4"))) {
                IO.println("Du måste välja ett elprisområde först!");
                continue;
            }

            switch (choice.toLowerCase()) {
                case "1":
                    String tempElectricityArea = IO.readln("Välj ditt elprisområde (SE1, SE2, SE3, SE4)");

                    if (tempElectricityArea.isEmpty() || !isValidArea(tempElectricityArea, validAreas)) {
                        IO.println("Ogiltigt område, försök igen!");
                        break;
                    }
                    electricityArea = tempElectricityArea.toUpperCase();
                    prices = client.fetchElectricityPrices(electricityArea);
                    break;
                case "2":
                    priceService.printMinMaxAverage(prices, electricityArea);
                    break;
                case "3":
                    IO.println("Dagens priser för område "
                            + electricityArea
                            + " sorterat från lägst till högst:");
                    priceService.sortPrices(prices);
                    break;
                case "4":
                    priceService.findBestChargingTime(prices);
                    break;
                case "e":
                    break;
                default:
                    IO.println("Ogiltigt val, försök igen");
                    break;
            }

        }
    }

    private static boolean isValidArea(String area, String[] validAreas) {
        for (String validArea : validAreas) {
            if (validArea.equalsIgnoreCase(area)) {
                return true;
            }
        }
        return false;
    }
}
