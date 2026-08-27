package service;

import model.ElectricityPrice;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;


public class ElectricityPriceService {

    public void printMinMaxAverage
            (ElectricityPrice[] prices,
             String electricityArea) {

        //Calculate min, max and average
        double min = prices[0].SEK_per_kWh();
        double max = prices[0].SEK_per_kWh();
        double total = 0;

        for (ElectricityPrice price : prices) {
            if (price.SEK_per_kWh() < min) {
                min = price.SEK_per_kWh();
            }

            if (price.SEK_per_kWh() > max) {
                max = price.SEK_per_kWh();
            }

            total += price.SEK_per_kWh();
        }

        double average = total / prices.length;

        double minOre = min * 100;
        double maxOre = max * 100;
        double averageOre = average * 100;

        IO.println("Den lägsta kostnaden per kWh för "
                + electricityArea + " idag var "
                + String.format("%.2f", minOre) + " öre.");
        IO.println("Den högsta kostnaden per kWh för "
                + electricityArea + " idag var "
                + String.format("%.2f", maxOre) + " öre.");
        IO.println("Genomsnittskostnaden var "
                + String.format("%.2f", averageOre) + " öre");
    }

    public void sortPrices(ElectricityPrice[] prices) {
        //Making clone to avoid alternating the order of original array
        ElectricityPrice[] clonedPrices = prices.clone();
        Arrays.sort(clonedPrices,
                Comparator.comparing(
                        ElectricityPrice::SEK_per_kWh
                )
        );

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");


        for (ElectricityPrice price : clonedPrices) {
            IO.println(price.time_start().format((dtf))
                    + "-" + price.time_end().format(dtf)
                    + " | "
                    + String.format("%.2f", (price.SEK_per_kWh()) * 100) + " öre");
        }
    }

    public void findBestChargingTime(ElectricityPrice[] prices) {
        int windowSize = 16;
        double cheapestPrice = 0.0;
        int cheapestIndex = 0;

        //implementation of sliding window for cheapest loading time for 4 hour period
        //Calculating the first 4 hour window
        for (int i = 0; i < windowSize; i++) {
            cheapestPrice += prices[i].SEK_per_kWh();
        }

        double currentPrize = cheapestPrice;

        //Removing the oldest price and adding the next to "slide the window"
        for (int i = windowSize; i < prices.length; i++) {
            currentPrize -= prices[i - windowSize].SEK_per_kWh();
            currentPrize += prices[i].SEK_per_kWh();

            if (currentPrize < cheapestPrice) {
                cheapestPrice = currentPrize;
                cheapestIndex = i - windowSize + 1;
            }
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String startTime = prices[cheapestIndex].time_start().format(dtf);
        String endTime = prices[cheapestIndex + windowSize - 1].time_end().format(dtf);
        double averagePriceOre = (cheapestPrice / windowSize) * 100;

        IO.println("Bästa sammanhängande laddningstiden: "
                + startTime + "-" + endTime
                + " |" + " " + "Genomsnittspris: "
                + String.format("%.2f", averagePriceOre) + " öre/kWh"
        );
    }
}
