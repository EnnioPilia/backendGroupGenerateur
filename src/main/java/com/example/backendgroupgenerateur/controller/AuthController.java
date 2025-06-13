package com.example.backendgroupgenerateur.controller;

import com.example.backendgroupgenerateur.dto.AuthResponse;
import com.example.backendgroupgenerateur.dto.LoginRequest;
import com.example.backendgroupgenerateur.dto.RegisterRequest;
import com.example.backendgroupgenerateur.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
