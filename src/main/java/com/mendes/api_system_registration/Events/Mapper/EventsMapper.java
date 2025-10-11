package com.mendes.api_system_registration.Events.Mapper;

import com.mendes.api_system_registration.Events.DTO.EventsDTO;
import com.mendes.api_system_registration.Events.Model.EventsModel;
import com.mendes.api_system_registration.Users.DTO.UserSummaryDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventsMapper {

    // - pega o atributo do Model e atribui a entidade encapsulada do DTO
    public EventsModel toEntity(EventsDTO eventsDTO) {
        if (eventsDTO == null) {
            return null;
        }

        EventsModel eventsModel = new EventsModel();

        eventsModel.setId(eventsDTO.getId());
        eventsModel.setNameEvent(eventsDTO.getNameEvent());
        eventsModel.setDateEvent(eventsDTO.getDateEvent());
        eventsModel.setDescriptionEvent(eventsDTO.getDescriptionEvent());

        return eventsModel;
    }

    //** mapeando, pega o DTO e trasnforma em uma entidade
    public EventsDTO toDTO(EventsModel eventsModel) {
        if (eventsModel == null) {
            return null;
        }

        EventsDTO eventsDTO = new EventsDTO();

        eventsDTO.setId(eventsModel.getId());
        eventsDTO.setNameEvent(eventsModel.getNameEvent());
        eventsDTO.setDateEvent(eventsModel.getDateEvent());
        eventsDTO.setDateEvent(eventsModel.getDateEvent());
        eventsDTO.setDescriptionEvent(eventsModel.getDescriptionEvent());

        // Verifica se o evento possui usuários associados.
        // Para cada usuário, cria um UserSummaryDTO contendo apenas id, nome e cidade,
        // e adiciona a lista de resumos ao EventsDTO.
        if (eventsModel.getUsers() != null) {
            List<UserSummaryDTO> userSummaries = eventsModel.getUsers().stream()
                    .map(user -> new UserSummaryDTO(
                            user.getId(),
                            user.getName(),
                            user.getCity()
                    ))
                    .collect(Collectors.toList());

            eventsDTO.setUsers(userSummaries);
        }
        return eventsDTO;
    }
}
