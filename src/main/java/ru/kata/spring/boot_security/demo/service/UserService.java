package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);
}
