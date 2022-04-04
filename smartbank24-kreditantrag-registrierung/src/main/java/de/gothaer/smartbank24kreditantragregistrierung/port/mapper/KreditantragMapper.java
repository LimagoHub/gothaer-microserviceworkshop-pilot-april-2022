package de.gothaer.smartbank24kreditantragregistrierung.port.mapper;

import de.gothaer.smartbank24kreditantragregistrierung.adapter.controllers.dtos.KreditantragDTO;
import de.gothaer.smartbank24kreditantragregistrierung.port.repositories.entities.KreditantragEntity;
import de.gothaer.smartbank24kreditantragregistrierung.services.model.Kreditantrag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KreditantragMapper {
    Kreditantrag convert(KreditantragEntity kreditantrag);
    KreditantragEntity convert(Kreditantrag kreditantrag);

    Iterable<Kreditantrag> convert(Iterable<KreditantragEntity> antraege);
}
