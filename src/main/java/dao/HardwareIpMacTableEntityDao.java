package dao;

import classes.HardwareIpMacTableEntity;

import java.util.List;

public interface HardwareIpMacTableEntityDao {
    public void addIpMacToHardware(int id_hardware, String ipaddress, String macaddress);
    public List<HardwareIpMacTableEntity> getHardwareByIpAndMac(String ipaddress, String macaddress);
    public List<HardwareIpMacTableEntity> getHardwaresIpMacByIdHardware(int id_hardware);
    public List<HardwareIpMacTableEntity> getAllHardwareIpMac();
    public List<HardwareIpMacTableEntity> getHardwaresByMac(String macaddress);
    public List<HardwareIpMacTableEntity> getHardwaresByIp(String ipaddress);
    public void addIpMacToHardwareByIdHardware(int hardware_id, String ipaddress, String macaddress);
}
