package com.example.notesrepository.repositories;

import com.example.notesrepository.dtos.AuthResponse;
import com.example.notesrepository.dtos.AuthenticationRequest;
import com.example.notesrepository.dtos.RegisterRequest;

public interface AuthRepository {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthenticationRequest authenticationRequest);
}
