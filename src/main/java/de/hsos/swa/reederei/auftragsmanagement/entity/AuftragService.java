package de.hsos.swa.reederei.auftragsmanagement.entity;

import de.hsos.swa.reederei.auftragsmanagement.boundary.dto.AuftragWebDTO;
import de.hsos.swa.reederei.auftragsmanagement.boundary.dto.AuftragWebDTOId;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.Optional;

@Dependent
public class AuftragService {

    @Inject
    AuftragVerwaltung auftragVerwaltung;

    /*
    Methoden ohne ID
    */

    public AuftragWebDTOId createAuftrag(AuftragWebDTO auftrag) {
        Auftrag auftragEntity = toEntity(auftrag);
        Optional<Auftrag> createdAuftrag = auftragVerwaltung.createAuftrag(auftragEntity);
        return createdAuftrag.map(this::toDTOwithId).orElse(null);
    }

    public Collection<AuftragWebDTOId> getAllAuftraegeDTO() {
        return auftragVerwaltung.getAllAuftraege().stream()
                .map(this::toDTOwithId)
                .toList();
    }

    /*
    Methoden mit ID
     */

    public AuftragWebDTOId getAuftrag(Long id) {
        Optional<Auftrag> auftrag = auftragVerwaltung.getAuftrag(id);
        return auftrag.map(this::toDTOwithId).orElse(null);
    }

    public boolean deleteAuftrag(Long id) {
        return auftragVerwaltung.deleteAuftrag(id);
    }

    public AuftragWebDTOId updateAuftrag(Long id, AuftragWebDTOId auftrag) {
        Auftrag auftragEntity = toEntityWithId(auftrag);
        Optional<Auftrag> updatedAuftrag = auftragVerwaltung.updateAuftrag(id, auftragEntity);
        return updatedAuftrag.map(this::toDTOwithId).orElse(null);
    }

    /*
    Methoden zum Umwandeln von DTOs in Entities und umgekehrt
     */

    private AuftragWebDTO toDTO(Auftrag auftrag) {
        return new AuftragWebDTO(auftrag.getBeschreibung(), auftrag.getEingangsdatum(), auftrag.getSchiffURL());
    }

    private AuftragWebDTOId toDTOwithId(Auftrag auftrag) {
        return new AuftragWebDTOId(auftrag.getId(), auftrag.getBeschreibung(), auftrag.getEingangsdatum(), auftrag.getSchiffURL());
    }

    private Auftrag toEntity(AuftragWebDTO auftragWebDTO) {
        return new Auftrag(-1, auftragWebDTO.getBeschreibung(), auftragWebDTO.getEingangsdatum(), auftragWebDTO.getSchiffURL());
    }

    private Auftrag toEntityWithId(AuftragWebDTOId auftragWebDTO) {
        return new Auftrag(auftragWebDTO.getId(), auftragWebDTO.getBeschreibung(), auftragWebDTO.getEingangsdatum(), auftragWebDTO.getSchiffURL());
    }
}