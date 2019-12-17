package dao;

import classes.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.*;
import utils.HibernateSessionFactoryUtil;
import org.hibernate.exception.ConstraintViolationException;
import java.util.List;

public class SysbookEntityDaoImp implements SysbookEntityDao {

    public SysbookEntity getHardwareById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        SysbookEntity hardware = session.get(SysbookEntity.class, id);
        session.close();
        return hardware;
    }

    public void updateHardware(int hardware_id, String location) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String query = "update SysbookEntity as sys set sys.location=:location where sys.id_hardware=:hardware_id";
        session.createQuery(query).setParameter("location", location).setParameter("hardware_id", hardware_id).executeUpdate();
        session.flush();
        session.close();
        System.out.println("Оборудование успешно обновлено");
    }

    public List<SysbookEntity> searchByType(String type) {
        TypeTableEntityDao typeDao = new TypeTableEntityDaoImp();
        int typeId = typeDao.findIdByType(type);
        TypeTableEntity typeCurrent = typeDao.findTypeById(typeId);
        String query = "from SysbookEntity as sys where sys.type=:type_id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<SysbookEntity> hardwares = session.createQuery(query).setParameter("type_id", typeCurrent).list();
        List<SysbookEntity> filtredHardwares = null;
        session.close();
        return hardwares;
    }

    public List<SysbookEntity> getHardwaresByLocation(String location) {
        String query;
        List<SysbookEntity> items;
        query = "from SysbookEntity as sys where sys.location like '%" + location + "%'";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        items = (List<SysbookEntity>) session.createQuery(query).list();
        session.close();
        return items;
    }

    public List<SysbookEntity> getAllHardwares() {
        String query;
        List<SysbookEntity> items;
        query = "from SysbookEntity";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        items = (List<SysbookEntity>) session.createQuery(query).list();
        session.close();
        return items;
    }

    public void addHardware(String typeOfHardware, String location, String ipaddress, String macaddress, String services) {
        TypeTableEntityDao typeDao = new TypeTableEntityDaoImp();
        ServicesTableEntityDao serviceDao = new ServicesTableEntityDaoImp();
        SysbookEntityDao hardwareDao = new SysbookEntityDaoImp();
        HardwareServicesTableEntityDao hardwareServiceDao = new HardwareServicesTableEntityDaoImp();
        HardwareIpMacTableEntityDao hardwareIpMacDao = new HardwareIpMacTableEntityDaoImp();
        int typeId = typeDao.findIdByType(typeOfHardware);
        TypeTableEntity type = typeDao.findTypeById(typeId);
        SysbookEntity hardware = new SysbookEntity(type, location);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(hardware);
        tx1.commit();
        session.close();
        List<SysbookEntity> hardwares = hardwareDao.getHardwaresByLocation(location);
        SysbookEntity currentHardware = hardwares.get(hardwares.size() - 1);
        /* Добавление в таблицу hardware_ip_mac_table */
        hardwareIpMacDao.addIpMacToHardware(currentHardware.getId(), ipaddress, macaddress);
        /* Добавление в таблицу hardware_services_table */
        for (String currService : services.split(",")) {
            int idOfservice = serviceDao.findIdByService(currService);
            hardwareServiceDao.addServiceToHardware(idOfservice, currentHardware.getId());
        }
        System.out.println("Успешно добавлено в таблицы sysbook, hardware_ip_mac_table и hardware_services_table");
    }

    public int deleteHardware(String ipaddress, String macaddress) {
        HardwareIpMacTableEntityDao hardwareIpMacDao = new HardwareIpMacTableEntityDaoImp();
        HardwareServicesTableEntityDao hardwareServiceDao = new HardwareServicesTableEntityDaoImp();
        List<HardwareIpMacTableEntity> hardwaresIpMacList = hardwareIpMacDao.getHardwareByIpAndMac(ipaddress, macaddress);
        List<HardwareServicesTableEntity> hardwaresServicesList;
        if (hardwaresIpMacList.size() > 1) {
            System.out.println("В выборке более одного оборудования");
            return -1;
        }
        if (hardwaresIpMacList.size() == 1) {
            try {
                /* Удаление записей в hardware_ip_mac_table */
                Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                HardwareIpMacTableEntity hardwareIpMac = hardwaresIpMacList.get(hardwaresIpMacList.size()-1);
                SysbookEntity currentHardware = hardwareIpMac.getHardware();
                int hardwareID = currentHardware.getId(); // ??????????????????????????????????
                hardwaresIpMacList = hardwareIpMacDao.getHardwaresIpMacByIdHardware(hardwareID);
                session1.close();
                for(int i = 0; i<hardwaresIpMacList.size(); i++) {
                    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    session.delete(hardwaresIpMacList.get(i));
                    session.flush();
                    session.getTransaction().commit();
                    session.close();
                }
                /* Удаление записей в hardware_services_table */
                hardwaresServicesList = hardwareServiceDao.getHardwaresServicesByIdHardware(hardwareID);
                for(int i = 0; i<hardwaresServicesList.size(); i++) {
                    Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    session.delete(hardwaresServicesList.get(i));
                    session.flush();
                    session.getTransaction().commit();
                    session.close();
                }
                /* Удаление записи в sysbook */
                Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.delete(currentHardware);
                session.flush();
                session.getTransaction().commit();
                session.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return 0;
        }
        else {
            System.out.println("Не найдено оборудование, которое необходимо удалить");
            return -1;
        }
    }

    public void deleteHardwareById(int id_hardware)  {
        HardwareIpMacTableEntityDao hardwareIpMacDao = new HardwareIpMacTableEntityDaoImp();
        SysbookEntityDao hardwareDao = new SysbookEntityDaoImp();
        HardwareServicesTableEntityDao hardwareServicesDao = new HardwareServicesTableEntityDaoImp();
        SysbookEntity hardware = hardwareDao.getHardwareById(id_hardware);
        System.out.println(hardware.getId());
        List<HardwareIpMacTableEntity> hardwaresIpMacList = hardwareIpMacDao.getHardwaresIpMacByIdHardware(hardware.getId());
        for(int i = 0; i<hardwaresIpMacList.size(); i++) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(hardwaresIpMacList.get(i));
            session.flush();
            session.getTransaction().commit();
            session.close();
        }
        List<HardwareServicesTableEntity> hardwaresServicesList = hardwareServicesDao.getHardwaresServicesByIdHardware(hardware.getId());
        for(int i = 0; i<hardwaresServicesList.size(); i++) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(hardwaresServicesList.get(i));
            session.flush();
            session.getTransaction().commit();
            session.close();
        }
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(hardware);
        session.flush();
        session.getTransaction().commit();
        session.close();
        System.out.println("Оборудование успешно удалено");
    }
}
