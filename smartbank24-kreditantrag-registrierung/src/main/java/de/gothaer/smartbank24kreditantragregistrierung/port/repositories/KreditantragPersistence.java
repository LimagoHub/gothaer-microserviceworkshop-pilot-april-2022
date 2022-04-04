package de.gothaer.smartbank24kreditantragregistrierung.port.repositories;

import de.gothaer.smartbank24kreditantragregistrierung.port.repositories.entities.KreditantragEntity;
import org.springframework.data.repository.CrudRepository;

public interface KreditantragPersistence extends CrudRepository<KreditantragEntity, String> {
}
