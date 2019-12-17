package classes;

import javax.persistence.*;

@Entity
@Table(name = "sysbook", schema = "public", catalog = "lab2sysadm")
public class SysbookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hardware", unique = true, nullable = false)
    private int id_hardware;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = TypeTableEntity.class)
    @JoinColumn(name = "type_id")
    private TypeTableEntity type;

    @Basic
    @Column(name = "location")
    private String location;

    public SysbookEntity() {}

    public SysbookEntity(TypeTableEntity type, String location) {
        this.type = type;
        this.location = location;
    }

    public TypeTableEntity getType() {
        return type;
    }

    public void setType(TypeTableEntity type) {
        this.type = type;
    }

    public int getId() {
        return id_hardware;
    }

    public void setId(int id) {
        this.id_hardware = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysbookEntity that = (SysbookEntity) o;

        if (id_hardware != that.id_hardware) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id_hardware;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
