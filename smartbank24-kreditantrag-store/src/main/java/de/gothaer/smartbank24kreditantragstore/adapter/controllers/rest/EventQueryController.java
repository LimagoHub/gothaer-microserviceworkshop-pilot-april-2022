package de.gothaer.smartbank24kreditantragstore.adapter.controllers.rest;


import de.gothaer.smartbank24kreditantragstore.application.events.eventstore.entities.AbstractEventEntity;
import de.gothaer.smartbank24kreditantragstore.application.events.eventstore.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@AllArgsConstructor
public class EventQueryController {

    private final EventRepository repo;
    @GetMapping(path="/{id}")
    public ResponseEntity<AbstractEventEntity>findEventById(@PathVariable String id) {
        return ResponseEntity.of(repo.findById(id));
        //return Mono.just(ResponseEntity.of(repo.findById(id)));
    }

    @GetMapping(path="")
    public List<AbstractEventEntity> findAll(@RequestParam(required = false, defaultValue = "%",name = "kreditantrag-id") String id) {
        return repo.findByCreditApplicationId(id);
    }
}
