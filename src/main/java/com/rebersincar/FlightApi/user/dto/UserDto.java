package com.rebersincar.FlightApi.user.dto;

import com.rebersincar.FlightApi.user.model.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Date createdAt;
    private Date updatedAt;
    private Set<Role> authorities;
}
