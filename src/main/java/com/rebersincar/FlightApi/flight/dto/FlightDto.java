package com.rebersincar.FlightApi.flight.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FlightDto {
    Long id;
    String company;
    String flightNumber;
    String airplaneModel;
    String origin;
    String destination;
    Date departureTime;
    Date arrivalTime;
    float price;
    Date createdAt;
    Date updatedAt;
}
