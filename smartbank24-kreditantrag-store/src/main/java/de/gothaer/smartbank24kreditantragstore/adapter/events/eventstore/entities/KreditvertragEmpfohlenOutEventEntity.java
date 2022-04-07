package de.gothaer.smartbank24kreditantragstore.adapter.events.eventstore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("KreditVertragEmpfohlenOut")
public class KreditvertragEmpfohlenOutEventEntity extends AbstractEventEntity{


    @Column(length = 51)
    private String firstName;
    @Column(length = 51)
    private String lastName;
    @Column(length = 51)
    private String city;
    private LocalDateTime applicationDate;
    private double monthlyIncome;
    private double monthlyExpenditure;
    private double creditSum;
}