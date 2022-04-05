package de.gothaer.smartbank24kreditantragstore.application.repositories;

import de.gothaer.smartbank24kreditantragstore.application.repositories.entities.KreditantragEntity;
import org.springframework.data.repository.CrudRepository;

public interface KreditantragRepository extends CrudRepository<KreditantragEntity, String> {
}
