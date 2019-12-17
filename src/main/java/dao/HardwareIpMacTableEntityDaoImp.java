package dao;
import classes.SysbookEntity;
import classes.HardwareIpMacTableEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class HardwareIpMacTableEntityDaoImp implements HardwareIpMacTableEntityDao {

    public void addIpMacToHardware(int id_hardware, String ipaddress, String macaddress) {
        SysbookEntityDao hardwareDao = new SysbookEntityDaoImp();
        System.out.println(1);
        SysbookEntity hardware = hardwareDao.getHardwareById(id_hardware);
        System.out.println(hardware.getId());
        HardwareIpMacTableEntity hardwareipmac = new HardwareIpMacTableEntity(hardware, ipaddress, macaddress);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(hardwareipmac);
        tx1.commit();
        session.close();
    }

    public List<HardwareIpMacTableEntity> getHardwaresIpMacByIdHardware(int id_hardware) {
        String query;
        query = "from HardwareIpMacTableEntity as him where him.hardware="+ id_hardware;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<HardwareIpMacTableEntity> items = (List<HardwareIpMacTableEntity>) session.createQuery(query).list();
        session.close();
        return items;
    }

    public List<HardwareIpMacTableEntity> getHardwareByIpAndMac(String ipaddress, String macaddress) {
        String query;
        List<HardwareIpMacTableEntity> items;
        query = "from HardwareIpMacTableEntity as him where him.ipaddress like '%" + ipaddress + "%' and him.macaddress like '%" + macaddress + "%'";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        items = (List<HardwareIpMacTableEntity>) session.createQuery(query).list();
        session.close();
        return items;
    }

    public List<HardwareIpMacTableEntity> getHardwaresByIp(String ipaddress) {
        String query;
        List<HardwareIpMacTableEntity> items;
        query = "from HardwareIpMacTableEntity as him where him.ipaddress like '%" + ipaddress + "%'";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        items = (List<HardwareIpMacTableEntity>) session.createQuery(query).list();
        session.close();
        return items;
    }

    public List<HardwareIpMacTableEntity> getHardwaresByMac(String macaddress) {
        String query;
        List<HardwareIpMacTableEntity> items;
        query = "from HardwareIpMacTableEntity as him where him.macaddress like '%" + macaddress + "%'";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        items = (List<HardwareIpMacTableEntity>) session.createQuery(query).list();
        session.close();
        return items;
    }

    public void addIpMacToHardwareByIdHardware(int hardware_id, String ipaddress, String macaddress) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        SysbookEntity hardware = session.get(SysbookEntity.class, hardware_id);
        session.close();
        HardwareIpMacTableEntity newIpMac = new HardwareIpMacTableEntity(hardware, ipaddress, macaddress);
        session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(newIpMac);
        session.flush();
        session.close();
    }

    public List<HardwareIpMacTableEntity> getAllHardwareIpMac() {
        String query;
        List<HardwareIpMacTableEntity> items;
        query = "from HardwareIpMacTableEntity ";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        items = (List<HardwareIpMacTableEntity>) session.createQuery(query).list();
        session.close();
        return items;
    }
}
