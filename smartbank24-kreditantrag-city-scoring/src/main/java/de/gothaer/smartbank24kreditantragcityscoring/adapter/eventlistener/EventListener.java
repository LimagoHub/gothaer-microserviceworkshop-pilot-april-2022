package de.gothaer.smartbank24kreditantragcityscoring.adapter.eventlistener;



import de.gothaer.smartbank24kreditantragcityscoring.adapter.events.KreditantragEvent;
import de.gothaer.smartbank24kreditantragcityscoring.adapter.events.ScoringEvent;
import de.gothaer.smartbank24kreditantragcityscoring.adapter.handler.KreditantragHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventListener {

    @Bean
    public Consumer<KreditantragEvent> kreditantragPersistiert (KreditantragHandler handler) {
        return handler::handle;
    }




}
