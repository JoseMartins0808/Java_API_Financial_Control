package com.br.martins.learningspringboot.controllers;

import com.br.martins.learningspringboot.models.User;
import com.br.martins.learningspringboot.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody final User userData) {

        final User newUser = userService.createUser(userData);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {

        final List<User> allUsers = userService.getAllUser();

        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }
}
