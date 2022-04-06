package de.gothaer.smartbank24kreditantragstore.application.handler;


import de.gothaer.smartbank24kreditantragstore.application.events.KreditantragEvent;
import de.gothaer.smartbank24kreditantragstore.application.events.ScoringEvent;
import de.gothaer.smartbank24kreditantragstore.domain.aggregates.Kreditantrag;
import de.gothaer.smartbank24kreditantragstore.domain.services.KreditantragServiceException;

public interface KreditantragHandler {

    void handleKreditantragRegistriert(KreditantragEvent event) throws KreditantragServiceException;

    void handleScoringPositive(ScoringEvent event) throws KreditantragServiceException;

    void handleScoringNegative(ScoringEvent event) throws KreditantragServiceException;

    void handleCityScoringPositive(ScoringEvent event) throws KreditantragServiceException;

    void handleCityScoringNegative(ScoringEvent event) throws KreditantragServiceException;

    void fireKreditantragPersitedEvent(Kreditantrag kreditantrag);

    void fireKreditantragAcceptedEvent(Kreditantrag kreditantrag);

    void fireKreditantragDeniedEvent(Kreditantrag kreditantrag);

    void fireKreditantragStornoEvent(Kreditantrag kreditantrag);
}

