package de.gothaer.smartbank24kreditantragregistrierung.services.impl;


import de.gothaer.smartbank24kreditantragregistrierung.port.repositories.KreditantragRepository;
import de.gothaer.smartbank24kreditantragregistrierung.services.KreditantragService;
import de.gothaer.smartbank24kreditantragregistrierung.services.KreditantragServiceException;
import de.gothaer.smartbank24kreditantragregistrierung.services.model.Kreditantrag;
import lombok.AllArgsConstructor;

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
}
