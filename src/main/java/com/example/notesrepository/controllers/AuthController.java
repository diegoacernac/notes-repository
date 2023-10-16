package com.example.notesrepository.controllers;

import com.example.notesrepository.dtos.AuthResponse;
import com.example.notesrepository.dtos.AuthenticationRequest;
import com.example.notesrepository.dtos.RegisterRequest;
import com.example.notesrepository.exceptions.EmailAlreadyTakenException;
import com.example.notesrepository.exceptions.ErrorResponse;
import com.example.notesrepository.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            AuthResponse authResponse = authService.register(request);
            return ResponseEntity.ok(authResponse);
        } catch (EmailAlreadyTakenException exception) {
            ErrorResponse errorResponse = new ErrorResponse("EmailAlreadyTaken", exception.getMessage());
            return new ResponseEntity<AuthResponse>((MultiValueMap<String, String>) errorResponse, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
