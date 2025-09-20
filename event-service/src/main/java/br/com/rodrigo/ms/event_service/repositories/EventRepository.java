package br.com.rodrigo.ms.event_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigo.ms.event_service.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    
}
