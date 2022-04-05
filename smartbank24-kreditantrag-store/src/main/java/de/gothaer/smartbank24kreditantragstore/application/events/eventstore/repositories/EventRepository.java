package de.gothaer.smartbank24kreditantragstore.application.events.eventstore.repositories;


import de.gothaer.smartbank24kreditantragstore.application.events.eventstore.entities.AbstractEventEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<AbstractEventEntity, String> , EventRepositoryExtension{

    List<AbstractEventEntity> findByCreditApplicationIdLikeOrderByEventTimestampAsc(String creditApplicationId);
}