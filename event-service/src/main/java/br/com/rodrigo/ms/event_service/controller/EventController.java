package br.com.rodrigo.ms.event_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigo.ms.event_service.dtos.RequestEventDTO;
import br.com.rodrigo.ms.event_service.dtos.ResponseEventDTO;
import br.com.rodrigo.ms.event_service.entities.Event;
import br.com.rodrigo.ms.event_service.exceptions.ResponseErrorDTO;
import br.com.rodrigo.ms.event_service.services.EventService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/events")
@Slf4j
public class EventController {

    @Autowired
    private EventService eventService;


    @GetMapping
    public Page<ResponseEventDTO> index(Pageable page) {
        return eventService.index(page);
    }
    
    @PostMapping
    public ResponseEntity<ResponseEventDTO> create(@Valid @RequestBody RequestEventDTO data, @AuthenticationPrincipal Jwt jwt) {
        String organizerId = jwt.getSubject();
        var event = eventService.createEvent(data, organizerId);
        return ResponseEntity.ok(event);
    }
    
    @RateLimiter(name = "default")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseEventDTO> show(@PathVariable("id") String id) {
        log.info("Request received for GET /events/{}", id);
        ResponseEventDTO event = eventService.findById(id);
        return ResponseEntity.ok(event);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseEventDTO> update(
            @PathVariable("id") String id,
            @Valid @RequestBody RequestEventDTO data,
            @AuthenticationPrincipal Jwt jwt) {

        String organizerId = jwt.getSubject();
        ResponseEventDTO updatedEvent = eventService.updateEvent(id, data, organizerId);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id, @AuthenticationPrincipal Jwt jwt) {
        String organizerId = jwt.getSubject();
        eventService.deleteEvent(id, organizerId);
        return ResponseEntity.noContent().build();
    }
    

    
    
}
