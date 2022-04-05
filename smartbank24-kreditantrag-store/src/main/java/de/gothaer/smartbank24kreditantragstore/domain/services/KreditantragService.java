package de.gothaer.smartbank24kreditantragstore.domain.services;


import de.gothaer.smartbank24kreditantragstore.domain.aggregates.Kreditantrag;

public interface KreditantragService {

    void speichereKreditantrag(Kreditantrag antrag) throws KreditantragServiceException;
    Kreditantrag.StatusWechsel verarbeitePositivesScoring(String id) throws KreditantragServiceException;
    Kreditantrag.StatusWechsel verarbeitePositivesCityScoring(String id) throws KreditantragServiceException;
    Kreditantrag.StatusWechsel verarbeiteNegativesScoring(String id) throws KreditantragServiceException;
    Kreditantrag.StatusWechsel verarbeiteNegativesCityScoring(String id) throws KreditantragServiceException;
    Kreditantrag findeKreditantragMitId(String id) throws KreditantragServiceException;
}
