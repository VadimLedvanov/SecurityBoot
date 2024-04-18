package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


@Service
public class PersonDetailsService implements UserDetailsService {

    private final AdminService adminService;

    @Autowired
    public PersonDetailsService(AdminService adminService) {
        this.adminService = adminService;
    }


    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User person =  adminService.findByUsername(username);

        if (person == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        for (Role role : person.getRoles()) {
            System.out.println(role.getName());
        }

        return person;
    }
}
