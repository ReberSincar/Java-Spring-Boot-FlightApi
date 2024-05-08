package com.rebersincar.FlightApi.flight.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
public class UpdateFlightDto {
    @Length(min = 3, max = 50, message = "company must be between 3 and 50 characters")
    String company;

    @Length(min = 3, max = 8, message = "flighNumber must be between 3 and 8 characters")
    String flightNumber;

    @Length(min = 3, max = 10, message = "airplaneModel must be between 3 and 10 characters")
    String airplaneModel;

    String origin;

    String destination;

    Date departureTime;

    Date arrivalTime;

    @Min(value = 100, message = "price min value must be 100")
    float price;
}
