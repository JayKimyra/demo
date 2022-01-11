package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    static BCryptPasswordEncoder instance;
    static {
        instance = new BCryptPasswordEncoder();
        System.out.println(instance.encode("random"));
    }
}
