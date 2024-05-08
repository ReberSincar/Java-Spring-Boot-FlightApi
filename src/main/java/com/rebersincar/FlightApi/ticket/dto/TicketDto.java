package com.rebersincar.FlightApi.ticket.dto;

import com.rebersincar.FlightApi.flight.dto.FlightDto;
import com.rebersincar.FlightApi.user.dto.UserDto;
import lombok.Data;

import java.util.Date;

@Data
public class TicketDto {
    private Long id;
    private Long userId;
    private UserDto user;
    private Long flightId;
    private FlightDto flight;
    private Date createdAt;
    private Date updatedAt;
}

