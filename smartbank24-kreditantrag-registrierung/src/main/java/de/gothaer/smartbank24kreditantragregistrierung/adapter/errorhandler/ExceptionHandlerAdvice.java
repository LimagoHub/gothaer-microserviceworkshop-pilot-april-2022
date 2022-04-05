package de.gothaer.smartbank24kreditantragregistrierung.adapter.errorhandler;


import de.gothaer.smartbank24kreditantragregistrierung.domain.services.KreditantragServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler{

	public static final String TIMESTAMP = "timestamp";
	public static final String MESSAGE = "message";

	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, ex.getMessage());

        log.error("unerwarteter Fehler",ex);
        return ResponseEntity.badRequest().body(body);
    }
	
	@ExceptionHandler(KreditantragServiceException.class)
	public ResponseEntity<Object> handleKreditantragServiceException(KreditantragServiceException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, ex.getMessage());
        log.error("Fehler beim Verarbeiten des Kreditantrags", ex);// Wichtig
        return ResponseEntity.badRequest().body(body);
    }

	 @Override
	 public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

	        Map<String, Object> body = new HashMap<>();
	        
	        
	        body.put(TIMESTAMP, LocalDateTime.now());
	        body.put("status", status.value());


	        List<String> errors = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .map(x -> x.getField() + " -> " + x.getDefaultMessage() )
	                .collect(Collectors.toList());

		 	body.put(MESSAGE, errors);
	        log.warn("Fehler beim Validieren",ex);
	        return ResponseEntity.badRequest().body(body);
	    }
	

}
