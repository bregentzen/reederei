package de.hsos.swa.reederei.flottenmanagement.gateway;

import de.hsos.swa.reederei.flottenmanagement.enitity.Schiff;
import de.hsos.swa.reederei.flottenmanagement.enitity.SchiffVerwaltung;
import de.hsos.swa.reederei.flottenmanagement.gateway.dto.SchiffJPAEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional(value = Transactional.TxType.MANDATORY)
public class SchiffRepository implements SchiffVerwaltung {

    @Inject
    transient EntityManager entityManager;

    @Override
    public Optional<Schiff> createSchiff(Schiff schiff) {
        SchiffJPAEntity schiffEntity = new SchiffJPAEntity();
        schiffEntity.setName(schiff.getName());
        schiffEntity.setGebucht(schiff.isGebucht());

        try {
            this.entityManager.persist(schiffEntity);
            return Optional.of(fromDbEntitySchiff(schiffEntity));
        } catch (PersistenceException e) {
            System.err.println("Autrag konnte nicht erstellt werden: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Schiff> getAllSchiffe() {
        String queryString = "SELECT s FROM Schiff s";
        TypedQuery<SchiffJPAEntity> query = this.entityManager.createQuery(queryString, SchiffJPAEntity.class);
        List<SchiffJPAEntity> resultList = query.getResultList();

        return resultList.stream()
                .map(this::fromDbEntitySchiff)
                .collect(Collectors.toList());
    }

    @Override
    public void updateSchiff(Schiff schiff) {
        SchiffJPAEntity foundSchiff = this.entityManager.find(SchiffJPAEntity.class, schiff.getId());
        if (foundSchiff != null) {
            foundSchiff.setName(schiff.getName());
            foundSchiff.setGebucht(schiff.isGebucht());
            try {
                this.entityManager.merge(foundSchiff);
            } catch (PersistenceException e) {
                System.err.println("Schiff konnte nicht aktualisiert werden: " + e.getMessage());
            }
        }
    }

    @Override
    public Optional<Schiff> getSchiff(Long id) {
        TypedQuery<SchiffJPAEntity> query =
                this.entityManager.createNamedQuery("SchiffJPAEntity.findById", SchiffJPAEntity.class);
        query.setParameter("id", id);
        SchiffJPAEntity foundSchiff = query.getSingleResult();

        if (foundSchiff == null) {
            return Optional.empty();
        }

        Schiff schiff = fromDbEntitySchiff(foundSchiff);

        return Optional.of(schiff);
    }

    private Schiff fromDbEntitySchiff(SchiffJPAEntity schiffJPAEntity) {
        return new Schiff(schiffJPAEntity.getId(), schiffJPAEntity.getName(), schiffJPAEntity.isGebucht());
    }
}