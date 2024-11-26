package de.hsos.swa.reederei.auftragsmanagement.gateway;

import de.hsos.swa.reederei.auftragsmanagement.entity.Auftrag;
import de.hsos.swa.reederei.auftragsmanagement.entity.AuftragVerwaltung;
import de.hsos.swa.reederei.auftragsmanagement.gateway.dto.AuftragJPAEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TransactionRequiredException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional(value = Transactional.TxType.MANDATORY)
public class AuftragRepository implements AuftragVerwaltung {

    transient EntityManager entityManager;

    @Override
    public Collection<Auftrag> getAllAuftraege() {
        String queryString = "select a from Auftrag a";
        TypedQuery<AuftragJPAEntity> query = this.entityManager
                .createQuery(queryString, AuftragJPAEntity.class);
        List<AuftragJPAEntity> resultList = query.getResultList();

        return resultList.stream()
                .map(this::fromDbEntityAuftrag)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Auftrag> getAuftrag(String id) {
        TypedQuery<AuftragJPAEntity> typedQuery =
                this.entityManager.createNamedQuery("AuftragJPAEntity.findById", AuftragJPAEntity.class);
        typedQuery.setParameter("id", id);
        AuftragJPAEntity foundAuftrag = typedQuery.getSingleResult();

        if(foundAuftrag == null) {
            return Optional.empty();
        }

        Auftrag auftrag = this.fromDbEntityAuftrag(foundAuftrag);

        return Optional.of(auftrag);
    }

    @Override
    //TODO: wie soll geupdatet werden? Eingangsdatum auch ändern z.B.?
    public Optional<Auftrag> updateAuftrag(String id, Auftrag auftrag) {
        AuftragJPAEntity foundAuftrag = this.entityManager.find(AuftragJPAEntity.class, id);
        if(foundAuftrag == null) {
            return Optional.empty();
        }
        foundAuftrag.setBeschreibung(auftrag.getBeschreibung());

        try {
            AuftragJPAEntity mergedAuftrag = this.entityManager.merge(foundAuftrag);
            if (mergedAuftrag != null && !mergedAuftrag.getBeschreibung().equals(auftrag.getBeschreibung())) {
                return Optional.empty();
            }
            Auftrag mergedAuftragToReturn = this.fromDbEntityAuftrag(mergedAuftrag);

            return Optional.of(mergedAuftragToReturn);
        } catch (IllegalStateException | TransactionRequiredException e) {
            System.err.println("Auftrag could not be updated!!!");
            return Optional.empty();
        }

    }

    @Override
    public boolean deleteAuftrag(String id) {
        AuftragJPAEntity foundAuftrag = this.entityManager.find(AuftragJPAEntity.class, id);
        if(foundAuftrag == null) {
            return false;
        }

        try {
            this.entityManager.remove(foundAuftrag);
            return true;
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            System.err.println("Auftrag could not be deleted!!!");
            return false;
        }
    }

    private Auftrag fromDbEntityAuftrag(AuftragJPAEntity auftrag) {
        return new Auftrag(auftrag.getId(), auftrag.getBeschreibung(), auftrag.getEingangsdatum(), auftrag.getSchiffURL());
    }
}
