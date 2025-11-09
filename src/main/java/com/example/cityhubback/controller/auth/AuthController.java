package com.example.cityhubback.controller.auth;

import com.example.cityhubback.dto.auth.LoginRequest;
import com.example.cityhubback.dto.auth.RegisterRequest;
import com.example.cityhubback.security.AuthContext;
import com.example.cityhubback.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthContext authContext;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).body("Nieautoryzowany");
        return ResponseEntity.ok(authContext.getCurrentUser());
    }
}