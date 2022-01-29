package com.example.demo.hibernate.entityHelpers;

import com.example.demo.hibernate.HibernateUtil;
import com.example.demo.hibernate.entities.Street;
import com.example.demo.hibernate.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserHelper {
    static public boolean save(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(user);
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
    static public boolean saveList(List<User> users){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for(User user : users){
                session.save(user);
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
    static public List<User> getListByParameter(String field, String criteria){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> personCriteria = cb.createQuery(User.class);
        Root<User> personRoot = personCriteria.from(User.class);
        personCriteria.select(personRoot);
        personCriteria.where(cb.equal(personRoot.get(field),criteria));


        return session.createQuery(personCriteria)
                .getResultList();
    }
    static public List<User> getFullList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> personCriteria = cb.createQuery(User.class);
        Root<User> personRoot = personCriteria.from(User.class);
        personCriteria.select(personRoot);
        List<User> result = session.createQuery(personCriteria).getResultList();
        session.close();
        return result;
    }

    public static User getById(Long user_id) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            user =  (User) session.get(User.class, user_id);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            //todo: Отправить ошибку куда-либо!
        }
        finally {
            session.close();
        }
        return user;
    }
}
