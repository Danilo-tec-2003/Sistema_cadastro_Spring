package com.mendes.api_system_registration.Events.Mapper;

import com.mendes.api_system_registration.Events.DTO.EventsDTO;
import com.mendes.api_system_registration.Events.DTO.EventsSummaryDTO;
import com.mendes.api_system_registration.Events.Model.EventsModel;
import com.mendes.api_system_registration.Users.Mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Interface de Mapper para conversão entre a entidade {@link EventsModel} e seus DTOs.
 * <p>
 * Utiliza a biblioteca MapStruct para gerar a implementação em tempo de compilação.
 * <p>
 * A anotação {@code @Mapper(componentModel = "spring", uses = {UserMapper.class})}
 * instrui o MapStruct a:
 * 1. Criar uma implementação que é um Bean do Spring.
 * 2. <strong>Usar o {@link UserMapper}</strong> sempre que precisar converter
 * qualquer coisa relacionada a Usuários (ex: List<User> para List<UserSummaryDTO>).
 * Isso elimina a necessidade de escrever métodos 'default' manuais.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EventsMapper {

    /**
     * Converte uma entidade {@link EventsModel} para um {@link EventsSummaryDTO} (DTO Resumido).
     * Usado para listas, onde apenas dados essenciais são necessários.
     *
     * @param eventsModel A entidade {@link EventsModel} a ser convertida.
     * @return O {@link EventsSummaryDTO} correspondente.
     */
    EventsSummaryDTO toSummaryDTO(EventsModel eventsModel);

    /**
     * Converte uma entidade {@link EventsModel} para um {@link EventsDTO} (DTO Detalhado).
     * <p>
     * O MapStruct automaticamente usará o {@link UserMapper} (definido no 'uses' da classe)
     * para converter a lista de {@code List<User>} para {@code List<UserSummaryDTO>}.
     * Não é necessário um @Mapping ou método default.
     *
     * @param eventsModel A entidade {@link EventsModel} a ser convertida.
     * @return O {@link EventsDTO} correspondente.
     */
    EventsDTO toDTO(EventsModel eventsModel);

    /**
     * Converte um {@link EventsDTO} (DTO) para uma entidade {@link EventsModel}.
     * <p>
     * <strong>CORREÇÃO DE BUG:</strong> A anotação {@code @Mapping(target = "users", ignore = true)}
     * é crucial. Ela impede que o MapStruct tente converter {@code List<UserSummaryDTO>}
     * (do DTO) para {@code List<User>} (da Entidade), o que causaria um erro.
     * <p>
     * A lógica de associar usuários a um evento deve ser feita manualmente na
     * camada de Serviço (Service).
     *
     * @param eventsDTO O {@link EventsDTO} a ser convertido.
     * @return A entidade {@link EventsModel} correspondente.
     */
    @Mapping(target = "users", ignore = true)
    EventsModel toEntity(EventsDTO eventsDTO);
}