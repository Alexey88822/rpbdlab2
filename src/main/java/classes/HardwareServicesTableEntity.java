package classes;

import javax.persistence.*;

@Entity
@Table(name = "hardware_services_table", schema = "public", catalog = "lab2sysadm")
public class HardwareServicesTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ServicesTableEntity.class)
    @JoinColumn(name = "id_service")
    private ServicesTableEntity service;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = SysbookEntity.class)
    @JoinColumn(name = "id_hardware")
    private SysbookEntity hardware;

    public HardwareServicesTableEntity() { }

    public HardwareServicesTableEntity(SysbookEntity hardware, ServicesTableEntity service) {
        this.service = service;
        this.hardware = hardware;
    }

    public SysbookEntity getHardware() {
        return hardware;
    }

    public void setHardware(SysbookEntity hardware) {
        this.hardware = hardware;
    }

    public ServicesTableEntity getService() {
        return service;
    }

    public void setService(ServicesTableEntity service) {
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HardwareServicesTableEntity that = (HardwareServicesTableEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
