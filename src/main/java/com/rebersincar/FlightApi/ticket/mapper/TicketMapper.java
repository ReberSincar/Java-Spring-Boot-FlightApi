package com.rebersincar.FlightApi.ticket.mapper;

import com.rebersincar.FlightApi.flight.mapper.FlightsMapper;
import com.rebersincar.FlightApi.ticket.dto.FlightTicketDto;
import com.rebersincar.FlightApi.ticket.dto.UserTicketDto;
import com.rebersincar.FlightApi.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rebersincar.FlightApi.ticket.model.Ticket;
import com.rebersincar.FlightApi.ticket.dto.TicketDto;

@Component
public class TicketMapper {

    private final FlightsMapper flightsMapper;
    private final UserMapper userMapper;

    @Autowired
    public TicketMapper(FlightsMapper flightsMapper, UserMapper userMapper) {
        this.flightsMapper = flightsMapper;
        this.userMapper = userMapper;
    }

    public TicketDto toTicketDto(Ticket ticket) {
        TicketDto ticketDTO = new TicketDto();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setUserId(ticket.getUser().getId());
        ticketDTO.setUser(userMapper.toUserDto(ticket.getUser()));
        ticketDTO.setFlightId(ticket.getFlight().getId());
        ticketDTO.setFlight(flightsMapper.toFlightDto(ticket.getFlight()));
        ticketDTO.setCreatedAt(ticket.getCreatedAt());
        ticketDTO.setUpdatedAt(ticket.getUpdatedAt());
        return ticketDTO;
    }

    public UserTicketDto toUserTicketDto(Ticket ticket) {
        UserTicketDto ticketDTO = new UserTicketDto();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setUserId(ticket.getUser().getId());
        ticketDTO.setFlightId(ticket.getFlight().getId());
        ticketDTO.setFlight(flightsMapper.toFlightDto(ticket.getFlight()));
        ticketDTO.setCreatedAt(ticket.getCreatedAt());
        ticketDTO.setUpdatedAt(ticket.getUpdatedAt());
        return ticketDTO;
    }

    public FlightTicketDto toFlightTicketDto(Ticket ticket) {
        FlightTicketDto ticketDTO = new FlightTicketDto();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setUserId(ticket.getUser().getId());
        ticketDTO.setUser(userMapper.toUserDto(ticket.getUser()));
        ticketDTO.setFlightId(ticket.getFlight().getId());
        ticketDTO.setCreatedAt(ticket.getCreatedAt());
        ticketDTO.setUpdatedAt(ticket.getUpdatedAt());
        return ticketDTO;
    }
}

