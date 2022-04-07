package de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.mapper;


import de.gothaer.smartbank24kreditantragstore.adapter.events.KreditantragEvent;
import de.gothaer.smartbank24kreditantragstore.adapter.events.ScoringEvent;
import de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.entities.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventToEntityMapper {

    CityScoringNegativInEventEntity convertCityScoringNegativInEventEntity(ScoringEvent event);
    CityScoringPositiveInEventEntity convertCityScoringPositiveInEventEntity(ScoringEvent event);

    @Mapping(target = ".", source = "kreditantrag")
    KreditAntragPersistiertOutEventEntity convertKreditAntragPersistiertOutEventEntity(KreditantragEvent event);

    @Mapping(target = ".", source = "kreditantrag")
    KreditantragRegistriertInEventEntity convertKreditantragRegistriertInEventEntity(KreditantragEvent event);

    @Mapping(target = ".", source = "kreditantrag")
    KreditvertragAbgelehntOutEventEntity convertKreditvertragAbgelehntOutEventEntity(KreditantragEvent event);

    @Mapping(target = ".", source = "kreditantrag")
    KreditvertragEmpfohlenOutEventEntity convertKreditvertragEmpfohlenOutEventEntity(KreditantragEvent event);

    ScoringNegativInEventEntity convertScoringNegativInEventEntity(ScoringEvent event);
    ScoringPositiveInEventEntity convertScoringPositiveInEventEntity(ScoringEvent event);
}
