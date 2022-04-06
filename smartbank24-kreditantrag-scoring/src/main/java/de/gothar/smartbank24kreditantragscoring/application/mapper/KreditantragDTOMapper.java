package de.gothar.smartbank24kreditantragscoring.application.mapper;

import de.gothar.smartbank24kreditantragscoring.application.KreditantragDTO;
import de.gothar.smartbank24kreditantragscoring.domain.aggregates.Kreditantrag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KreditantragDTOMapper {

    Kreditantrag convert(KreditantragDTO kreditantragDTO);
}
