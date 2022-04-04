package de.gothaer.smartbank24kreditantragregistrierung.port.repositories;

import de.gothaer.smartbank24kreditantragregistrierung.port.mapper.KreditantragMapper;
import de.gothaer.smartbank24kreditantragregistrierung.port.repositories.entities.KreditantragEntity;
import de.gothaer.smartbank24kreditantragregistrierung.services.model.Kreditantrag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class KreditantragRepository {

    private final KreditantragPersistence persistence;
    private final KreditantragMapper mapper;

    public void save(Kreditantrag antrag) {
        persistence.save(mapper.convert(antrag));
    }

    public boolean exists(Kreditantrag k) {
        return persistence.existsById(k.getCreditApplicationId());
    }
}
