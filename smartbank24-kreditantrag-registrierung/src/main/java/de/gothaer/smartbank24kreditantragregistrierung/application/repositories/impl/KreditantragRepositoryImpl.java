package de.gothaer.smartbank24kreditantragregistrierung.application.repositories.impl;

import de.gothaer.smartbank24kreditantragregistrierung.application.mapper.KreditantragMapper;
import de.gothaer.smartbank24kreditantragregistrierung.application.repositories.KreditantragPersistence;
import de.gothaer.smartbank24kreditantragregistrierung.domain.model.Kreditantrag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class KreditantragRepositoryImpl implements de.gothaer.smartbank24kreditantragregistrierung.application.repositories.KreditantragRepository {

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

    @Override
    public Optional<Kreditantrag> findById(String id) {
        return persistence.findById(id).map(mapper::convert);
    }

    @Override
    public Iterable<Kreditantrag> findAll() {
        return mapper.convert(persistence.findAll());
    }
}
