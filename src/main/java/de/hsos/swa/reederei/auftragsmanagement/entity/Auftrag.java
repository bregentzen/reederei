package de.hsos.swa.reederei.auftragsmanagement.entity;

import java.util.Date;

public class Auftrag {
    private long id;
    private String beschreibung;
    private Date eingangsdatum;
    private String schiffURL;

    public Auftrag() {
    }

    public Auftrag(long id, String beschreibung, Date eingangsdatum, String schiffURL) {
        this.id = id;
        this.beschreibung = beschreibung;
        this.eingangsdatum = eingangsdatum;
        this.schiffURL = schiffURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
