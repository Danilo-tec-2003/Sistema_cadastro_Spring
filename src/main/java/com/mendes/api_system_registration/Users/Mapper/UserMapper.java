package com.mendes.api_system_registration.Users.Mapper;

import com.mendes.api_system_registration.Users.DTO.UsersDTO;
import com.mendes.api_system_registration.Users.DTO.UserSummaryDTO;
import com.mendes.api_system_registration.Users.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Interface de Mapper para conversão entre a entidade {@link User} e seus DTOs.
 * <p>
 * Utiliza a biblioteca MapStruct para gerar a implementação em tempo de compilação,
 * eliminando a necessidade de código manual, repetitivo e propenso a erros.
 * <p>
 * A anotação {@code @Mapper(componentModel = "spring")} instrui o MapStruct a
 * criar uma implementação que é um Bean do Spring, permitindo sua injeção em
 * outras classes, como o {@code UserService}.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Converte uma entidade {@link User} para um {@link UserSummaryDTO}.
     * Usado para listas, onde apenas dados essenciais são necessários.
     *
     * @param user A entidade {@link User} a ser convertida.
     * @return O {@link UserSummaryDTO} correspondente.
     */
    UserSummaryDTO toSummaryDTO(User user);

    /**
     * Converte uma entidade {@link User} para um {@link UsersDTO} detalhado.
     * <p>
     * As anotações {@code @Mapping} são usadas para mapear campos de objetos aninhados
     * ou com nomes diferentes. Neste caso, os dados do evento associado são
     * transferidos para campos primitivos no DTO.
     *
     * @param user A entidade {@link User} a ser convertida.
     * @return O {@link UsersDTO} correspondente.
     */
    @Mapping(source = "events.id", target = "eventId")
    @Mapping(source = "events.nameEvent", target = "eventName")
    UsersDTO toDTO(User user);

    /**
     * Converte um {@link UsersDTO} para uma entidade {@link User}.
     * <p>
     * A anotação {@code @Mapping(target = "events", ignore = true)} é crucial
     * para evitar que o MapStruct tente mapear o DTO para o objeto complexo de Evento.
     * A lógica de buscar e associar a entidade de evento pelo {@code eventId}
     * deve ser responsabilidade da camada de serviço (Service).
     *
     * @param userDTO O {@link UsersDTO} a ser convertido.
     * @return A entidade {@link User} correspondente.
     */
    @Mapping(target = "events", ignore = true)
    User toEntity(UsersDTO userDTO);
}