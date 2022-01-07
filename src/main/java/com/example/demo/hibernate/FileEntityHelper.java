package com.example.demo.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class FileEntityHelper {
    static boolean save(FileEntity fileEntity){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(fileEntity);
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
    static boolean saveList(List<FileEntity> fileEntitys){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for(FileEntity fileEntity : fileEntitys){
                session.save(fileEntity);
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
    static List<FileEntity> getListByParameter(String field, String criteria){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<FileEntity> personCriteria = cb.createQuery(FileEntity.class);
        Root<FileEntity> personRoot = personCriteria.from(FileEntity.class);
        personCriteria.select(personRoot);
        personCriteria.where(cb.equal(personRoot.get(field),criteria));


        return session.createQuery(personCriteria)
                .getResultList();
    }

    static List<FileEntity> getFullList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<FileEntity> personCriteria = cb.createQuery(FileEntity.class);
        Root<FileEntity> personRoot = personCriteria.from(FileEntity.class);
        personCriteria.select(personRoot);
        return session.createQuery(personCriteria)
                .getResultList();
    }
}
