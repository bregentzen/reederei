package de.hsos.swa.reederei.auftragsmanagement.boundary.dto;

import java.util.Date;

public class AuftragWebDTOId extends AuftragWebDTO {
    private Long id;

    public AuftragWebDTOId(Long id, String beschreibung, Date eingangsdatum, String schiffURL) {
        super(beschreibung, eingangsdatum, schiffURL);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
