package de.gothaer.smartbank24kreditantragcityscoring.adapter.handler;


import de.gothaer.smartbank24kreditantragcityscoring.adapter.events.KreditantragEvent;

public interface KreditantragHandler {

    void handle(KreditantragEvent event);
}
