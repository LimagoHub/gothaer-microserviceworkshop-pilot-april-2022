package de.gothaer.smartbank24kreditantragregistrierung.application.events;



import de.gothaer.smartbank24kreditantragregistrierung.adapter.dtos.KreditantragDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class KreditantragRegistriertEvent extends BaseEvent{
    private KreditantragDTO kreditantrag;
}
