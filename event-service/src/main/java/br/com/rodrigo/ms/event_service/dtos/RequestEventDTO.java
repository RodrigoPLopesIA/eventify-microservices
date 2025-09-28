package br.com.rodrigo.ms.event_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

public record RequestEventDTO(
    @NotBlank
    @Size(max = 255)
    String name,

    @Size(max = 1000)
    String description,

    @NotBlank
    @Size(max = 255)
    String street,

    @NotBlank
    @Size(max = 20)
    String number,

    @Size(max = 100)
    String complement,

    @NotBlank
    @Size(max = 100)
    String neighborhood,

    @NotBlank
    @Size(max = 100)
    String city,

    @NotBlank
    @Size(max = 100)
    String state,

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "Invalid postal code format")
    String postalCode,

    @NotNull
    @Future
    LocalDateTime startTime,

    @NotNull
    @Future
    LocalDateTime endTime
) {

}
