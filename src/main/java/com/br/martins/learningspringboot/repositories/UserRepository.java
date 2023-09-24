package com.br.martins.learningspringboot.repositories;

import com.br.martins.learningspringboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
