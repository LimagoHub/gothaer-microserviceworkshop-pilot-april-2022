package de.gothaer.smartbank24kreditantragregistrierung.domain.services.impl;


import de.gothaer.smartbank24kreditantragregistrierung.application.repositories.KreditantragRepository;
import de.gothaer.smartbank24kreditantragregistrierung.domain.services.KreditantragService;
import de.gothaer.smartbank24kreditantragregistrierung.domain.services.KreditantragServiceException;
import de.gothaer.smartbank24kreditantragregistrierung.domain.model.Kreditantrag;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class KreditantragServiceImpl implements KreditantragService {

    private final KreditantragRepository repo;

    @Override
    public void register(Kreditantrag kreditantrag) throws KreditantragServiceException {
        if(repo.exists(kreditantrag)) {
            throw new KreditantragServiceException("Upps");
        }
        repo.save(kreditantrag);

    }

    @Override
    public Optional<Kreditantrag> findById(String id) throws KreditantragServiceException {
        return repo.findById(id);
    }

    @Override
    public Iterable<Kreditantrag> findAll() throws KreditantragServiceException {
        return repo.findAll();
    }
}
