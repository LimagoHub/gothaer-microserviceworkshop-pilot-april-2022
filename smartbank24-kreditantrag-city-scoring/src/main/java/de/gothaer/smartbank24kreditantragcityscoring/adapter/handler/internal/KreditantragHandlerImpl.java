package de.gothaer.smartbank24kreditantragcityscoring.adapter.handler.internal;


import de.gothaer.smartbank24kreditantragcityscoring.adapter.events.KreditantragEvent;
import de.gothaer.smartbank24kreditantragcityscoring.adapter.events.ScoringEvent;
import de.gothaer.smartbank24kreditantragcityscoring.adapter.handler.KreditantragHandler;
import de.gothaer.smartbank24kreditantragcityscoring.adapter.mapper.KreditantragDTOMapper;
import de.gothaer.smartbank24kreditantragcityscoring.domain.aggregate.Kreditantrag;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class KreditantragHandlerImpl implements KreditantragHandler {

    private static final String CITY_SCORING_NEGATIVE = "cityscoringnegative-out-0";
    private static final String CITY_SCORING_POSITIVE = "cityscoringpositive-out-0";
    private final StreamBridge bridge;
    private final KreditantragDTOMapper mapper;

    @Override
    public void handle(KreditantragEvent kreditantragEvent) {

        Kreditantrag kreditantrag = mapper.convert(kreditantragEvent.getKreditantrag());
        ScoringEvent scoringEvent = ScoringEvent.builder().creditApplicationId(kreditantrag.getCreditApplicationId()).build();
        if (kreditantrag.isKreditwuerdig()) {
            bridge.send(CITY_SCORING_POSITIVE, scoringEvent);
        } else {
            bridge.send(CITY_SCORING_NEGATIVE, scoringEvent);
        }
    }
}
