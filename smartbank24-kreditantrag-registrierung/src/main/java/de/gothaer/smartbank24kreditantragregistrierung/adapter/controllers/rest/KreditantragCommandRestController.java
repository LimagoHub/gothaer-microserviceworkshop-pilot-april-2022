package de.gothaer.smartbank24kreditantragregistrierung.adapter.controllers.rest;



import de.gothaer.smartbank24kreditantragregistrierung.adapter.dtos.KreditantragDTO;
import de.gothaer.smartbank24kreditantragregistrierung.application.handlers.KreditantragHandler;
import de.gothaer.smartbank24kreditantragregistrierung.domain.services.KreditantragServiceException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/api/v1/kreditantraege")
@AllArgsConstructor
public class KreditantragCommandRestController {

    private final KreditantragHandler kreditantragHandler;

    @ApiResponse(responseCode = "201", description = "Kreditantrag wurde registriert")
    @ApiResponse(responseCode = "400", description = "Bad Request (moeglicherweise bereits erfasst)" )
    @ApiResponse(responseCode = "500", description = "Interner Serverfehler")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@Valid @RequestBody KreditantragDTO kreditantragDTO) throws KreditantragServiceException {

        kreditantragHandler.handle(kreditantragDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
