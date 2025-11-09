package com.example.cityhubback.service;

import com.example.cityhubback.dto.auth.LoginRequest;
import com.example.cityhubback.dto.auth.RegisterRequest;
import com.example.cityhubback.model.User;
import com.example.cityhubback.model.Role;
import com.example.cityhubback.repository.UserRepository;
import com.example.cityhubback.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email zajęty");
        }

        User user = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(Role.USER)
                .build();

        user = userRepository.save(user);
        return jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name());
    }

    public String login(LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .filter(u -> passwordEncoder.matches(req.getPassword(), u.getPassword()))
                .orElseThrow(() -> new RuntimeException("Błędne dane logowania"));

        return jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name());
    }
}