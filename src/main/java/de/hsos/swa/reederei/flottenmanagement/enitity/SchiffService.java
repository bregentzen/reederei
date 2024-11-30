package de.hsos.swa.reederei.flottenmanagement.enitity;

import de.hsos.swa.reederei.auftragsmanagement.boundary.dto.AuftragWebDTO;
import de.hsos.swa.reederei.auftragsmanagement.entity.Auftrag;
import de.hsos.swa.reederei.flottenmanagement.boundary.dto.SchiffWebDTO;
import de.hsos.swa.reederei.flottenmanagement.boundary.dto.SchiffWebDTOId;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.util.Optional;

@Dependent
public class SchiffService {

    @Inject
    SchiffVerwaltung schiffVerwaltung;

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
}
