package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    @Transactional(readOnly = true)
    List<User> findAll();

    @Transactional(readOnly = true)
    Optional<User> findById(Long id);

    @Transactional
    void deleteById(Long id);

    @Transactional
    void save(User user, List<Long> selectedRoleId);

    @Transactional
    void update(User user, List<Long> selectedRoleId);

    @Transactional(readOnly = true)
    User findByUsername(String username);
}
