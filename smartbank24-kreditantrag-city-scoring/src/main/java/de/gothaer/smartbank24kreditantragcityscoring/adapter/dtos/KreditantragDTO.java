package de.gothaer.smartbank24kreditantragcityscoring.adapter.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder



public class KreditantragDTO implements Serializable {

    private static final long serialVersionUID = 8144682724082102640L;



    @Builder.Default
    @Size(min = 36, max = 36)
    @NotNull
    private String creditApplicationId = UUID.randomUUID().toString();

    @NotNull
    @Size(min = 2, max = 51, message = "bitte min. 2 Zeichen eingeben")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 51,  message = "bitte min. 2 Zeichen eingeben")
    @NotBlank
    private String lastName;

    @Size(min = 2, max = 51, message = "bitte min. 2 Zeichen eingeben")
    @NotNull
    private String city;

    @Builder.Default
    private LocalDateTime applicationDate = LocalDateTime.now();

    @Min(value = 0)
    @DecimalMin(inclusive = false , message = "darf nicht negativ oder leer (0) sein", value = "0")
    @NotNull
    private double monthlyIncome;
    @Min(value = 0)
    private double monthlyExpenditure;
    @Min(value = 0)
    @DecimalMin(inclusive = false , message = "darf nicht negativ oder leer (0) sein", value = "0")
    private double creditSum;


}
