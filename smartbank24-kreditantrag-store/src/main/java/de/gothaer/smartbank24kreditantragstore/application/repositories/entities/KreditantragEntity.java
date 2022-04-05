package de.gothaer.smartbank24kreditantragstore.application.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_kredit_antraege")
public class KreditantragEntity implements Serializable {

    @Id
    @Column(length = 36)
    private String creditApplicationId;
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

    @Version
    private long version;

	@Column(length = 30, nullable = false)
	private String kreditantragState;
}
