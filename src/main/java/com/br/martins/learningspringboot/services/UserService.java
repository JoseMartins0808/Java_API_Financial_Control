package com.br.martins.learningspringboot.services;

import com.br.martins.learningspringboot.models.User;
import com.br.martins.learningspringboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser (final User userData) {

        final User newUser = new User(userData.getName(), userData.getCpf(), userData.getEmail(), userData.getPassword(), userData.getType());

        return userRepository.save(newUser);
    }

    public List<User> getAllUser () {

        return userRepository.findAll();
    }
}
