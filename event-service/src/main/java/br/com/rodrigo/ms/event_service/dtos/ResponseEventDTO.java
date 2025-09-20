package br.com.rodrigo.ms.event_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseEventDTO(
    UUID id, 
    String name,
    String description,
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String state,
    String postalCode,
    String organizerId,
    LocalDateTime startTime,
    LocalDateTime endTime,
    Instant createdAt,
    Instant updatedAt
) {

}
