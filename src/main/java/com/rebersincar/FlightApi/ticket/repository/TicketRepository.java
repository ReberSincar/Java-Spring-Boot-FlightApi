package com.rebersincar.FlightApi.ticket.repository;

import com.rebersincar.FlightApi.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByUserId(Long userId);

    List<Ticket> findAllByFlightId(Long flightId);
}
