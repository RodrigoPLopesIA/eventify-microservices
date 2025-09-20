package br.com.rodrigo.ms.event_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.rodrigo.ms.event_service.dtos.RequestEventDTO;
import br.com.rodrigo.ms.event_service.entities.Event;
import br.com.rodrigo.ms.event_service.models.Address;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "address", expression = "java(mapToAddress(dto))")
    Event toEvent(RequestEventDTO dto);
    

    default Address mapToAddress(RequestEventDTO dto) {
        if (dto == null) {
            return null;
        }
        return Address.builder()
                .street(dto.street())
                .number(dto.number())
                .complement(dto.complement())
                .neighborhood(dto.neighborhood())
                .city(dto.city())
                .state(dto.state())
                .postalCode(dto.postalCode())
                .build();
    }
}
