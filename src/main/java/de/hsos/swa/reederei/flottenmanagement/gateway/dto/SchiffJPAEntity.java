package de.hsos.swa.reederei.flottenmanagement.gateway.dto;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Schiff")
@Table(name = "SCHIFFE")
@NamedQueries(value = {
        @NamedQuery(name = "SchiffJPAEntity.findById", query = "select s from Schiff s where s.id=:id")
})
public class SchiffJPAEntity {
    @Id
    @TableGenerator(name ="schiff_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "schiff_generator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gebucht")
    private boolean gebucht;

    @Version
    private long version;

    public SchiffJPAEntity() {}

    public SchiffJPAEntity(String name, boolean gebucht, long version) {
        this.name = name;
        this.gebucht = gebucht;
    }

    public SchiffJPAEntity(Long id, String name, boolean gebucht) {
        this.id = id;
        this.name = name;
        this.gebucht = gebucht;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGebucht() {
        return gebucht;
    }

    public void setGebucht(boolean gebucht) {
        this.gebucht = gebucht;
    }



    @Override
    public String toString() {
        return "SchiffJPAEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gebucht=" + gebucht +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchiffJPAEntity that = (SchiffJPAEntity) o;
        return gebucht == that.gebucht && version == that.version && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gebucht, version);
    }
}

