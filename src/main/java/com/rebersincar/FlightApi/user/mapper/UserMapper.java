package com.rebersincar.FlightApi.user.mapper;

import com.rebersincar.FlightApi.user.dto.UserDto;
import com.rebersincar.FlightApi.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        UserDto toUserDto = new UserDto();
        toUserDto.setId(user.getId());
        toUserDto.setUsername(user.getUsername());
        toUserDto.setName(user.getName());
        toUserDto.setSurname(user.getSurname());
        toUserDto.setEmail(user.getEmail());
        toUserDto.setAuthorities(user.getAuthorities());
        toUserDto.setCreatedAt(user.getCreatedAt());
        toUserDto.setUpdatedAt(user.getUpdatedAt());
        return toUserDto;
    }
}
