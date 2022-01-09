package com.example.demo;

import com.example.demo.hibernate.UserHelper;
import com.example.demo.hibernate.User;
import java.io.FileNotFoundException;
import java.lang.instrument.Instrumentation;
import java.util.List;


public class Main {
    public static void main(String[] args){
        UserHelper.save(new User("login","kirill","gabov","qwerty"));
        List<User> users = UserHelper.getFullList();
        users.forEach(System.out::println);
    }
}
