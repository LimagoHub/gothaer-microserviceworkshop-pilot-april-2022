package de.gothaer.smartbank24kreditantragcityscoring.adapter.mapper;

import de.gothaer.smartbank24kreditantragcityscoring.adapter.dtos.KreditantragDTO;
import de.gothaer.smartbank24kreditantragcityscoring.domain.aggregate.Kreditantrag;
import de.gothaer.smartbank24kreditantragcityscoring.domain.aggregate.Kreditantrag.KreditantragBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-07T11:26:23+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class KreditantragDTOMapperImpl implements KreditantragDTOMapper {

    @Override
    public Kreditantrag convert(KreditantragDTO kreditantragDTO) {
        if ( kreditantragDTO == null ) {
            return null;
        }

        KreditantragBuilder kreditantrag = Kreditantrag.builder();

        kreditantrag.creditApplicationId( kreditantragDTO.getCreditApplicationId() );
        kreditantrag.city( kreditantragDTO.getCity() );

        return kreditantrag.build();
    }
}
