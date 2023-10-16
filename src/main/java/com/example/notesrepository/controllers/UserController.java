package com.example.notesrepository.controllers;

import com.example.notesrepository.entities.User;
import com.example.notesrepository.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/info/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
