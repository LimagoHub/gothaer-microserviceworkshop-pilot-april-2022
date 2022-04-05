package de.gothaer.smartbank24kreditantragregistrierung.application.repositories;

import de.gothaer.smartbank24kreditantragregistrierung.domain.model.Kreditantrag;

import java.util.Optional;

public interface KreditantragRepository {
    void save(Kreditantrag antrag);

    boolean exists(Kreditantrag k);

    Optional<Kreditantrag> findById(String id);

    Iterable<Kreditantrag> findAll();
}
