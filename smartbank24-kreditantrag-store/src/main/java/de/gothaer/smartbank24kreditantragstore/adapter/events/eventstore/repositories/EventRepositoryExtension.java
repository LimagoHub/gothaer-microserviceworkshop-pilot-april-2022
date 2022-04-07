package de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.repositories;


import de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.entities.*;


public interface EventRepositoryExtension {

    void save(KreditantragRegistriertInEventEntity entity);
    void save(KreditAntragPersistiertOutEventEntity entity);
    void save(ScoringPositiveInEventEntity entity);
    void save(ScoringNegativInEventEntity entity);
    void save(CityScoringPositiveInEventEntity entity);
    void save(CityScoringNegativInEventEntity entity);
    void save(KreditvertragEmpfohlenOutEventEntity entity);
    void save(KreditvertragAbgelehntOutEventEntity entity);
}
