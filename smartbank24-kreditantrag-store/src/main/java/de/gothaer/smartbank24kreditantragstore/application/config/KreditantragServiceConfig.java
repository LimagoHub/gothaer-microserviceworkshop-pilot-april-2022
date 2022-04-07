package de.gothaer.smartbank24kreditantragstore.application.config;


import de.gothaer.smartbank24kreditantragstore.adapter.mapper.KreditantragMapper;
import de.gothaer.smartbank24kreditantragstore.application.repositories.KreditantragRepository;
import de.gothaer.smartbank24kreditantragstore.domain.services.KreditantragService;
import de.gothaer.smartbank24kreditantragstore.domain.services.internal.KreditantragServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KreditantragServiceConfig {

    @Bean
    public KreditantragService createKreditantragService(KreditantragRepository repo , KreditantragMapper mapper) {
        return new KreditantragServiceImpl(repo, mapper);
    }
}
