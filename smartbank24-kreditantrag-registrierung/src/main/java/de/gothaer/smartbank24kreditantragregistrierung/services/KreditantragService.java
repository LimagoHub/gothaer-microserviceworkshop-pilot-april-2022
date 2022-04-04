package de.gothaer.smartbank24kreditantragregistrierung.services;


import de.gothaer.smartbank24kreditantragregistrierung.services.model.Kreditantrag;

public interface KreditantragService {

    public void register(Kreditantrag kreditantrag) throws KreditantragServiceException ;
}
