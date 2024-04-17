package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.PeopleRepository;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    private final UserService userService;
    private final PeopleRepository peopleRepository;

    @Autowired
    public SuccessUserHandler(UserService userService, PeopleRepository peopleRepository) {
        this.userService = userService;
        this.peopleRepository = peopleRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_USER") && !roles.contains("ROLE_ADMIN")) {
            User user = peopleRepository.findByUsername(authentication.getName()).get();
            Long id = user.getId();
            httpServletResponse.sendRedirect(String.format("/userPage?id=%s", id));
        } else {
            httpServletResponse.sendRedirect("/admin");
        }
    }
}