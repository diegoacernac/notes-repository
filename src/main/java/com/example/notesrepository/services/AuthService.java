package com.example.notesrepository.services;

import com.example.notesrepository.config.JwtService;
import com.example.notesrepository.dtos.AuthResponse;
import com.example.notesrepository.dtos.AuthenticationRequest;
import com.example.notesrepository.dtos.RegisterRequest;
import com.example.notesrepository.entities.User;
import com.example.notesrepository.enums.Role;
import com.example.notesrepository.exceptions.EmailAlreadyTakenException;
import com.example.notesrepository.repositories.AuthRepository;
import com.example.notesrepository.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthRepository {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyTakenException("El correo electrónico ya está registrado.");
        }
        var user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        var user = userRepository.findUserByEmail(authenticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken, user);
    }
}
