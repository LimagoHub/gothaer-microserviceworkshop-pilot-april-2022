package de.gothaer.smartbank24kreditantragregistrierung.port.repositories.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_kreditantraege")
public class KreditantragEntity {
    private static final long serialVersionUID = 8144682724082102640L;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        KreditantragEntity that = (KreditantragEntity) o;
        return creditApplicationId != null && Objects.equals(creditApplicationId, that.creditApplicationId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
