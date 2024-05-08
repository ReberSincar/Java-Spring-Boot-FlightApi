package com.rebersincar.FlightApi.ticket.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BuyTicketDto {
    @NotNull(message = "flightId can not be null")
    Long flightId;

    @NotNull(message = "userId can not be null")
    Long userId;
}
