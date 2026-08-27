package service;

import model.ElectricityPrice;

import java.time.LocalDate;
import java.time.LocalTime;
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
                + String.format("%.2f", minOre) + " ören.");
        IO.println("Den högsta kostnaden per kWh för "
                + electricityArea + " idag var "
                + String.format("%.2f", maxOre) + " ören.");
        IO.println("Genomsnittskostnaden var "
                + String.format("%.2f", averageOre) + " ören");
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
}
