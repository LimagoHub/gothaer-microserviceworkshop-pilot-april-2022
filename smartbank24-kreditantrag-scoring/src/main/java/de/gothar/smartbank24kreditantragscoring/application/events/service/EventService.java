package de.gothar.smartbank24kreditantragscoring.application.events.service;

import de.gothar.smartbank24kreditantragscoring.application.events.ScoringEvent;
import de.gothar.smartbank24kreditantragscoring.domain.aggregates.Kreditantrag;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


    @Service
    @AllArgsConstructor
    public class EventService{

        private static final String SCORING_POSITIV ="scoringpositive-out-0";
        private static final String SCORING_NEGATIV ="scoringnegative-out-0";
        private final StreamBridge bridge;

        public void publishScoringPositivEvent(Kreditantrag kreditantrag) {
            ScoringEvent event = ScoringEvent.builder().creditApplicationId(kreditantrag.getCreditApplicationId()).build();
            bridge.send(SCORING_POSITIV, event);
        }


        public void publishScoringNegativEvent(Kreditantrag kreditantrag) {
            ScoringEvent event = ScoringEvent.builder().creditApplicationId(kreditantrag.getCreditApplicationId()).build();
            bridge.send(SCORING_NEGATIV, event);
        }



}
