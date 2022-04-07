package de.gothaer.smartbank24kreditantragstore.adapter.servicehandler;


import de.gothaer.smartbank24kreditantragstore.adapter.events.KreditantragEvent;
import de.gothaer.smartbank24kreditantragstore.adapter.events.ScoringEvent;
import de.gothaer.smartbank24kreditantragstore.domain.services.KreditantragServiceException;

public interface KreditantragHandler {

    void handleKreditantragRegistriert(KreditantragEvent event) throws KreditantragServiceException;

    void handleScoringPositive(ScoringEvent event) throws KreditantragServiceException;

    void handleScoringNegative(ScoringEvent event) throws KreditantragServiceException;

    void handleCityScoringPositive(ScoringEvent event) throws KreditantragServiceException;

    void handleCityScoringNegative(ScoringEvent event) throws KreditantragServiceException;
}

