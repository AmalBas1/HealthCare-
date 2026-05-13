package com.healthcare.medical_system.controller;


import com.healthcare.medical_system.dto.AuthResponse;
import com.healthcare.medical_system.dto.LoginRequest;
import com.healthcare.medical_system.dto.RegisterRequest;
import com.healthcare.medical_system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        AuthResponse response = authService.register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid @RequestBody LoginRequest loginRequest){
        String response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
