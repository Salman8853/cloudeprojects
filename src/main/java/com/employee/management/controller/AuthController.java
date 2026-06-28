package com.employee.management.controller;

import com.employee.management.dto.LoginRequestDTO;
import com.employee.management.dto.LoginResponseDTO;
import com.employee.management.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Login and token management")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // In-memory user store (for demo purposes - use database in production)
    private static final Map<String, String> USERS = new ConcurrentHashMap<>();

    static {
        USERS.put("admin", "admin123");
        USERS.put("user", "user123");
    }

    public AuthController(JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticate user and get JWT token")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        String username = request.getUsername();
        String password = request.getPassword();

        // Check credentials
        String storedPassword = USERS.get(username);
        if (storedPassword == null || !password.equals(storedPassword)) {
            return ResponseEntity.status(401)
                    .body(new LoginResponseDTO(null, null, "Invalid username or password"));
        }

        // Generate token
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(new LoginResponseDTO(token, username, "Login successful"));
    }

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Register a new user")
    public ResponseEntity<LoginResponseDTO> register(@Valid @RequestBody LoginRequestDTO request) {
        if (USERS.containsKey(request.getUsername())) {
            return ResponseEntity.status(400)
                    .body(new LoginResponseDTO(null, null, "Username already exists"));
        }

        USERS.put(request.getUsername(), request.getPassword());
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new LoginResponseDTO(token, request.getUsername(), "Registration successful"));
    }
}