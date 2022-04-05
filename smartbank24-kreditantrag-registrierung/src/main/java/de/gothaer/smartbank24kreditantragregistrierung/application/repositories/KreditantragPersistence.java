package de.gothaer.smartbank24kreditantragregistrierung.application.repositories;

import de.gothaer.smartbank24kreditantragregistrierung.application.repositories.entities.KreditantragEntity;
import org.springframework.data.repository.CrudRepository;

public interface KreditantragPersistence extends CrudRepository<KreditantragEntity, String> {
}
