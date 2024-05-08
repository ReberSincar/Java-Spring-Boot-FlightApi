package com.rebersincar.FlightApi.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginDto {
    @NotBlank(message = "username can not be null or blank")
    @Length(min = 3, message = "username min length should be 3")
    private String username;

    @NotBlank(message = "password can not be null or blank")
    @Length(min = 6, message = "password min length should be 6")
    private String password;
}
