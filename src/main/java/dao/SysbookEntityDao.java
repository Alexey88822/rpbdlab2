package dao;

import classes.SysbookEntity;

import java.util.List;

public interface SysbookEntityDao {
    public SysbookEntity getHardwareById(int id);
    public List<SysbookEntity> getHardwaresByLocation(String location);
    public void addHardware(String typeOfHardware, String location, String ipaddress, String macaddress, String services);
    public int deleteHardware(String ipaddress, String macaddress);
    public void deleteHardwareById(int id_hardware);
    public List<SysbookEntity> getAllHardwares();
    public List<SysbookEntity> searchByType(String type);
    public void updateHardware(int hardware_id, String location);
}
