package com.rebersincar.FlightApi.flight.mapper;

import com.rebersincar.FlightApi.flight.dto.FlightDto;
import com.rebersincar.FlightApi.flight.model.Flight;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FlightsMapper {
    public FlightDto toFlightDto(Flight flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setCompany(flight.getCompany());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setAirplaneModel(flight.getAirplaneModel());
        flightDto.setOrigin(flight.getOrigin());
        flightDto.setDestination(flight.getDestination());
        flightDto.setDepartureTime(flight.getDepartureTime());
        flightDto.setArrivalTime(flight.getArrivalTime());
        flightDto.setPrice(flight.getPrice());
        flightDto.setCreatedAt(flight.getCreatedAt());
        flightDto.setUpdatedAt(flight.getUpdatedAt());
        return flightDto;
    }
}
