package com.br.martins.learningspringboot.repositories;

import com.br.martins.learningspringboot.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByCpf (final String cpf);

    boolean existsUserByEmail (final String email);

}
