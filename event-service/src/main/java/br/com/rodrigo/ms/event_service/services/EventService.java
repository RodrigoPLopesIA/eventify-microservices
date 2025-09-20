package br.com.rodrigo.ms.event_service.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rodrigo.ms.event_service.dtos.RequestEventDTO;
import br.com.rodrigo.ms.event_service.dtos.ResponseEventDTO;
import br.com.rodrigo.ms.event_service.entities.Event;
import br.com.rodrigo.ms.event_service.mapper.EventMapper;
import br.com.rodrigo.ms.event_service.models.Address;
import br.com.rodrigo.ms.event_service.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;


    public Page<ResponseEventDTO> index(Pageable page) {
        return this.eventRepository.findAll(page).map(eventMapper::toResponseEventDTO);
    }
    @Transactional()
    public ResponseEventDTO createEvent(RequestEventDTO eventDTO, String organizerId) {
        Event event = eventMapper.toEvent(eventDTO);

        if (organizerId == null) throw new EntityNotFoundException("An event should have an organizer");
       
        event.setOrganizerId(organizerId);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toResponseEventDTO(savedEvent);   
    }
    @Transactional
    public ResponseEventDTO updateEvent(String id, RequestEventDTO data, String organizerId) {
        Event event = eventRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));

        if (!event.getOrganizerId().equals(organizerId)) {
            throw new IllegalArgumentException("You cannot update an event you do not own.");
        }

        var updatedData = eventMapper.updateEventFromDto(data, event);

        Event updatedEvent = eventRepository.save(updatedData);

        return eventMapper.toResponseEventDTO(updatedEvent);
    }
    public void deleteEvent(String id, String organizerId) {
        Event event = eventRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));

        if (!event.getOrganizerId().equals(organizerId)) {
            throw new IllegalArgumentException("You cannot delete an event you do not own.");
        }

        eventRepository.delete(event);

    }
    
    public ResponseEventDTO findById(String id) {
        Event event = eventRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));
        return eventMapper.toResponseEventDTO(event);
    }
}
