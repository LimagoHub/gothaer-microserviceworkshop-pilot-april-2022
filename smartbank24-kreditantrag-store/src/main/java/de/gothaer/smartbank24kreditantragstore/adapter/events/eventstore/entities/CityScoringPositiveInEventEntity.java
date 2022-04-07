package de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("CityScoringPositiveIn")
public class CityScoringPositiveInEventEntity extends AbstractEventEntity{

}
