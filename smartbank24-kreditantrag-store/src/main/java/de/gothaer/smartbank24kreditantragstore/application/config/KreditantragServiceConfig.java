package de.gothaer.smartbank24kreditantragstore.application.config;


import de.gothaer.smartbank24kreditantragstore.application.handler.KreditantragHandler;
import de.gothaer.smartbank24kreditantragstore.application.mapper.KreditantragMapper;
import de.gothaer.smartbank24kreditantragstore.application.repositories.KreditantragRepository;
import de.gothaer.smartbank24kreditantragstore.domain.aggregates.Kreditantrag;
import de.gothaer.smartbank24kreditantragstore.domain.services.KreditantragService;
import de.gothaer.smartbank24kreditantragstore.domain.services.internal.KreditantragServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.Flow;

@Configuration
@AllArgsConstructor
public class KreditantragServiceConfig {

    private final KreditantragHandler handler;

    @Bean
    public KreditantragService createKreditantragService(KreditantragRepository repo , KreditantragMapper mapper) {
        var service = new KreditantragServiceImpl(repo, mapper);
        service.subscribe(new Endsubscriber());
        return service;
    }

    class Endsubscriber implements Flow.Subscriber<Map.Entry<Kreditantrag,Kreditantrag.StatusWechsel>> {

        private Flow.Subscription subscription;
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(Map.Entry<Kreditantrag, Kreditantrag.StatusWechsel> item) {
            switch (item.getValue()) {
                case PERSISTED:
                    handler.fireKreditantragPersitedEvent(item.getKey());
                    break;
                case ACCEPTED:
                    handler.fireKreditantragAcceptedEvent(item.getKey());
                    break;
                case DENIED:
                    handler.fireKreditantragDeniedEvent(item.getKey());
                    break;
                case STORNO:
                    handler.fireKreditantragStornoEvent(item.getKey());
                    break;

            }
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
    }
}
