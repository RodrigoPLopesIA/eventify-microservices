package br.com.rodrigo.ms.event_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.ms.event_service.dtos.RequestEventDTO;
import br.com.rodrigo.ms.event_service.dtos.ResponseEventDTO;
import br.com.rodrigo.ms.event_service.entities.Event;
import br.com.rodrigo.ms.event_service.services.EventService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;


    @PostMapping
    public ResponseEntity<ResponseEventDTO> create(@Valid @RequestBody RequestEventDTO data, @AuthenticationPrincipal Jwt jwt) {
        String organizerId = jwt.getSubject();
        var event = eventService.createEvent(data, organizerId);
        return ResponseEntity.ok(event);
    }
        

    
    
}
