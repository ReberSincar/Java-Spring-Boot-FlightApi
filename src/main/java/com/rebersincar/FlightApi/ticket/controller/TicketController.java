package com.rebersincar.FlightApi.ticket.controller;

import com.rebersincar.FlightApi.ticket.dto.*;
import com.rebersincar.FlightApi.ticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketDto> buyTicket(@Valid @RequestBody BuyTicketDto dto) {
        return ticketService.buyTicket(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> cancelTicket(@Valid @RequestBody CancelTicketDto dto) {
        return ticketService.cancelTicket(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketDto> findTicketById(@PathVariable Long id) {
        return ticketService.findTicketById(id);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<UserTicketDto>> getUserTickets(@PathVariable Long id) {
        return ticketService.getUserTickets(id);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<List<FlightTicketDto>> getFlightTickets(@PathVariable Long id) {
        return ticketService.getFlightTickets(id);
    }
}
