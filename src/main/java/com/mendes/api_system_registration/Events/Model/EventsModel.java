package com.mendes.api_system_registration.Events.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mendes.api_system_registration.Users.Model.User;
import jakarta.persistence.*;
import lombok.*; // Importações do Lombok atualizadas

import java.time.LocalDate;
import java.util.List;

/**
 * Representa a entidade 'Evento' no banco de dados.
 * Esta classe mapeia a tabela 'tb_events' e contém todas as informações
 * pertinentes a um evento do sistema.
 */
// @Data foi substituído por anotações específicas para evitar loops em relacionamentos
@Getter
@Setter
@ToString(exclude = "users") // Evita recursão infinita no método toString()
@EqualsAndHashCode(exclude = "users") // Evita problemas de performance e lógica com o JPA
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_events")
public class EventsModel {

    /**
     * Identificador único do evento.
     * Gerado automaticamente pelo banco de dados (estratégia IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome único do evento.
     */
    @Column(unique = true, name = "nm_name_event")
    private String nameEvent;

    /**
     * Data de ocorrência do evento.
     */
    @Column(name = "dt_date_event")
    private LocalDate dateEvent;

    /**
     * Descrição detalhada sobre o propósito do evento.
     */
    @Column(name = "nm_description_event")
    private String descriptionEvent;

    /**
     * Relacionamento Um-para-Muitos com a entidade de Usuários.
     * Indica que um evento pode ter vários usuários associados (participantes).
     *
     * - "mappedBy": Especifica que a entidade 'User' é a dona do relacionamento,
     * através do seu campo 'events'.
     * - @JsonManagedReference: Marca este lado como o "pai" na serialização JSON.
     * Isso permite que a lista de usuários seja serializada junto com o evento,
     * enquanto evita um loop de referência com o @JsonBackReference em 'User'.
     */
    @OneToMany(mappedBy = "events")
    @JsonManagedReference
    private List<User> users;
}