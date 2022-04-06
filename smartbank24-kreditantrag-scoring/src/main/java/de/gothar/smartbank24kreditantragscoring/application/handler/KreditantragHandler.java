package de.gothar.smartbank24kreditantragscoring.application.handler;

import de.gothar.smartbank24kreditantragscoring.application.events.KreditantragEvent;

public interface KreditantragHandler {

    void handle(KreditantragEvent event);
}
