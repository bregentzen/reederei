package de.hsos.swa.reederei.auftragsmanagement.gateway.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Version;

@Entity(name="Auftrag")
@Table(name="AUFTRAEGE")
@NamedQuery(name = "AuftragJPAEntity.findByBeschreibung",
        query = "SELECT a FROM Auftrag a WHERE a.beschreibung=:beschreibung")
@Cacheable(true)
public class AuftragJPAEntity implements Serializable {
    @Id
    @TableGenerator(name="auftrag_generator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "auftrag_generator")
    private Long id;

    @Column(name="beschreibung")
    private String beschreibung;

    @Column(name="eingangsdatum")
    private Date eingangsdatum;

    @Column(name="schiff_url")
    private String schiffURL;

    @Version
    private Long versionsnr;

    public AuftragJPAEntity() {
    }

    public AuftragJPAEntity(String beschreibung, Date eingangsdatum, String schiffURL) {
        this.beschreibung = beschreibung;
        this.eingangsdatum = eingangsdatum;
        this.schiffURL = schiffURL;
    }

    public AuftragJPAEntity(Long id, String beschreibung, Date eingangsdatum, String schiffURL) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.eingangsdatum = eingangsdatum;
        this.schiffURL = schiffURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Date getEingangsdatum() {
        return eingangsdatum;
    }

    public void setEingangsdatum(Date eingangsdatum) {
        this.eingangsdatum = eingangsdatum;
    }

    public String getSchiffURL() {
        return schiffURL;
    }

    public void setSchiffURL(String schiffURL) {
        this.schiffURL = schiffURL;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AuftragJPAEntity other = (AuftragJPAEntity) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "id=" + id + ", beschreibung=" + beschreibung + ", eingangsdatum=" + eingangsdatum + ", schiffURL=" + schiffURL;
    }
}