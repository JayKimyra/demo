package com.example.demo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("C:\\Users\\Admin\\Desktop\\data.txt"));
        new User();
        while (scanner.hasNext()){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            try{
                session.save(new City(scanner.nextLine()));
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
                //todo: Отправить ошибку куда-либо!
            }
            finally {
                session.close();
            }
        }


    }
}

