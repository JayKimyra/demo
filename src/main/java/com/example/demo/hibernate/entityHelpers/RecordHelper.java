package com.example.demo.hibernate.entityHelpers;

import com.example.demo.hibernate.HibernateUtil;
import com.example.demo.hibernate.entities.Record;
import com.example.demo.hibernate.entities.Street;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    public static Record getById(Long record_id) {
        Record record = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        record = session.get(Record.class, record_id);
        session.close();
        return record;
    }



    public static List<Record> getListByMultipleParameter(Map<Object,Object> map){
        List<Record> resultList = null;


        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Predicate> predicates = new ArrayList<Predicate>();


        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Record> personCriteria = cb.createQuery(Record.class);
        Root<Record> personRoot = personCriteria.from(Record.class);

        if (map.get("userId") != null) {
            predicates.add(
                    cb.equal(personRoot.get("userId"), map.get("userId")));
        }
        if (map.get("streetId") != null) {
            predicates.add(
                    cb.equal(personRoot.get("streetId"), map.get("streetId")));
        }
        if (map.get("dateBegin") != null) {
            predicates.add(
                    cb.greaterThanOrEqualTo(personRoot.get("date"),(Timestamp) map.get("dateBegin")));
        }
        if (map.get("dateEnd") != null) {
            predicates.add(
                    cb.lessThan(personRoot.<Timestamp>get("date"), (Timestamp) map.get("dateBegin")));
        }

        if (predicates.isEmpty()) return getFullList();
        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[]{}));
        personCriteria.select(personRoot).where(finalPredicate);
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            resultList = session.createQuery(personCriteria).getResultList();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return resultList;
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
