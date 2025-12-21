package com.tlu.unigrade.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/me")
    public ResponseEntity<Void> me() {
        return ResponseEntity.ok().build();
    }
    
}
