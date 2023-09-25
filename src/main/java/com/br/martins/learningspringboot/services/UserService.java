package com.br.martins.learningspringboot.services;

import com.br.martins.learningspringboot.models.User;
import com.br.martins.learningspringboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User retrieveUser (final long id) throws Exception {

        final User userFound = userRepository.findById(id).orElseThrow(() ->
            new Exception("User not found."));

        return userFound;
    }

    public User updateUser (final User newData, final long id) throws Exception {

        final User userFound = userRepository.findById(id).orElseThrow(() ->
            new Exception("User not found."));

        userFound.setName(newData.getName());
        userFound.setCpf(newData.getCpf());
        userFound.setEmail(newData.getEmail());
        userFound.setPassword(newData.getPassword());
        userFound.setType(newData.getType());

        return userRepository.save(userFound);
    }

    public void deleteUser (final long id) throws Exception {

        final User userFound = userRepository.findById(id).orElseThrow(() ->
                new Exception("User not found."));

        userRepository.delete(userFound);
    }
}
