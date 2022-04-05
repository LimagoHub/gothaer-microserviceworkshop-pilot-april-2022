package de.gothaer.smartbank24kreditantragregistrierung.application.handlers.impl;

import de.gothaer.smartbank24kreditantragregistrierung.adapter.dtos.KreditantragDTO;
import de.gothaer.smartbank24kreditantragregistrierung.application.events.KreditantragRegistriertEvent;
import de.gothaer.smartbank24kreditantragregistrierung.application.handlers.KreditantragHandler;
import de.gothaer.smartbank24kreditantragregistrierung.application.mapper.KreditantragDTOMapper;
import de.gothaer.smartbank24kreditantragregistrierung.domain.services.KreditantragService;
import de.gothaer.smartbank24kreditantragregistrierung.domain.services.KreditantragServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = KreditantragServiceException.class, propagation = Propagation.REQUIRED)
@AllArgsConstructor
@Slf4j
public class KreditantragHandlerImpl implements KreditantragHandler {

    private static final String SUPPLIER_BINDING_NAME = "kreditantrag-registriert-out-0";

    private final KreditantragService service;
    private final KreditantragDTOMapper mapper;
    private final StreamBridge streamBridge;

    @Override
    public void handle(KreditantragDTO kreditantragDTO) throws KreditantragServiceException {
        try {
            service.register(mapper.convert(kreditantragDTO));
            feuereKreditantragRegistriertEvent(kreditantragDTO);
            log.info("Kreditantrag mit der ID '{}' erfolgreich registriert.", kreditantragDTO.getCreditApplicationId());
        } catch (KreditantragServiceException e) {
            log.error("Fehler beim Registrieren des Kreditantrags mit der ID '{}'", kreditantragDTO.getCreditApplicationId(), e);
            throw e;
        }
    }

    private void feuereKreditantragRegistriertEvent(KreditantragDTO kreditantragDTO) {
        KreditantragRegistriertEvent event = KreditantragRegistriertEvent.builder().kreditantrag(kreditantragDTO).build();
        streamBridge.send(SUPPLIER_BINDING_NAME, event);
    }
}
