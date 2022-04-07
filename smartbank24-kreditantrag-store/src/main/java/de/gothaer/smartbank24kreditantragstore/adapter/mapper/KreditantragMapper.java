package de.gothaer.smartbank24kreditantragstore.adapter.mapper;

import de.gothaer.smartbank24kreditantragstore.application.repositories.entities.KreditantragEntity;
import de.gothaer.smartbank24kreditantragstore.domain.aggregates.Kreditantrag;
import de.gothaer.smartbank24kreditantragstore.domain.aggregates.KreditantragStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KreditantragMapper {

    KreditantragEntity convert(Kreditantrag kreditantrag);


    Kreditantrag convert(KreditantragEntity kreditantragEntity);


    Iterable<Kreditantrag> convert(Iterable<KreditantragEntity> kreditantragEntity);

    default String toString(KreditantragStatus status){
        return status.name();
    }
    default KreditantragStatus toEnum(String code){
        for (KreditantragStatus possibleResult : KreditantragStatus.values()) {
            if(possibleResult.name().equals(code)){
                return possibleResult;
            }
        }
        return null;
    }
}
