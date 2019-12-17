package classes;

import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "hardware_ip_mac_table", schema = "public", catalog = "lab2sysadm")
public class HardwareIpMacTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SysbookEntity.class)
    @JoinColumn(name = "id_hardware")
    private SysbookEntity hardware;

    @Column(name = "ipaddress")
    private String ipaddress;

    @Column(name = "macaddress")
    private String macaddress;

    public HardwareIpMacTableEntity() { }

    public HardwareIpMacTableEntity(SysbookEntity hardware, String ipaddress, String macaddress) {
        this.hardware = hardware;
        this.ipaddress = ipaddress;
        this.macaddress = macaddress;
    }

    public SysbookEntity getHardware() {
        return hardware;
    }

    public void setHardware(SysbookEntity hardware) {
        this.hardware = hardware;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HardwareIpMacTableEntity that = (HardwareIpMacTableEntity) o;

        if (id != that.id) return false;
        if (ipaddress != null ? !ipaddress.equals(that.ipaddress) : that.ipaddress != null) return false;
        if (macaddress != null ? !macaddress.equals(that.macaddress) : that.macaddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ipaddress != null ? ipaddress.hashCode() : 0);
        result = 31 * result + (macaddress != null ? macaddress.hashCode() : 0);
        return result;
    }
}
