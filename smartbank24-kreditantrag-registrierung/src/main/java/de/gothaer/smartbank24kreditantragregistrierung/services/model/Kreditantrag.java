package de.gothaer.smartbank24kreditantragregistrierung.services.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Kreditantrag {
    @Id
    @Column(length = 36)
    private String creditApplicationId ;

    @Column(length = 51)
    private String firstName;

    @Column(length = 51)
    private String lastName;

    @Column(length = 51)
    private String city;


    private LocalDateTime applicationDate ;


    private double monthlyIncome;

    private double monthlyExpenditure;

    private double creditSum;

}
