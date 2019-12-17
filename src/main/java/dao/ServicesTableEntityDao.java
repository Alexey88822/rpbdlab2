package dao;

import classes.ServicesTableEntity;

import java.util.List;

public interface ServicesTableEntityDao {
    public void addService(ServicesTableEntity service);
    public ServicesTableEntity findServiceById(int id);
    public int findIdByService(String service);
    public List<ServicesTableEntity> showAllServices();
}
