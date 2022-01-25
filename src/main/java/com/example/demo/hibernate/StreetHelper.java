package com.example.demo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StreetHelper {
    static boolean save(Street street){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(street);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            //todo: Отправить ошибку куда-либо!
            return false;
        }
        finally {
            session.close();
        }
    }
    static boolean saveList(List<Street> streets){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for(Street street : streets){
                session.save(street);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            //todo: Отправить ошибку куда-либо!
            return false;
        }
        finally {
            session.close();
        }
    }


    //смотрим на колонку field и выбираем те поля для которых критерий равен criteria
    static List<Street> getListByParameter(String field, String criteria){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Street> personCriteria = cb.createQuery(Street.class);
        Root<Street> personRoot = personCriteria.from(Street.class);
        personCriteria.select(personRoot);
        personCriteria.where(cb.equal(personRoot.get(field),criteria));


        return session.createQuery(personCriteria)
                .getResultList();
    }

    public static Street getFirstByParameter(String field, String criteria){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Street> personCriteria = cb.createQuery(Street.class);
        Root<Street> personRoot = personCriteria.from(Street.class);
        personCriteria.select(personRoot);
        personCriteria.where(cb.equal(personRoot.get(field),criteria));


        return session.createQuery(personCriteria)
                .getResultList().get(0);
    }



    static List<Street> getFullList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Street> personCriteria = cb.createQuery(Street.class);
        Root<Street> personRoot = personCriteria.from(Street.class);
        personCriteria.select(personRoot);
        return session.createQuery(personCriteria)
                .getResultList();
    }
}
