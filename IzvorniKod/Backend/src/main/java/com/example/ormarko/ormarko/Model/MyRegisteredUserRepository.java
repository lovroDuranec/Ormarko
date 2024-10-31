package com.example.ormarko.ormarko.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyRegisteredUserRepository extends JpaRepository<MyRegisteredUser, String> {

    Optional<MyRegisteredUser> findByUsername(String username);
}