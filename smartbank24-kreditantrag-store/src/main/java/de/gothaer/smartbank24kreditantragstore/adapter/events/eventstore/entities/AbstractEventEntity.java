package de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

@Entity
@Table(name="tbl_kreditantrag_events")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "eventtype", discriminatorType=DiscriminatorType.STRING, length=30)
public class AbstractEventEntity {

    @Id
    @Column(length = 36)
    private String eventID;

    private LocalDateTime eventTimestamp;

    @Column(name = "eventtype", insertable = false, updatable = false)
    private String eventtype;

    @Column(length = 36)
    private String creditApplicationId;
}
