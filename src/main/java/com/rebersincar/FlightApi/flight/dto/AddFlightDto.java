package com.rebersincar.FlightApi.flight.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
public class AddFlightDto {
    @NotEmpty(message = "company can not be null or blank")
    @Length(min = 3, max = 50, message = "company must be between 3 and 50 characters")
    String company;

    @NotEmpty(message = "flightNumber can not be null or blank")
    @Length(min = 3, max = 8, message = "flighNumber must be between 3 and 8 characters")
    String flightNumber;

    @NotEmpty(message = "airplaneModel can not be null or blank")
    @Length(min = 3, max = 10, message = "airplaneModel must be between 3 and 10 characters")
    String airplaneModel;

    @NotEmpty(message = "origin can not be null or blank")
    String origin;

    @NotEmpty(message = "destination can not be null or blank")
    String destination;

    @NotNull(message = "departureTime can not be null or blank")
    Date departureTime;

    @NotNull(message = "arrivalTime can not be null or blank")
    Date arrivalTime;

    @NotNull(message = "price can not be null or blank")
    @Min(value = 100, message = "price min value must be 100")
    float price;
}
