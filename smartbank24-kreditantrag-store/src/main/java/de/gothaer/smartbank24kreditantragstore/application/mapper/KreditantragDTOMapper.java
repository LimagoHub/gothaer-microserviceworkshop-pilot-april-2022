package de.gothaer.smartbank24kreditantragstore.application.mapper;


import de.gothaer.smartbank24kreditantragstore.adapter.dtos.KreditantragDTO;
import de.gothaer.smartbank24kreditantragstore.domain.aggregates.Kreditantrag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KreditantragDTOMapper {

    @Mapping(target = "kreditantragState", ignore = true)
    Kreditantrag convert(KreditantragDTO dto);


    KreditantragDTO convert(Kreditantrag kreditantrag);


    Iterable<KreditantragDTO> convert(Iterable<Kreditantrag> kreditantragEntity);


}
