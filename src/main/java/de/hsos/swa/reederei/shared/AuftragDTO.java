package de.hsos.swa.reederei.shared;

public class AuftragDTO {
    private Long id;
    private String beschreibung;
    private String eingangsdatum;
    private String schiffURL;

    public AuftragDTO() {
    }

    public AuftragDTO(Long id, String beschreibung, String eingangsdatum, String schiffURL) {
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

    public String getEingangsdatum() {
        return eingangsdatum;
    }

    public void setEingangsdatum(String eingangsdatum) {
        this.eingangsdatum = eingangsdatum;
    }

    public String getSchiffURL() {
        return schiffURL;
    }

    public void setSchiffURL(String schiffURL) {
        this.schiffURL = schiffURL;
    }
}