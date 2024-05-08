package com.rebersincar.FlightApi.ticket.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancelTicketDto {
    @NotNull(message = "ticketId can not be null")
    Long ticketId;
}
