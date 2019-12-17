import services.SystemAdministratorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.err.close();
        SystemAdministratorService service = new SystemAdministratorService();
        boolean exit = false;
        int r;
        while (!exit) {
            service.showMenu();
            r = Integer.parseInt(reader.readLine());
            switch (r) {
                case 0: {
                    exit = true;
                    break;
                }
                case 1: {
                    service.addHardware();
                    break;
                }
                case 2: {
                    service.deleteHardware();
                    break;
                }
                case 3: {
                    service.deleteHardwareById();
                    break;
                }
                case 4: {
                    service.showHardwares();
                    break;
                }
                case 5: {
                    service.showHardwareIpMac();
                    break;
                }
                case 6: {
                    service.showHardwareServices();
                    break;
                }
                case 7: {
                    service.searchByIp();
                    break;
                }
                case 8: {
                    service.searchByMac();
                    break;
                }
                case 9: {
                    service.searchByType();
                    break;
                }
                case 10: {
                    service.updateHardware();
                    break;
                }
                case 11: {
                    service.addIpMacToHardware();
                    break;
                }
            }
        }
    }
}