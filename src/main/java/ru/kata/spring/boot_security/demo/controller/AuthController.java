package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AdminService;


@Controller
public class AuthController {

    private final AdminService adminService;

    @Autowired
    public AuthController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") User user) {
        return "pages/registration";
    }

    @PostMapping("/registration")
    public String perfomRegistration(@ModelAttribute("person") User user) {
        adminService.save(user);
        return "redirect:/auth/login";
    }
}
