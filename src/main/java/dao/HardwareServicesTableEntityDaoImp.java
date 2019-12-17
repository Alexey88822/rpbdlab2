package dao;

import classes.HardwareIpMacTableEntity;
import classes.HardwareServicesTableEntity;
import classes.ServicesTableEntity;
import classes.SysbookEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class HardwareServicesTableEntityDaoImp implements HardwareServicesTableEntityDao {

    public int addServiceToHardware(int id_service, int id_hardware) {
        SysbookEntityDao hardwareDao = new SysbookEntityDaoImp();
        SysbookEntity hardware = hardwareDao.getHardwareById(id_hardware);
        ServicesTableEntityDao serviceDao = new ServicesTableEntityDaoImp();
        ServicesTableEntity service = serviceDao.findServiceById(id_service);
        if (service==null) {
            System.out.println("Такого сервиса не существует.");
            return -1;
        }
        HardwareServicesTableEntity hardwareservice = new HardwareServicesTableEntity(hardware, service);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(hardwareservice);
        tx1.commit();
        session.close();
        return 0;
    }

    public List<HardwareServicesTableEntity> getHardwaresServicesByIdHardware(int id_hardware) {
        String query;
        query = "from HardwareServicesTableEntity as hst where hst.hardware="+ id_hardware;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<HardwareServicesTableEntity> items = (List<HardwareServicesTableEntity>) session.createQuery(query).list();
        session.close();
        return items;
    }

    public List<HardwareServicesTableEntity> getAllHardwareServices() {
        String query;
        List<HardwareServicesTableEntity> items;
        query = "from HardwareServicesTableEntity ";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        items = (List<HardwareServicesTableEntity>) session.createQuery(query).list();
        session.close();
        return items;
    }
}
