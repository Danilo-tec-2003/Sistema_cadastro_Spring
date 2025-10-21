package com.mendes.api_system_registration.Events.DTO;

import com.mendes.api_system_registration.Users.DTO.UserSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO (Data Transfer Object) para a representação detalhada de um Evento.
 * <p>
 * Esta classe é usada para transferir informações completas de um evento,
 * incluindo a lista de seus participantes (em formato resumido).
 * É ideal para respostas de endpoints como GET /events/{id}, POST /events e PUT /events/{id}.
 * <p>
 * Utiliza as anotações do Lombok para gerar automaticamente getters, setters, construtores, etc.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsDTO { // Nome ajustado para o singular

    /**
     * O identificador único do evento.
     */
    private Long id;

    /**
     * O nome oficial do evento.
     */
    private String nameEvent;

    /**
     * A data em que o evento ocorrerá.
     */
    private LocalDate dateEvent;

    /**
     * Uma descrição detalhada sobre o evento.
     */
    private String descriptionEvent;

    /**
     * A lista de usuários participantes deste evento.
     * <p>
     * Utiliza {@link UserSummaryDTO} para expor apenas os dados essenciais
     * dos usuários, prevenindo loops de serialização e exposição de dados sensíveis.
     */
    private List<UserSummaryDTO> users;
}