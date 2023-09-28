package com.br.martins.learningspringboot.services;

import com.br.martins.learningspringboot.dto.CreateDepositDto;
import com.br.martins.learningspringboot.dto.UserDto;
import com.br.martins.learningspringboot.exceptions.AppException;
import com.br.martins.learningspringboot.models.User;
import com.br.martins.learningspringboot.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void checkCpfAndEmailRepetition (final UserDto userData) {

        if (userRepository.existsUserByCpf(userData.getCpf())) {
            throw new AppException("cpfAlreadyInUse", HttpStatus.CONFLICT);
        }

        if(userRepository.existsUserByEmail(userData.getEmail())) {
            throw new AppException("emailAlreadyInUse", HttpStatus.CONFLICT);
        }
    }

    public User createUser (final UserDto userData) {

        checkCpfAndEmailRepetition(userData);

        final User newUser = new User(userData.getName(), userData.getCpf(), userData.getEmail(), userData.getPassword(), userData.getType());

        return userRepository.save(newUser);
    }

    public List<User> getAllUser () {

        return userRepository.findAll();
    }

    public User retrieveUser (final long id) {

        final User userFound = userRepository.findById(id).orElseThrow(() ->
            new AppException("userNotFound", HttpStatus.NOT_FOUND));

        return userFound;
    }

    public User updateUser (final UserDto newData, final long id) {

        checkCpfAndEmailRepetition(newData);

        final User userFound = userRepository.findById(id).orElseThrow(() ->
            new AppException("userNotFound", HttpStatus.NOT_FOUND));

        userFound.setName(newData.getName());
        userFound.setCpf(newData.getCpf());
        userFound.setEmail(newData.getEmail());
        userFound.setPassword(newData.getPassword());
        userFound.setType(newData.getType());

        return userRepository.save(userFound);
    }

    public void deleteUser (final long id) {

        final User userFound = userRepository.findById(id).orElseThrow(() ->
                new AppException("userNotFound", HttpStatus.NOT_FOUND));

        userRepository.delete(userFound);
    }

    public User createDeposit (final CreateDepositDto depositData, final String userId) {

        final User userFound = userRepository.findById(Long.parseLong(userId)).orElseThrow(() ->
            new AppException("userNotFound", HttpStatus.NOT_FOUND));

        final float currentBalance = userFound.getBalance();

        userFound.setBalance(currentBalance + depositData.getDepositValue());

        return userRepository.save(userFound);
    }
}
