package com.example.demo;


import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Formatter {
    public static String getFileName(String location) {
        return location;
    }

    public static String getDirectoryName(Object... path){
        StringBuilder sb = new StringBuilder();
        Arrays.stream(path).forEach(x -> sb.append(x.toString()+"\\"));
        return sb.toString();
    }

    public static String getFullLocation(String street,String home, String flat) {
        return street + "-номер улицы " + home + "-дом " + flat + "-квартира";
    }
}