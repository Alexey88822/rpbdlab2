package dao;

import classes.ServicesTableEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class ServicesTableEntityDaoImp implements ServicesTableEntityDao {

    public void addService(ServicesTableEntity service) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(service);
        tx1.commit();
        session.close();
    }


    public ServicesTableEntity findServiceById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ServicesTableEntity.class, id);
    }

    public int findIdByService(String service) {
        ServicesTableEntity serviceEntity;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Criteria typeCriteria = session.createCriteria(ServicesTableEntity.class);
        typeCriteria.add(Restrictions.eq("service", service));
        serviceEntity = (ServicesTableEntity) typeCriteria.uniqueResult();
        session.close();
        return serviceEntity.getId();
    }

    public List<ServicesTableEntity> showAllServices() {
        List<ServicesTableEntity> types = (List<ServicesTableEntity>)HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From ServicesTableEntity ").list();
        return types;
    }
}
