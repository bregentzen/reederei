package de.hsos.swa.reederei.auftragsmanagement.entity;

import jakarta.enterprise.context.Dependent;

import java.util.Collection;
import java.util.Optional;

public interface AuftragVerwaltung {
    Collection<Auftrag> getAllAuftraege();
    Optional<Auftrag> getAuftrag(Long id);
    Optional<Auftrag> updateAuftrag(Long id, Auftrag auftrag);
    boolean deleteAuftrag(Long id);
    Optional<Auftrag> createAuftrag(Auftrag auftrag);
    void markShipAsBooked(Long auftragId, String schiffURL);
}
