package com.mendes.api_system_registration.Events.DTO;

import com.mendes.api_system_registration.Users.DTO.UserSummaryDTO;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "O nome do evento é obrigatório.")
    @Size(min = 5, message = "O nome do evento deve ter no mínimo 5 caracteres.")
    private String nameEvent;

    /**
     * A data em que o evento ocorrerá.
     */
    @NotNull(message = "A data do evento é obrigatória.")
    @FutureOrPresent(message = "Data inválida: não é permitido registrar um evento em uma data passada.")
    private LocalDate dateEvent;

    /**
     * Uma descrição detalhada sobre o evento.
     */
    @NotBlank(message = "A descrição do evento é obrigatória.")
    @Size(min = 10, message = "A descrição do evento deve ter no mínimo 10 caracteres.")
    private String descriptionEvent;

    /**
     * A lista de usuários participantes deste evento.
     * <p>
     * Utiliza {@link UserSummaryDTO} para expor apenas os dados essenciais
     * dos usuários, prevenindo loops de serialização e exposição de dados sensíveis.
     */
    private List<UserSummaryDTO> users;
}