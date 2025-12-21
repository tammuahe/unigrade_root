package com.tlu.unigrade.init;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tlu.unigrade.entity.User;
import com.tlu.unigrade.enums.Role;
import com.tlu.unigrade.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    @PostConstruct
    void init() {
        if (userRepo.findByUsername("student1").isEmpty()) {
            User u = new User();
            u.setUsername("student1");
            u.setPassword(encoder.encode("123456"));
            u.setRole(Role.STUDENT);
            u.setStudentId(1L);

            userRepo.save(u);
        }
    }
}

