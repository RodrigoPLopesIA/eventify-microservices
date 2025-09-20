package br.com.rodrigo.ms.event_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.rodrigo.ms.event_service.dtos.RequestEventDTO;
import br.com.rodrigo.ms.event_service.dtos.ResponseEventDTO;
import br.com.rodrigo.ms.event_service.entities.Event;
import br.com.rodrigo.ms.event_service.models.Address;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "address", expression = "java(mapToAddress(dto))")
    Event toEvent(RequestEventDTO dto);

    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "number", source = "address.number")
    @Mapping(target = "complement", source = "address.complement")
    @Mapping(target = "neighborhood", source = "address.neighborhood")
    @Mapping(target = "city", source = "address.city")
    @Mapping(target = "state", source = "address.state")
    @Mapping(target = "postalCode", source = "address.postalCode")
    ResponseEventDTO toResponseEventDTO(Event event);

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
