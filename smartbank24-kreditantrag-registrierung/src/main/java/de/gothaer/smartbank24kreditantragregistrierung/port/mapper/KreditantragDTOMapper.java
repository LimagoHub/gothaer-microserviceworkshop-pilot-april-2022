package de.gothaer.smartbank24kreditantragregistrierung.port.mapper;

import de.gothaer.smartbank24kreditantragregistrierung.adapter.controllers.dtos.KreditantragDTO;
import de.gothaer.smartbank24kreditantragregistrierung.services.model.Kreditantrag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KreditantragDTOMapper {
    KreditantragDTO convert(Kreditantrag kreditantrag);
    Kreditantrag convert(KreditantragDTO kreditantrag);

    Iterable<KreditantragDTO> convert(Iterable<Kreditantrag> antraege);
}
