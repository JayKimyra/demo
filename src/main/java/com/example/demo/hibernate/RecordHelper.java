package com.example.demo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RecordHelper {
    public static boolean save(Record record){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(record);
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
    static boolean saveList(List<Record> records){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for(Record record : records){
                session.save(record);
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
    static List<Record> getListByParameter(String field, String criteria){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Record> personCriteria = cb.createQuery(Record.class);
        Root<Record> personRoot = personCriteria.from(Record.class);
        personCriteria.select(personRoot);
        personCriteria.where(cb.equal(personRoot.get(field),criteria));


        return session.createQuery(personCriteria)
                .getResultList();
    }

    public static List<Record> getFullList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Record> personCriteria = cb.createQuery(Record.class);
        Root<Record> personRoot = personCriteria.from(Record.class);
        personCriteria.select(personRoot);
        List<Record> result = session.createQuery(personCriteria).getResultList();
        session.close();
        return result;
    }
}
