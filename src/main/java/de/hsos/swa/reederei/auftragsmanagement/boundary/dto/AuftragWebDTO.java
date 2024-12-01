package de.hsos.swa.reederei.auftragsmanagement.boundary.dto;

import java.util.Date;

public class AuftragWebDTO {
    private String beschreibung;
    private Date eingangsdatum;
    private String schiffURL;

    public AuftragWebDTO(){
    }

    public AuftragWebDTO(String beschreibung, Date eingangsdatum, String schiffURL) {
        this.beschreibung = beschreibung;
        this.eingangsdatum = eingangsdatum;
        this.schiffURL = schiffURL;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Date getEingangsdatum() {
        return eingangsdatum;
    }

    public String getSchiffURL() {
        return schiffURL;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setEingangsdatum(Date eingangsdatum) {
        this.eingangsdatum = eingangsdatum;
    }

    public void setSchiffURL(String schiffURL) {
        this.schiffURL = schiffURL;
    }
}
