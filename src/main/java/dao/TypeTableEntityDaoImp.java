package dao;

import classes.TypeTableEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class TypeTableEntityDaoImp implements TypeTableEntityDao {

    public void addType(TypeTableEntity type) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(type);
        tx1.commit();
        session.close();
    }


    public TypeTableEntity findTypeById(int id) {
        Session sesion = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        TypeTableEntity type = sesion.get(TypeTableEntity.class, id);
        return type;
    }

    public int findIdByType(String type) {
        TypeTableEntity typeEntity;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Criteria typeCriteria = session.createCriteria(TypeTableEntity.class);
        typeCriteria.add(Restrictions.eq("type", type));
        typeEntity = (TypeTableEntity) typeCriteria.uniqueResult();
        session.close();
        return typeEntity.getId();
    };


    public List<TypeTableEntity> showAllTypes() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<TypeTableEntity> types = (List<TypeTableEntity>) session.createQuery("From TypeTableEntity ").list();
        session.close();
        return types;
    }
}
