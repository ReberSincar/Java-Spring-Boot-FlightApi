package com.rebersincar.FlightApi.ticket.service;

import com.rebersincar.FlightApi.exception.NotFoundException;
import com.rebersincar.FlightApi.flight.model.Flight;
import com.rebersincar.FlightApi.flight.service.FlightService;
import com.rebersincar.FlightApi.ticket.dto.*;
import com.rebersincar.FlightApi.ticket.mapper.TicketMapper;
import com.rebersincar.FlightApi.ticket.model.Ticket;
import com.rebersincar.FlightApi.ticket.repository.TicketRepository;
import com.rebersincar.FlightApi.user.model.User;
import com.rebersincar.FlightApi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final FlightService flightService;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserService userService, FlightService flightService, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.flightService = flightService;
        this.ticketMapper = ticketMapper;
    }

    private Ticket checkTicketExist(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) throw new NotFoundException("Ticket not found");
        return ticket.get();
    }

    public ResponseEntity<TicketDto> buyTicket(BuyTicketDto dto) {
        Flight flight = flightService.checkFlightExist(dto.getFlightId());
        User user = userService.checkUserExist(dto.getUserId());

        Ticket ticket = Ticket.builder()
                .user(user)
                .flight(flight)
                .build();

        ticket = ticketRepository.save(ticket);
        return ResponseEntity.ok().body(ticketMapper.toTicketDto(ticket));
    }

    public ResponseEntity<?> cancelTicket(CancelTicketDto dto) {
        checkTicketExist(dto.getTicketId());
        ticketRepository.deleteById(dto.getTicketId());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<TicketDto> findTicketById(Long id) {
        Ticket ticket = checkTicketExist(id);
        return ResponseEntity.ok().body(ticketMapper.toTicketDto((ticket)));

    }

    public ResponseEntity<List<UserTicketDto>> getUserTickets(Long userId) {
        List<Ticket> ticketList = ticketRepository.findAllByUserId(userId);
        List<UserTicketDto> ticketDtos = ticketList.stream().map(ticketMapper::toUserTicketDto).toList();
        return ResponseEntity.ok().body(ticketDtos);
    }

    public ResponseEntity<List<FlightTicketDto>> getFlightTickets(Long flightId) {
        List<Ticket> ticketList = ticketRepository.findAllByFlightId(flightId);
        List<FlightTicketDto> ticketDtos = ticketList.stream().map(ticketMapper::toFlightTicketDto).toList();
        return ResponseEntity.ok().body(ticketDtos);
    }
}
