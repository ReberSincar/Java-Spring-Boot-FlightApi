package com.rebersincar.FlightApi.ticket.dto;

import com.rebersincar.FlightApi.flight.dto.FlightDto;
import lombok.Data;

import java.util.Date;

@Data
public class UserTicketDto {
    private Long id;
    private Long userId;
    private Long flightId;
    private FlightDto flight;
    private Date createdAt;
    private Date updatedAt;
}

