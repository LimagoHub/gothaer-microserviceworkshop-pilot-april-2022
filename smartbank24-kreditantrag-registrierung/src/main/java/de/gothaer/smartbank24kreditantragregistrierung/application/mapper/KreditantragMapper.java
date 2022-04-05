package de.gothaer.smartbank24kreditantragregistrierung.application.mapper;


import de.gothaer.smartbank24kreditantragregistrierung.application.repositories.entities.KreditantragEntity;
import de.gothaer.smartbank24kreditantragregistrierung.domain.model.Kreditantrag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KreditantragMapper {
    Kreditantrag convert(KreditantragEntity kreditantrag);
    KreditantragEntity convert(Kreditantrag kreditantrag);

    Iterable<Kreditantrag> convert(Iterable<KreditantragEntity> antraege);
}
