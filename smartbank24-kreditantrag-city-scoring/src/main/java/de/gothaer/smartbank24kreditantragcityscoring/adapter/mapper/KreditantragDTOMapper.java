package de.gothaer.smartbank24kreditantragcityscoring.adapter.mapper;


import de.gothaer.smartbank24kreditantragcityscoring.adapter.dtos.KreditantragDTO;
import de.gothaer.smartbank24kreditantragcityscoring.domain.aggregate.Kreditantrag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KreditantragDTOMapper {

    Kreditantrag convert(KreditantragDTO kreditantragDTO);
}
