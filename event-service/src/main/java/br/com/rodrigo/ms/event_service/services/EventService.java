package br.com.rodrigo.ms.event_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.rodrigo.ms.event_service.dtos.RequestEventDTO;
import br.com.rodrigo.ms.event_service.dtos.ResponseEventDTO;
import br.com.rodrigo.ms.event_service.entities.Event;
import br.com.rodrigo.ms.event_service.mapper.EventMapper;
import br.com.rodrigo.ms.event_service.models.Address;
import br.com.rodrigo.ms.event_service.repositories.EventRepository;
import jakarta.transaction.Transactional;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventMapper eventMapper;

    @Transactional()
    public ResponseEventDTO createEvent(RequestEventDTO eventDTO, String organizerId) {
        Event event = eventMapper.toEvent(eventDTO);
        event.setOrganizerId(organizerId);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toResponseEventDTO(savedEvent);   
    }
}
