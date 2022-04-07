package de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.repositories.impl;


import de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.entities.*;

import de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.repositories.EventRepositoryExtension;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@AllArgsConstructor
public class EventRepositoryExtensionImpl implements EventRepositoryExtension {

    private EntityManager em;

    @Override
    public void save(KreditantragRegistriertInEventEntity entity) {
        em.persist(entity);
    }

    @Override
    public void save(KreditAntragPersistiertOutEventEntity entity) {
        em.persist(entity);
    }

    @Override
    public void save(ScoringPositiveInEventEntity entity) {

        em.persist(entity);
    }

    @Override
    public void save(ScoringNegativInEventEntity entity) {
        em.persist(entity);
    }

    @Override
    public void save(CityScoringPositiveInEventEntity entity) {
        em.persist(entity);
    }

    @Override
    public void save(CityScoringNegativInEventEntity entity) {
        em.persist(entity);
    }

    @Override
    public void save(KreditvertragEmpfohlenOutEventEntity entity) {
        em.persist(entity);
    }

    @Override
    public void save(KreditvertragAbgelehntOutEventEntity entity) {
        em.persist(entity);
    }
}
