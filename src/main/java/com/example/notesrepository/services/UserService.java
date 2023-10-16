package com.example.notesrepository.services;

import com.example.notesrepository.entities.User;
import com.example.notesrepository.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}