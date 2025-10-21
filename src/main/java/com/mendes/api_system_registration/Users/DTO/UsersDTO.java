package com.mendes.api_system_registration.Users.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mendes.api_system_registration.Events.Model.EventsModel;
import lombok.Data;

/**
 * DTO (Data Transfer Object) que representa os dados de um usuário.
 * <p>
 * Esta classe é utilizada para transferir informações do usuário entre as camadas
 * da aplicação (ex: do Controller para o Service) e como corpo de requisições/respostas
 * da API, desacoplando a camada de apresentação da entidade de persistência {@link com.mendes.api_system_registration.Users.Model.User}.
 * <p>
 * Utiliza as anotações do Lombok {@code @Data} para gerar getters, setters, toString, etc.
 */
@Data

public class UsersDTO {

    private Long id;
    private String name;
    private Integer age;
    private String city;
    private String profession;
    private String email;
    private String urlImg;

    /**
     *
     * Para evitar que este campo seja exposto em respostas da API (ex: GET /users/{id}),
     * é recomendado usar a anotação {@code @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)}.
     * Isso permite que o CPF seja recebido em requisições (POST/PUT), mas nunca enviado em respostas.
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cpf;


    /**
     * O ID do evento ao qual o usuário está associado.
     * Utilizado para associar um usuário a um evento ao criar ou atualizar.
     */
    private Long eventId;

    /**
     * O nome do evento ao qual o usuário está associado.
     * Utilizado para exibir informações do evento nas respostas da API.
     */
    private String eventName;
}