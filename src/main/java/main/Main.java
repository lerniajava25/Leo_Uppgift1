//API www.elprisetjustnu.se
//API-ändpunkt: https://www.elprisetjustnu.se/api/v1/prices/{ÅR}/{MÅNAD}-{DAG}_{ELOMRÅDE}.json
//Exempel: https://www.elprisetjustnu.se/api/v1/prices/2026/08-10_SE3.json
//End program with "e" or "E"
package main;

public class Main {
    static void main(){

        String choice = "";
        String electricityArea= "";

        while(!choice.toLowerCase().equals("e")){
            IO.println("Elpriser – Analysverktyg");
            IO.println("1. Välj elområde (SE1, SE2, SE3, SE4)");
            IO.println("2. Min, Max och Medelpris");
            IO.println("3. Sortera priser (lägst till högst)");
            IO.println("4. Bästa laddningstid (4h sammanhängande)");
            IO.println("e. Avsluta");

            choice = IO.readln("Välj ett alternativ");

            switch(choice.toLowerCase()){
                case "1":
                    electricityArea = IO.readln("Välj ditt elprisområde (SE1, SE2, SE3, SE4)");
                    break;
                case "2":
                    IO.println("min, max, average");
                    //Calculate min, max and average
                    break;
                case "3":
                    //Sort prices
                    break;
                case "4":
                    //Best loading time
                    break;
                case "e":
                    break;
                default:
                    IO.println("Något gick fel, försök igen");
                    break;
            }

        }
    }

}
