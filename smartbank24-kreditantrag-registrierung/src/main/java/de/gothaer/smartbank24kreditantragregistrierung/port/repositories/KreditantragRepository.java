package de.gothaer.smartbank24kreditantragregistrierung.port.repositories;

import de.gothaer.smartbank24kreditantragregistrierung.services.model.Kreditantrag;

public interface KreditantragRepository {
    void save(Kreditantrag antrag);

    boolean exists(Kreditantrag k);
}
