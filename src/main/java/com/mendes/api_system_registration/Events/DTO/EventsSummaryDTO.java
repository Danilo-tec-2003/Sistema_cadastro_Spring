package com.mendes.api_system_registration.Events.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) para uma representação resumida do Evento.
 * <p>
 * Esta classe é projetada para ser usada em cenários onde apenas informações essenciais
 * do evento são necessárias, como em listas de eventos. Isso melhora a performance
 * ao reduzir a quantidade de dados transferidos e evita a exposição de informações
 * desnecessárias (como a lista completa de participantes).
 * <p>
 * Utiliza as anotações do Lombok para gerar automaticamente getters, setters, construtores, etc.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsSummaryDTO {

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

}