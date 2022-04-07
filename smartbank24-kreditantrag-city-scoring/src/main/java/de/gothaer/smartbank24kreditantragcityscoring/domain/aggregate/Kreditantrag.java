package de.gothaer.smartbank24kreditantragcityscoring.domain.aggregate;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Kreditantrag {

    private String creditApplicationId ;
    private String city;

    public boolean isKreditwuerdig() {
        return ! ("münchen".equalsIgnoreCase(city) || "muenchen".equalsIgnoreCase(city));
    }

}
