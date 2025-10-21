package com.mendes.api_system_registration.Users.Repository;

import com.mendes.api_system_registration.Users.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de Repositório para a entidade {@link User}.
 * <p>
 * Esta interface estende {@link JpaRepository}, fornecendo automaticamente
 * métodos para operações CRUD (Criar, Ler, Atualizar, Deletar) e outras
 * consultas comuns ao banco de dados, sem a necessidade de implementação manual.
 *
 * @see JpaRepository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Nenhuma implementação é necessária aqui para as operações básicas.
    // O Spring Data JPA cria a implementação em tempo de execução.
    // Métodos de consulta customizados (ex: findByEmail, findByNameContaining) podem ser adicionados aqui.

}