package services;

import dao.*;
import classes.HardwareServicesTableEntity;
import classes.SysbookEntity;
import classes.TypeTableEntity;
import classes.ServicesTableEntity;
import classes.HardwareIpMacTableEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SystemAdministratorService {
    private HardwareServicesTableEntityDao hardwareserviceDao = new HardwareServicesTableEntityDaoImp();
    private SysbookEntityDao hardwareDao = new SysbookEntityDaoImp();
    private TypeTableEntityDao typeDao = new TypeTableEntityDaoImp();
    private ServicesTableEntityDao serviceDao = new ServicesTableEntityDaoImp();
    private HardwareIpMacTableEntityDao hardwareipmacDao = new HardwareIpMacTableEntityDaoImp();

    public SystemAdministratorService(){}

    public void showMenu(){
        System.out.println("Записная книжка системного администратора: ");
        System.out.println("0. Выход");
        System.out.println("1. Добавление оборудования");
        System.out.println("2. Удаление оборудования по IPADDRESS и MACADDRESS");
        System.out.println("3. Удаление оборудования по ID");
        System.out.println("4. Просмотр всего оборудования(Таблица sysbook)");
        System.out.println("5. Просмотр IP и MAC(Таблица hardware_ip_mac_table)");
        System.out.println("6. Просмотр SERVICES(Таблица hardware_services_table");
        System.out.println("7. Поиск оборудования по IP");
        System.out.println("8. Поиск оборудования по MAC");
        System.out.println("9. Поиск оборудования по типу");
        System.out.println("10. Изменение расположения оборудования");
        System.out.println("11. Добавление IP и MAC оборудованию");
    }

    //  1. Добавление нового оборудования
    public void addHardware() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите тип оборудования: ");
        String type = reader.readLine();
        System.out.print("Введите расположение: ");
        String location = reader.readLine();
        System.out.print("Введите IP адрес: ");
        String ipaddress = reader.readLine();
        System.out.print("Введите MAC адрес: ");
        String macaddress = reader.readLine();
        System.out.print("Введите поддерживыемые сервисы через запятую (Пример: snmp,ssh,telnet): ");
        String services = reader.readLine();
        hardwareDao.addHardware(type, location, ipaddress, macaddress, services);
    }

    // 2. Удаление оборудования
    public void deleteHardware() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите IP адрес: ");
        String ipaddress = reader.readLine();
        System.out.print("Введите MAC адрес: ");
        String macaddress = reader.readLine();
        hardwareDao.deleteHardware(ipaddress, macaddress);
    }

    // 3. Удаление оборудования по id
    public void deleteHardwareById() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите ID оборудования: ");
        int hardwareid = Integer.parseInt(reader.readLine());
        hardwareDao.deleteHardwareById(hardwareid);
    }

    // 4. Просмотр оборудования
    public void showHardwares() throws IOException {
        List<SysbookEntity> hardwares = hardwareDao.getAllHardwares();
        for(SysbookEntity item : hardwares){
            System.out.println("________________________________________________________________________________");
            System.out.println(item + " ID: " + item.getId() + " TYPE: " + item.getType().getType() + " LOCATION: " + item.getLocation());
        }
    }
    // 5. Просмотр IP и MAC оборудования
    public void showHardwareIpMac() throws IOException {
        List<HardwareIpMacTableEntity> hardwareIpMac = hardwareipmacDao.getAllHardwareIpMac();
        for(HardwareIpMacTableEntity item : hardwareIpMac){
            System.out.println("_________________________________________________________________________________");
            System.out.println(item + " ID: " + item.getId() + " ID_HARDWARE: " + item.getHardware()+ " IPADDRESS: " + item.getIpaddress() + " MACADDRESS " + item.getMacaddress());
        }
    }
    // 6. Просмотр SERVICES
    public void  showHardwareServices() throws IOException {
        List<HardwareServicesTableEntity> hardwareServices = hardwareserviceDao.getAllHardwareServices();
        for(HardwareServicesTableEntity item : hardwareServices){
            System.out.println("_________________________________________________________________________________");
            System.out.println(item + " ID: " + item.getId() + " ID_HARDWARE: " + item.getHardware()+ " SERVICE: " + item.getService());
        }
    }

    // 7. Поиск оборудования по IP
    public void searchByIp() throws  IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите IP адрес: ");
        String ipaddress = reader.readLine();
        List<HardwareIpMacTableEntity> hardwareIpMac = hardwareipmacDao.getHardwaresByIp(ipaddress);
        if (hardwareIpMac.size() == 0) {
            System.out.println("Оборудование не найдено");
        }
        for(HardwareIpMacTableEntity item : hardwareIpMac){
            System.out.println("_________________________________________________________________________________");
            System.out.println(item + " ID: " + item.getId()+ " ID_HARDWARE: " + item.getHardware()+ " IPADDRESS: " + item.getIpaddress() + " MACADDRESS: " + item.getMacaddress());
        }
    }

    // 8. Поиск оборудования по MAC
    public void searchByMac() throws  IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите MAC адрес: ");
        String macaddress = reader.readLine();
        List<HardwareIpMacTableEntity> hardwareIpMac = hardwareipmacDao.getHardwaresByMac(macaddress);
        if (hardwareIpMac.size() == 0) {
            System.out.println("Оборудование не найдено");
        }
        for(HardwareIpMacTableEntity item : hardwareIpMac){
            System.out.println("_________________________________________________________________________________");
            System.out.println(item + " ID: " + item.getId()+ " ID_HARDWARE: " + item.getHardware()+ " IPADDRESS: " + item.getIpaddress() + " MACADDRESS: " + item.getMacaddress());
        }
    }

    // 9. Поиск оборудования по типу
    public void searchByType() throws  IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите тип оборудования: ");
        String type = reader.readLine();
        List<SysbookEntity> hardwares = hardwareDao.searchByType(type);
        if (hardwares.size() == 0) {
            System.out.println("Оборудование не найдено");
        }
        for(SysbookEntity item : hardwares){
            System.out.println("_________________________________________________________________________________");
            System.out.println(item + " ID_HARDWARE: " + item.getId()+ " TYPE_ID: " + item.getType() + " LOCATION: " + item.getLocation());
        }
    }

    // 10. Изменение расположения оборудования
    public void updateHardware() throws  IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите ID оборудования: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.print("Введите расположение оборудования: ");
        String location = reader.readLine();
        hardwareDao.updateHardware(id, location);
    }

    // 11. Добавление IP и MAC оборудованию
    public void addIpMacToHardware() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите ID оборудования: ");
        int id = Integer.parseInt(reader.readLine());
        System.out.print("Введите IP ADDRESS: ");
        String ipaddress = reader.readLine();
        System.out.print("Введите MAC: ");
        String macaddress = reader.readLine();
        hardwareipmacDao.addIpMacToHardwareByIdHardware(id, ipaddress, macaddress);
    }
}
