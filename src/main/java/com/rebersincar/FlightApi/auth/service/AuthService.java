package com.rebersincar.FlightApi.auth.service;

import com.rebersincar.FlightApi.auth.dto.LoginDto;
import com.rebersincar.FlightApi.auth.dto.RegisterDto;
import com.rebersincar.FlightApi.exception.ConflictException;
import com.rebersincar.FlightApi.exception.NotFoundException;
import com.rebersincar.FlightApi.security.JwtService;
import com.rebersincar.FlightApi.user.dto.UserDto;
import com.rebersincar.FlightApi.user.model.Role;
import com.rebersincar.FlightApi.user.model.User;
import com.rebersincar.FlightApi.user.repository.UserRepository;
import com.rebersincar.FlightApi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(
            UserRepository userRepository,
            UserService userService,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<String> login(LoginDto dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(jwtService.generateToken(dto.getUsername()));
        }
        throw new NotFoundException(STR."invalid username {} \{dto.getUsername()}");
    }

    public ResponseEntity<UserDto> register(RegisterDto dto) {
        Optional<User> userResult = userRepository.findByEmailOrUsername(dto.getEmail(), dto.getUsername());
        if (userResult.isPresent()) throw new ConflictException("Email already using");

        return userService.createUser(dto, Role.USER);
    }
}
