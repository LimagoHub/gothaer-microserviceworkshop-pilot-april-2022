package de.gothaer.smartbank24kreditantragstore.application.handler.internal;

import de.gothaer.smartbank24kreditantragstore.application.events.KreditantragEvent;
import de.gothaer.smartbank24kreditantragstore.application.events.ScoringEvent;
import de.gothaer.smartbank24kreditantragstore.application.events.eventstore.services.EventService;
import de.gothaer.smartbank24kreditantragstore.application.handler.KreditantragHandler;
import de.gothaer.smartbank24kreditantragstore.application.mapper.KreditantragDTOMapper;
import de.gothaer.smartbank24kreditantragstore.domain.aggregates.Kreditantrag;
import de.gothaer.smartbank24kreditantragstore.domain.services.KreditantragService;
import de.gothaer.smartbank24kreditantragstore.domain.services.KreditantragServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Component
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = KreditantragServiceException.class)
@Slf4j
public class KreditantragHandlerImpl implements KreditantragHandler {

    public static final String ERROR_MESSAGE = "Fehler beim Verarbeiten des Antrags mit der IS '%s'.";
    private final KreditantragService kreditantragService;
    private final EventService eventService;
    private final KreditantragDTOMapper mapper;
    @Override
    public void handleKreditantragRegistriert(KreditantragEvent event) throws KreditantragServiceException {

        try {
            eventService.storeKreditantragRegistiertEvent(event);
            kreditantragService.speichereKreditantrag(mapper.convert(event.getKreditantrag()));
        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(ERROR_MESSAGE,event.getKreditantrag().getCreditApplicationId()) , e);
        }
    }

    @Override
    public void handleScoringPositive(ScoringEvent event) throws KreditantragServiceException {

        try {

            eventService.storeScoringPositiveEvent(event);
            kreditantragService.verarbeitePositivesScoring(event.getCreditApplicationId());

        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(ERROR_MESSAGE,event.getCreditApplicationId()) , e);
        }
    }



    @Override
    public void handleScoringNegative(ScoringEvent event) throws KreditantragServiceException {
        try {
            eventService.storeScoringNegativeEvent(event);
            kreditantragService.verarbeiteNegativesScoring(event.getCreditApplicationId());

        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(ERROR_MESSAGE,event.getCreditApplicationId()) , e);
        }
    }

    @Override
    public void handleCityScoringPositive(ScoringEvent event) throws KreditantragServiceException {
        try {
            eventService.storeCityScoringPositiveEvent(event);
            kreditantragService.verarbeitePositivesCityScoring(event.getCreditApplicationId());

        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(ERROR_MESSAGE,event.getCreditApplicationId()) , e);
        }
    }

    @Override
    public void handleCityScoringNegative(ScoringEvent event) throws KreditantragServiceException {
        try {
            eventService.storeCityScoringNegativeEvent(event);
            kreditantragService.verarbeiteNegativesCityScoring(event.getCreditApplicationId());

        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(ERROR_MESSAGE,event.getCreditApplicationId()) , e);
        }
    }

    @Override
    public void fireKreditantragPersitedEvent(Kreditantrag kreditantrag) {
        eventService.storeAndFireKreditAntragPersistiertOutEvent(KreditantragEvent.builder().kreditantrag(mapper.convert(kreditantrag)).build());

    }

    @Override
    public void fireKreditantragAcceptedEvent(Kreditantrag kreditantrag) {
        eventService.storeAndFireKreditvertragEmpfohlenOutEvent(KreditantragEvent.builder().kreditantrag(mapper.convert(kreditantrag)).build());
    }

    @Override
    public void fireKreditantragDeniedEvent(Kreditantrag kreditantrag) {
        eventService.storeAndFireKreditvertragAbgelehntOutEvent(KreditantragEvent.builder().kreditantrag(mapper.convert(kreditantrag)).build());
    }

    @Override
    public void fireKreditantragStornoEvent(Kreditantrag kreditantrag) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
