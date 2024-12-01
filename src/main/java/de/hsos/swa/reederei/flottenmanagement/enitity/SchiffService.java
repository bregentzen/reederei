package de.hsos.swa.reederei.flottenmanagement.enitity;

import de.hsos.swa.reederei.flottenmanagement.boundary.dto.SchiffWebDTO;
import de.hsos.swa.reederei.flottenmanagement.boundary.dto.SchiffWebDTOId;
import de.hsos.swa.reederei.shared.AuftragDTO;
import de.hsos.swa.reederei.shared.SchiffDTO;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.event.Observes;

import java.util.List;
import java.util.Optional;

@Dependent
public class SchiffService {

    @Inject
    SchiffVerwaltung schiffVerwaltung;

    @Inject
    Event<SchiffDTO> auftragAngenommenEvent;

    /*
    Methoden ohne ID
     */

    public SchiffWebDTOId createSchiff(SchiffWebDTO schiff) {
        Schiff schiffEntity = toEntity(schiff);
        Optional<Schiff> createdSchiff = schiffVerwaltung.createSchiff(schiffEntity);
        return createdSchiff.map(this::toDTOwithId).orElse(null);
    }

    /*
    Methoden mit ID
     */
    public SchiffWebDTOId getSchiff(Long id) {
        Optional<Schiff> schiff = schiffVerwaltung.getSchiff(id);
        return schiff.map(this::toDTOwithId).orElse(null);
    }

    private Schiff toEntity(SchiffWebDTO schiffWebDTO) {
        return new Schiff(-1, schiffWebDTO.getName(), schiffWebDTO.isGebucht());
    }

    private SchiffWebDTOId toDTOwithId(Schiff schiff) {
        return new SchiffWebDTOId(schiff.getId(), schiff.getName(), schiff.isGebucht());
    }

    public void onNewAuftrag(@Observes AuftragDTO auftragDTO) {
        List<Schiff> ships = schiffVerwaltung.getAllSchiffe();
        Schiff availableShip = ships.stream()
                .filter(schiff -> !schiff.isGebucht())
                .findFirst()
                .orElse(null);

        if (availableShip != null) {
            System.out.println("Available ship: " + availableShip.toString());
            availableShip.setGebucht(true);
            schiffVerwaltung.updateSchiff(availableShip);
            // Trigger AuftragAngenommen event
            //Erstell mir bitte den link zu diesem Schiff hier als String:
            String schiffURL = "http://localhost:8080/schiffe/" + availableShip.getId();
            auftragAngenommenEvent.fire(new SchiffDTO(auftragDTO.getId(), schiffURL));
        }
    }
}