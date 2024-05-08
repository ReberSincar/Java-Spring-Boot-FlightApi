package com.rebersincar.FlightApi.user.service;

import com.rebersincar.FlightApi.auth.dto.RegisterDto;
import com.rebersincar.FlightApi.exception.NotFoundException;
import com.rebersincar.FlightApi.user.dto.UserDto;
import com.rebersincar.FlightApi.user.mapper.UserMapper;
import com.rebersincar.FlightApi.user.model.Role;
import com.rebersincar.FlightApi.user.model.User;
import com.rebersincar.FlightApi.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public User checkUserExist(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new NotFoundException("User not found");
        return user.get();
    }

    public boolean checkUserExistByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    public ResponseEntity<UserDto> createUser(RegisterDto dto, Role role) {
        Set<Role> authorities = new HashSet<>();
        authorities.add(role);

        User newUser = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .authorities(authorities)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();
        newUser = userRepository.save(newUser);
        return ResponseEntity.ok(userMapper.toUserDto(newUser));
    }

    public ResponseEntity<UserDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(userMapper.toUserDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(userMapper::toUserDto).toList();
        return ResponseEntity.ok(userDtos);
    }

    public ResponseEntity deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throw new NotFoundException("Wrong credentials");
        return user.get();
    }
}
