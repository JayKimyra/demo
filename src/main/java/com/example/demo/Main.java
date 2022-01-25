package com.example.demo;

import com.example.demo.hibernate.UserHelper;
import com.example.demo.hibernate.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileNotFoundException;
import java.lang.instrument.Instrumentation;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception {
        User user = new User("123","kirill","gabov","111","admin");
        User user2 = new User("321","kirill","gabov","1111","admin");
        UserHelper.save(user);
        UserHelper.save(user2);
    }
}
