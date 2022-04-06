package de.gothar.smartbank24kreditantragscoring.application.handler.internal;

import de.gothar.smartbank24kreditantragscoring.application.events.KreditantragEvent;
import de.gothar.smartbank24kreditantragscoring.application.events.service.EventService;
import de.gothar.smartbank24kreditantragscoring.application.handler.KreditantragHandler;
import de.gothar.smartbank24kreditantragscoring.application.mapper.KreditantragDTOMapper;
import de.gothar.smartbank24kreditantragscoring.domain.aggregates.Kreditantrag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class KreditantragHandlerImpl implements KreditantragHandler {

    private final EventService eventService;
    private final KreditantragDTOMapper mapper;

    @Override
    public void handle(KreditantragEvent kreditantragEvent) {

        Kreditantrag kreditantrag = mapper.convert(kreditantragEvent.getKreditantrag());
        if (kreditantrag.isKreditwuerdig()) {
            eventService.publishScoringPositivEvent(kreditantrag);
        } else {
            eventService.publishScoringNegativEvent(kreditantrag);
        }
    }
}
