package com.example.demo;

import java.util.Date;
import java.util.stream.Stream;

public class User {

    private String name;
    private int id;
    public User(String name,int id){
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
