package de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.repositories;


import de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.entities.AbstractEventEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<AbstractEventEntity, String> , EventRepositoryExtension{

    @Query("select e from AbstractEventEntity e where e.creditApplicationId like :creditApplicationId order by e.eventTimestamp asc")
    List<AbstractEventEntity> findByCreditApplicationId(String creditApplicationId);
}
