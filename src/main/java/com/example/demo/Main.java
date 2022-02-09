package com.example.demo;

import com.example.demo.hibernate.entities.Record;
import com.example.demo.hibernate.entities.Role;
import com.example.demo.hibernate.entities.Street;
import com.example.demo.hibernate.entities.User;
import com.example.demo.hibernate.entityHelpers.RecordHelper;
import com.example.demo.hibernate.entityHelpers.StreetHelper;
import com.example.demo.hibernate.entityHelpers.UserHelper;

import javax.mail.AuthenticationFailedException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        /*Scanner scanner = new Scanner(new File("C:\\Users\\Admin\\Desktop\\Vse_ulitsy_Petrozavodska.txt"));
        List<Street> list = new ArrayList<>();
        while (scanner.hasNext()){
            String data = scanner.nextLine();
            list.add(new Street(data));
            //break;
        }
        StreetHelper.saveList(list);*/
        /*User user = new User("123","kirill","gabov", "111", Role.ADMIN);
        UserHelper.save(user);
        user = new User("qwe","sanya","egorov", "333",Role.WORKER);
        UserHelper.save(user);
        RecordHelper.save(new Record(new Timestamp(System.currentTimeMillis()),UserHelper.getById(1L),StreetHelper.getById(1L),"1","1","1","1"));
        Record record = RecordHelper.getById(Long.valueOf("1"));
        System.out.println(record);*/
        Timestamp.valueOf("");
    }


}
