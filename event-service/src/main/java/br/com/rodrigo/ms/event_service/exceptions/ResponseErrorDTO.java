package br.com.rodrigo.ms.event_service.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;

public record ResponseErrorDTO(String path, HttpStatus status, String message, Map<String, String> errors) {

}
