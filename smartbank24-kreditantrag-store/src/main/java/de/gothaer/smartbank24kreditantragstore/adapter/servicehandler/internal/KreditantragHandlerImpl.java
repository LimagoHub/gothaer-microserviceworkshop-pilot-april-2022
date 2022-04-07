package de.gothaer.smartbank24kreditantragstore.adapter.servicehandler.internal;

import de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.services.EventService;
import de.gothaer.smartbank24kreditantragstore.adapter.mapper.KreditantragDTOMapper;
import de.gothaer.smartbank24kreditantragstore.adapter.events.KreditantragEvent;
import de.gothaer.smartbank24kreditantragstore.adapter.events.ScoringEvent;
import de.gothaer.smartbank24kreditantragstore.adapter.servicehandler.KreditantragHandler;
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
            kreditantragService.speichereKreditantrag(mapper.convert(event.getKreditantrag()));
            eventService.storeKreditantragRegistiertEvent(event);
            eventService.storeAndFireKreditAntragPersistiertOutEvent(KreditantragEvent.builder().kreditantrag(event.getKreditantrag()).build());
        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(ERROR_MESSAGE,event.getKreditantrag().getCreditApplicationId()) , e);
        }
    }

    @Override
    public void handleScoringPositive(ScoringEvent event) throws KreditantragServiceException {

        try {

            eventService.storeScoringPositiveEvent(event);
            storeAndFireKreditvertragEmpfohlenOutEvent(event);
        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(ERROR_MESSAGE,event.getCreditApplicationId()) , e);
        }
    }



    @Override
    public void handleScoringNegative(ScoringEvent event) throws KreditantragServiceException {
        try {
            eventService.storeScoringNegativeEvent(event);
            if (kreditantragService.verarbeiteNegativesScoring(event.getCreditApplicationId()) == Kreditantrag.StatusWechsel.DENIED) {
               eventService.storeAndFireKreditvertragAbgelehntOutEvent(KreditantragEvent.builder().kreditantrag(mapper.convert(kreditantragService.findeKreditantragMitId(event.getCreditApplicationId()))).build());
            }

        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(ERROR_MESSAGE,event.getCreditApplicationId()) , e);
        }
    }

    @Override
    public void handleCityScoringPositive(ScoringEvent event) throws KreditantragServiceException {
        try {
            eventService.storeCityScoringPositiveEvent(event);
            if (kreditantragService.verarbeitePositivesCityScoring(event.getCreditApplicationId()) == Kreditantrag.StatusWechsel.ACCEPTED) {
                eventService.storeAndFireKreditvertragEmpfohlenOutEvent(KreditantragEvent.builder().kreditantrag(mapper.convert(kreditantragService.findeKreditantragMitId(event.getCreditApplicationId()))).build());
            }

        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(ERROR_MESSAGE,event.getCreditApplicationId()) , e);
        }
    }

    @Override
    public void handleCityScoringNegative(ScoringEvent event) throws KreditantragServiceException {
        try {
            eventService.storeCityScoringNegativeEvent(event);
            if (kreditantragService.verarbeiteNegativesCityScoring(event.getCreditApplicationId())== Kreditantrag.StatusWechsel.DENIED) {
                eventService.storeAndFireKreditvertragAbgelehntOutEvent(KreditantragEvent.builder().kreditantrag(mapper.convert(kreditantragService.findeKreditantragMitId(event.getCreditApplicationId()))).build());
            }

        } catch (RuntimeException e) {
            throw new KreditantragServiceException(String.format(ERROR_MESSAGE,event.getCreditApplicationId()) , e);
        }
    }

    private void storeAndFireKreditvertragEmpfohlenOutEvent(ScoringEvent event) {
        if(kreditantragService.verarbeitePositivesScoring(event.getCreditApplicationId()) == Kreditantrag.StatusWechsel.ACCEPTED){
            eventService.storeAndFireKreditvertragEmpfohlenOutEvent(KreditantragEvent.builder().kreditantrag(mapper.convert(kreditantragService.findeKreditantragMitId(event.getCreditApplicationId()))).build());
        }
    }
}
