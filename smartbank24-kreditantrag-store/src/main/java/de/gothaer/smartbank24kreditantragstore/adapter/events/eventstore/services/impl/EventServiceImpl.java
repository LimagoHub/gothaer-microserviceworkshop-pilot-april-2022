package de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.services.impl;


import de.gothaer.smartbank24kreditantragstore.adapter.events.KreditantragEvent;
import de.gothaer.smartbank24kreditantragstore.adapter.events.ScoringEvent;
import de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.mapper.EventToEntityMapper;
import de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.repositories.EventRepository;
import de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private static final String KREDITANTRAG_PERSISTIERT ="kreditantragPersistiert-out-0";
    private static final String KREDITVERTRAG_EMPFOHLEN ="kreditvertragEmpfohlen-out-0";
    private static final String KREDITVERTRAG_ABGELEHNT ="kreditvertragAbgelehnt-out-0";



    private final StreamBridge bridge;
    private final EventRepository repository;
    private final EventToEntityMapper mapper;



    @Override
    public void storeKreditantragRegistiertEvent(KreditantragEvent event) {
        repository.save(mapper.convertKreditantragRegistriertInEventEntity(event));
    }

    @Override
    public void storeScoringPositiveEvent(ScoringEvent event) {
        repository.save(mapper.convertScoringPositiveInEventEntity(event));
    }

    @Override
    public void storeScoringNegativeEvent(ScoringEvent event) {
        repository.save(mapper.convertScoringNegativInEventEntity(event));
    }

    @Override
    public void storeCityScoringPositiveEvent(ScoringEvent event) {
        repository.save(mapper.convertCityScoringPositiveInEventEntity(event));
    }

    @Override
    public void storeCityScoringNegativeEvent(ScoringEvent event) {
        repository.save(mapper.convertCityScoringNegativInEventEntity(event));
    }

    @Override
    public void storeAndFireKreditAntragPersistiertOutEvent(KreditantragEvent event) {
        repository.save(mapper.convertKreditAntragPersistiertOutEventEntity(event));
        bridge.send(KREDITANTRAG_PERSISTIERT,event);
    }

    @Override
    public void storeAndFireKreditvertragEmpfohlenOutEvent(KreditantragEvent event) {
        repository.save(mapper.convertKreditvertragEmpfohlenOutEventEntity(event));
        bridge.send(KREDITVERTRAG_EMPFOHLEN,event);
    }

    @Override
    public void storeAndFireKreditvertragAbgelehntOutEvent(KreditantragEvent event) {
        repository.save(mapper.convertKreditvertragAbgelehntOutEventEntity(event));
        bridge.send(KREDITVERTRAG_ABGELEHNT,event);
    }
}
