package com.rebersincar.FlightApi.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class RegisterDto {
    @NotBlank(message = "name can not be null or blank")
    @Length(min = 2, max = 50, message = "name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "surname can not be null or blank")
    @Length(min = 2, max = 50, message = "name must be between 2 and 50 characters")
    private String surname;

    @NotBlank(message = "email can not be null or blank")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "username can not be null or blank")
    @Length(min = 2, max = 10, message = "name must be between 2 and 10 characters")
    private String username;

    @NotBlank(message = "password can not be null or blank")
    @Length(min = 6, message = "password must be min 6 characters")
    private String password;
}
