package classes;

import javax.persistence.*;

@Entity
@Table(name = "services_table", schema = "public", catalog = "lab2sysadm")
public class ServicesTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "service")
    private String service;

    public ServicesTableEntity() {}

    public ServicesTableEntity(String service) {
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicesTableEntity that = (ServicesTableEntity) o;

        if (id != that.id) return false;
        if (service != null ? !service.equals(that.service) : that.service != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (service != null ? service.hashCode() : 0);
        return result;
    }
}
