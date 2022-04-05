package de.gothaer.smartbank24kreditantragregistrierung.port.config;

import de.gothaer.smartbank24kreditantragregistrierung.port.repositories.KreditantragRepository;
import de.gothaer.smartbank24kreditantragregistrierung.services.KreditantragService;
import de.gothaer.smartbank24kreditantragregistrierung.services.impl.KreditantragServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KreditantragConfig {

    @Bean
    public KreditantragService createKreditantragService(KreditantragRepository repo) {
        return new KreditantragServiceImpl(repo);
    }
}
