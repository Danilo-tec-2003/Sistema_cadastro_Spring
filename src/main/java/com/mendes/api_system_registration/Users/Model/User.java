package com.mendes.api_system_registration.Users.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mendes.api_system_registration.Events.Model.EventsModel; // Supondo que o nome correto da entidade seja EventsModel
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a entidade 'Usuário' no banco de dados.
 * Esta classe mapeia a tabela 'tb_users' e contém todas as informações
 * pertinentes a um usuário do sistema.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class User { // Nome da classe ajustado para o singular, seguindo a convenção

    /**
     * Identificador único do usuário.
     * Gerado automaticamente pelo banco de dados (estratégia IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome completo do usuário.
     */
    private String name;

    /**
     * Idade do usuário.
     */
    private Integer age;

    /**
     * Cidade de residência do usuário.
     */
    private String city;

    /**
     * Profissão do usuário.
     */
    private String profession;

    /**
     * Cadastro de Pessoa Física (CPF) do usuário.
     * É um campo único e não pode ser nulo, servindo como identificador natural.
     */
    @Column(unique = true, nullable = false)
    private String cpf;

    /**
     * URL para a imagem de perfil do usuário.
     */
    @Column(name = "url_img")
    private String urlImg;

    /**
     * Endereço de e-mail do usuário.
     * Utilizado para login e comunicação. É um campo único e não pode ser nulo.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Relacionamento Muitos-para-Um com a entidade de Eventos.
     * Indica que um usuário pode estar associado a um único evento.
     * - FetchType.LAZY: Otimiza a performance, carregando o evento apenas quando explicitamente solicitado.
     * - @JoinColumn: Especifica a coluna 'events_id' como a chave estrangeira nesta tabela.
     * - @JsonBackReference: Evita loops de serialização JSON, marcando este lado da relação como o "filho".
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "events_id")
    @JsonBackReference
    private EventsModel events;
}