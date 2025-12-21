package com.tlu.unigrade.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tlu.unigrade.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private final UserRepository userRepository;

    public Long getStudentId(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(username).orElseThrow().getStudentId();
    }

}
