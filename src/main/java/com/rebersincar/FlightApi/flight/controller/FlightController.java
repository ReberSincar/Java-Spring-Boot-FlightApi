package com.rebersincar.FlightApi.flight.controller;

import com.rebersincar.FlightApi.flight.dto.AddFlightDto;
import com.rebersincar.FlightApi.flight.dto.FlightDto;
import com.rebersincar.FlightApi.flight.dto.UpdateFlightDto;
import com.rebersincar.FlightApi.flight.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<FlightDto> addFlight(@Valid @RequestBody AddFlightDto dto) {
        return flightService.addFlight(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long id, @Valid @RequestBody UpdateFlightDto dto) {
        return flightService.updateFlight(id, dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
        return flightService.deleteFlight(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<FlightDto> findFlightById(@PathVariable Long id) {
        return flightService.findFlightById(id);
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return flightService.getAllFlights();
    }
}
