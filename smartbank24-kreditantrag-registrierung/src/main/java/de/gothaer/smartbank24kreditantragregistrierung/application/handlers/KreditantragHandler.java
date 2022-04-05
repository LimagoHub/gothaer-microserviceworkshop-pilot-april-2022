package de.gothaer.smartbank24kreditantragregistrierung.application.handlers;

import de.gothaer.smartbank24kreditantragregistrierung.adapter.dtos.KreditantragDTO;
import de.gothaer.smartbank24kreditantragregistrierung.domain.services.KreditantragServiceException;

public interface KreditantragHandler {
    void handle(KreditantragDTO kreditantragDTO) throws KreditantragServiceException;
}
