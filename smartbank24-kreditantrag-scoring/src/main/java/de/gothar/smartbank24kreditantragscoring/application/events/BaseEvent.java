package de.gothar.smartbank24kreditantragscoring.application.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseEvent {
    @Builder.Default
    private String eventID = UUID.randomUUID().toString();
    @Builder.Default
    private LocalDateTime eventTimestamp = LocalDateTime.now();
}
