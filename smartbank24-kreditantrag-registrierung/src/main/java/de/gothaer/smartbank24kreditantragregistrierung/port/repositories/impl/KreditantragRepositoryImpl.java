package de.gothaer.smartbank24kreditantragregistrierung.port.repositories.impl;

import de.gothaer.smartbank24kreditantragregistrierung.port.mapper.KreditantragMapper;
import de.gothaer.smartbank24kreditantragregistrierung.port.repositories.KreditantragPersistence;
import de.gothaer.smartbank24kreditantragregistrierung.services.model.Kreditantrag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class KreditantragRepositoryImpl implements de.gothaer.smartbank24kreditantragregistrierung.port.repositories.KreditantragRepository {

    private final KreditantragPersistence persistence;
    private final KreditantragMapper mapper;

    @Override
    public void save(Kreditantrag antrag) {
        persistence.save(mapper.convert(antrag));
    }

    @Override
    public boolean exists(Kreditantrag k) {
        return persistence.existsById(k.getCreditApplicationId());
    }
}
