package com.br.martins.learningspringboot.services;

import com.br.martins.learningspringboot.dto.CreateDepositDto;
import com.br.martins.learningspringboot.dto.UserDto;
import com.br.martins.learningspringboot.exceptions.AppException;
import com.br.martins.learningspringboot.models.User;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserService {

    User createUser (final UserDto userData);

    List<User> getAllUser ();

    User retrieveUser (final long id);

    User updateUser (final UserDto newData, final long id);

    void deleteUser (final long id);

    User createDeposit (final CreateDepositDto depositData, final String userId);
}
