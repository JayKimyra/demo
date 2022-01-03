package com.example.demo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            }catch (Exception e){
                registry.close();
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
