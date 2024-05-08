package com.rebersincar.FlightApi.user.controller;

import com.rebersincar.FlightApi.auth.dto.RegisterDto;
import com.rebersincar.FlightApi.user.dto.UserDto;
import com.rebersincar.FlightApi.user.model.Role;
import com.rebersincar.FlightApi.user.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        boolean isAdminExist = userService.checkUserExistByEmail(adminEmail);
        if (!isAdminExist) {
            RegisterDto registerDto = new RegisterDto("ADMIN", "ADMIN", adminEmail, "ADMIN", adminPassword);
            userService.createUser(registerDto, Role.ADMIN);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        return userService.deleteUser(id);
    }
}
