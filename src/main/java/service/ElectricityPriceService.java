package service;

import model.ElectricityPrice;


public class ElectricityPriceService {

    public void printMinMaxAverage
            (ElectricityPrice[] prices,
             String electricityArea){


        //Calculate min, max and average
        double min = prices[0].SEK_per_kWh();
        double max = prices[0].SEK_per_kWh();
        double total = 0;

        for (ElectricityPrice price : prices) {
            if (price.SEK_per_kWh() < min){
                min = price.SEK_per_kWh();
            }

            if (price.SEK_per_kWh() > max){
                max = price.SEK_per_kWh();
            }

            total += price.SEK_per_kWh();
        }

        double average = total/prices.length;

        double minOre = min * 100;
        double maxOre = max * 100;
        double averageOre = average * 100;

        IO.println("Den lägsta kostnaden per kWh för "
                + electricityArea + " idag var "
                + String.format("%.2f", minOre)  + " ören.");
        IO.println("Den högsta kostnaden per kWh för "
                + electricityArea + " idag var "
                + String.format("%.2f", maxOre) + " ören.");
        IO.println("Genomsnittskostnaden var "
                + String.format("%.2f", averageOre) + " ören");
    }

}
