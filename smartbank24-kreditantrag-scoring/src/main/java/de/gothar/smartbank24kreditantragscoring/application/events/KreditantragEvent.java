package de.gothar.smartbank24kreditantragscoring.application.events;



import de.gothar.smartbank24kreditantragscoring.application.KreditantragDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KreditantragEvent extends BaseEvent {
    private KreditantragDTO kreditantrag;
}
