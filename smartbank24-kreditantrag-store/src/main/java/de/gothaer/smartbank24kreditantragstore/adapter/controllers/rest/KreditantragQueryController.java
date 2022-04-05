package de.gothaer.smartbank24kreditantragstore.adapter.controllers.rest;



import de.gothaer.smartbank24kreditantragstore.application.repositories.KreditantragRepository;
import de.gothaer.smartbank24kreditantragstore.application.repositories.entities.KreditantragEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/kreditantraege")
@AllArgsConstructor
public class KreditantragQueryController {

    private final KreditantragRepository repo;

    @GetMapping(path="/{id}")
    public Mono<ResponseEntity<KreditantragEntity>> findAntragById(@PathVariable String id) {
        return Mono.just(ResponseEntity.of(repo.findById(id)));
    }

    @GetMapping(path="")
    public Flux<Iterable<KreditantragEntity>> findAll() {
        return Flux.just(repo.findAll());
    }

}
