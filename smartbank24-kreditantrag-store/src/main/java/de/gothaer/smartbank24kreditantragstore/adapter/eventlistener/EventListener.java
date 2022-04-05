package de.gothaer.smartbank24kreditantragstore.adapter.eventlistener;


import de.gothaer.smartbank24kreditantragstore.application.events.KreditantragEvent;
import de.gothaer.smartbank24kreditantragstore.application.events.ScoringEvent;
import de.gothaer.smartbank24kreditantragstore.application.handler.KreditantragHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventListener {

    @Bean
    public Consumer<KreditantragEvent> kreditantragregistriert(final KreditantragHandler handler){
        return handler::handleKreditantragRegistriert;
    }


    @Bean
    public Consumer<ScoringEvent> scoringnegative (final KreditantragHandler handler){
        return handler::handleScoringNegative;
    }
    @Bean
    public Consumer<ScoringEvent> scoringpositive (final KreditantragHandler handler){
        return handler::handleScoringPositive;
    }
    @Bean
    public Consumer<ScoringEvent> cityscoringnegative (final KreditantragHandler handler){
        return handler::handleCityScoringNegative;
    }
    @Bean
    public Consumer<ScoringEvent> cityscoringpositive (final KreditantragHandler handler){
        return handler::handleCityScoringPositive;
    }

}
