package dao;

import classes.HardwareServicesTableEntity;

import java.util.List;

public interface HardwareServicesTableEntityDao {
    public int addServiceToHardware(int id_service, int id_hardware);
    public List<HardwareServicesTableEntity> getHardwaresServicesByIdHardware(int id_hardware);
    public List<HardwareServicesTableEntity> getAllHardwareServices();
}
