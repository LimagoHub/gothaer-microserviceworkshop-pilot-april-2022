package de.gothaer.smartbank24kreditantragregistrierung.application.config;

import de.gothaer.smartbank24kreditantragregistrierung.application.repositories.KreditantragRepository;
import de.gothaer.smartbank24kreditantragregistrierung.domain.services.KreditantragService;
import de.gothaer.smartbank24kreditantragregistrierung.domain.services.impl.KreditantragServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KreditantragConfig {

    @Bean
    public KreditantragService createKreditantragService(KreditantragRepository repo) {
        return new KreditantragServiceImpl(repo);
    }
}
