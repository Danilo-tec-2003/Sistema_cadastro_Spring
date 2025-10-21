package com.mendes.api_system_registration.Users.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) para uma representação resumida do usuário.
 * <p>
 * Esta classe é projetada para ser usada em cenários onde apenas informações essenciais
 * do usuário são necessárias, como em listas de usuários. Isso melhora a performance
 * ao reduzir a quantidade de dados transferidos e evita a exposição de informações
 * desnecessárias ou sensíveis.
 * <p>
 * Utiliza as anotações do Lombok para gerar automaticamente getters, setters, construtores, etc.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserSummaryDTO {

    private Long id;
    private String name;
    private String city;
    private String urlImg;

    public UserSummaryDTO(Long id, String name, String city) {
    }
}
