package com.example.demo;

import com.example.demo.hibernate.entities.Record;
import com.example.demo.hibernate.entities.Role;
import com.example.demo.hibernate.entities.Street;
import com.example.demo.hibernate.entities.User;
import com.example.demo.hibernate.entityHelpers.RecordHelper;
import com.example.demo.hibernate.entityHelpers.StreetHelper;
import com.example.demo.hibernate.entityHelpers.UserHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("C:\\Users\\Admin\\Desktop\\Vse_ulitsy_Petrozavodska.txt"));
        List<Street> list = new ArrayList<>();
        while (scanner.hasNext()){
            String data = scanner.nextLine();
            list.add(new Street(data));
            //break;
        }
        StreetHelper.saveList(list);
        User user = new User("123","kirill","gabov", "111", Role.ADMIN);
        UserHelper.save(user);
        user = new User("qwe","sanya","egorov", "333",Role.WORKER);
        UserHelper.save(user);
        RecordHelper.save(new Record(new Timestamp(System.currentTimeMillis()),UserHelper.getById(1L),StreetHelper.getById(1L),"1","1","1","1"));
        Record record = RecordHelper.getById(Long.valueOf("1"));
        System.out.println(record);

    }


}
