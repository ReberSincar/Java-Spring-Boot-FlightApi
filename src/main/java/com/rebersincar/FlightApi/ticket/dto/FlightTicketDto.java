package com.rebersincar.FlightApi.ticket.dto;

import com.rebersincar.FlightApi.user.dto.UserDto;
import lombok.Data;

import java.util.Date;

@Data
public class FlightTicketDto {
    private Long id;
    private Long userId;
    private UserDto user;
    private Long flightId;
    private Date createdAt;
    private Date updatedAt;
}

