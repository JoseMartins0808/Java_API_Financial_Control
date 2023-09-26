package com.br.martins.learningspringboot.controllers;

import com.br.martins.learningspringboot.dto.CreateDepositDto;
import com.br.martins.learningspringboot.dto.UserDto;
import com.br.martins.learningspringboot.models.User;
import com.br.martins.learningspringboot.services.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<User> createUser (@Valid @RequestBody final UserDto userData) {

        final User newUser = userService.createUser(userData);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {

        final List<User> allUsers = userService.getAllUser();

        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> retrieveUser (@PathVariable final String id) {

        final User userFound = userService.retrieveUser(Long.parseLong(id));

        return new ResponseEntity<User>(userFound, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser (@PathVariable final String id,
        @Valid @RequestBody final UserDto updateData) {

            final User userUpdated = userService.updateUser(updateData, Long.parseLong(id));

            return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable final String id) {

        userService.deleteUser(Long.parseLong(id));

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userId}/deposit")
    public ResponseEntity<User> createDeposit (@Valid @RequestBody final CreateDepositDto depositData,
        @PathVariable final String userId) {

        final User newDeposit = userService.createDeposit(depositData, userId);

        return new ResponseEntity<User>(newDeposit, HttpStatus.CREATED);
    }
}
