package com.rebersincar.FlightApi.auth.controller;

import com.rebersincar.FlightApi.auth.dto.LoginDto;
import com.rebersincar.FlightApi.auth.dto.RegisterDto;
import com.rebersincar.FlightApi.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto dto) {
        return authService.login(dto);
    }
}
