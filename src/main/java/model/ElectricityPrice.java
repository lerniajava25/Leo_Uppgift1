package model;

import java.time.OffsetDateTime;

public record ElectricityPrice(
        double SEK_per_kWh,
        double EUR_per_kWh,
        double EXR,
        OffsetDateTime time_start,
        OffsetDateTime time_end){
}
