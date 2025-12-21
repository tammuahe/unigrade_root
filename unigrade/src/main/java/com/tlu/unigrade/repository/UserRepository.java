package com.tlu.unigrade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlu.unigrade.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);

}
