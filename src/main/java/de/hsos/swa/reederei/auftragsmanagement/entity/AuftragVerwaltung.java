package de.hsos.swa.reederei.auftragsmanagement.entity;

import java.util.Collection;
import java.util.Optional;

public interface AuftragVerwaltung {
    Collection<Auftrag> getAllAuftraege();
    Optional<Auftrag> getAuftrag(String id);
    Optional<Auftrag> updateAuftrag(String id, Auftrag auftrag);
    boolean deleteAuftrag(String id);

}
