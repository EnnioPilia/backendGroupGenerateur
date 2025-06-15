package com.example.backendgroupgenerateur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backendgroupgenerateur.config.JWTUtils;
import com.example.backendgroupgenerateur.dto.AuthResponse;
import com.example.backendgroupgenerateur.dto.LoginRequest;
import com.example.backendgroupgenerateur.dto.RegisterRequest;
import com.example.backendgroupgenerateur.model.User;
import com.example.backendgroupgenerateur.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserService userService;

@PostMapping("/register")
public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
    try {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());  // le service UserService encode le mot de passe

        String role = request.getRole();
        if (role == null || (!role.equalsIgnoreCase("admin") && !role.equalsIgnoreCase("user"))) {
            role = "USER";  // Par défaut USER si rôle absent ou incorrect
        }
        user.setRole(role.toUpperCase());

        userService.register(user);
        return ResponseEntity.ok("Utilisateur enregistré");
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            // Récupérer le rôle depuis l'authentication (ex: "ROLE_ADMIN" ou "ROLE_USER")
            String role = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .orElse("ROLE_USER");

            // Retirer le préfixe "ROLE_" et mettre en minuscules pour le token
            String cleanRole = role.startsWith("ROLE_") ? role.substring(5).toLowerCase() : role.toLowerCase();

            String token = jwtUtils.generateToken(request.getEmail(), cleanRole);

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
        }
    }
}
