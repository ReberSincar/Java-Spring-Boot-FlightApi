package com.rebersincar.FlightApi.flight.repository;

import com.rebersincar.FlightApi.flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
