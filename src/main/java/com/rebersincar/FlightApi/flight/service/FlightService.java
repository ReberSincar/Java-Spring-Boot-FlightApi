package com.rebersincar.FlightApi.flight.service;

import com.rebersincar.FlightApi.exception.NotFoundException;
import com.rebersincar.FlightApi.flight.dto.AddFlightDto;
import com.rebersincar.FlightApi.flight.dto.FlightDto;
import com.rebersincar.FlightApi.flight.dto.UpdateFlightDto;
import com.rebersincar.FlightApi.flight.mapper.FlightsMapper;
import com.rebersincar.FlightApi.flight.model.Flight;
import com.rebersincar.FlightApi.flight.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightsMapper flightMapper;

    @Autowired
    public FlightService(FlightRepository flightRepository, FlightsMapper flightsMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightsMapper;
    }

    public Flight checkFlightExist(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        if (flight.isEmpty()) throw new NotFoundException("Flight not found");
        return flight.get();
    }

    @Transactional
    public ResponseEntity<FlightDto> addFlight(AddFlightDto dto) {
        Flight flight = Flight.builder()
                .company(dto.getCompany())
                .flightNumber(dto.getFlightNumber())
                .arrivalTime(dto.getArrivalTime())
                .airplaneModel(dto.getAirplaneModel())
                .price(dto.getPrice())
                .departureTime(dto.getDepartureTime())
                .origin(dto.getOrigin())
                .destination(dto.getDestination())
                .build();

        flight = flightRepository.save(flight);
        return ResponseEntity.ok().body(flightMapper.toFlightDto(flight));
    }

    public ResponseEntity<FlightDto> updateFlight(Long id, UpdateFlightDto dto) {
        Flight flight = checkFlightExist(id);
        if (dto.getCompany() != null) flight.setCompany(dto.getCompany());
        if (dto.getFlightNumber() != null) flight.setFlightNumber(dto.getFlightNumber());
        if (dto.getArrivalTime() != null) flight.setArrivalTime(dto.getArrivalTime());
        if (dto.getAirplaneModel() != null) flight.setAirplaneModel(dto.getAirplaneModel());
        if (dto.getPrice() != 0.0f) flight.setPrice(dto.getPrice());
        if (dto.getDepartureTime() != null) flight.setDepartureTime(dto.getDepartureTime());
        if (dto.getOrigin() != null) flight.setOrigin(dto.getOrigin());
        if (dto.getDestination() != null) flight.setDestination(dto.getDestination());

        flight = flightRepository.save(flight);
        return ResponseEntity.ok().body(flightMapper.toFlightDto(flight));
    }

    public ResponseEntity<FlightDto> findFlightById(Long id) {
        Flight flight = checkFlightExist(id);
        return ResponseEntity.ok().body(flightMapper.toFlightDto(flight));

    }

    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<Flight> flightList = flightRepository.findAll();
        List<FlightDto> flightDtos = flightList.stream().map(flightMapper::toFlightDto).toList();
        return ResponseEntity.ok().body(flightDtos);
    }

    public ResponseEntity<?> deleteFlight(Long id) {
        checkFlightExist(id);
        flightRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
