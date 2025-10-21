package com.mendes.api_system_registration.Users.Controller;

import com.mendes.api_system_registration.Users.DTO.UsersDTO;
import com.mendes.api_system_registration.Users.DTO.UserSummaryDTO;
import com.mendes.api_system_registration.Users.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller REST para gerenciar os recursos de Usuários.
 * <p>
 * Expõe endpoints para realizar operações CRUD (Criar, Ler, Atualizar, Deletar)
 * em usuários, seguindo as convenções da arquitetura RESTful.
 */
@RestController
@RequestMapping("/users") // Define a rota base para todos os endpoints deste controller
public class UserController {

    private final UserService userService;

    /**
     * Constrói o UserController com a injeção do UserService.
     * A injeção via construtor é a prática recomendada pelo Spring.
     *
     * @param userService O serviço que contém a lógica de negócio para usuários.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para buscar todos os usuários cadastrados.
     * <p>
     * <strong>GET /users</strong>
     *
     * @return Um {@link ResponseEntity} com status 200 OK e a lista de {@link UserSummaryDTO} no corpo.
     * A lista estará vazia se não houver usuários, mas o status continuará sendo 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<UserSummaryDTO>> getAllUsers() {
        List<UserSummaryDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint para buscar um usuário específico pelo seu ID.
     * <p>
     * <strong>GET /users/{id}</strong>
     *
     * @param id O ID do usuário a ser buscado.
     * @return Um {@link ResponseEntity} com status 200 OK e o {@link UsersDTO} do usuário no corpo.
     * Retornará 404 Not Found (automaticamente, via exceção no Service) se o usuário não existir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> findUserById(@PathVariable Long id) {
        UsersDTO user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Endpoint para criar um novo usuário.
     * <p>
     * <strong>POST /users</strong>
     *
     * @param userDTO O {@link UsersDTO} contendo os dados do novo usuário, enviado no corpo da requisição.
     * @return Um {@link ResponseEntity} com status 201 Created. O corpo da resposta contém o
     * usuário recém-criado e o cabeçalho 'Location' contém a URL para acessá-lo.
     */
    @PostMapping
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO userDTO) {
        UsersDTO newUser = userService.createUser(userDTO);

        // Constrói a URI do novo recurso criado para retornar no header 'Location'
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(newUser);
    }

    /**
     * Endpoint para atualizar um usuário existente.
     * <p>
     * <strong>PUT /users/{id}</strong>
     *
     * @param id          O ID do usuário a ser atualizado.
     * @param userDetails O {@link UsersDTO} com os novos dados, enviado no corpo da requisição.
     * @return Um {@link ResponseEntity} com status 200 OK e o {@link UsersDTO} atualizado no corpo.
     * Retornará 404 Not Found se o usuário não existir.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsersDTO> editUser(@PathVariable Long id, @RequestBody UsersDTO userDetails) {
        UsersDTO updatedUser = userService.editUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Endpoint para deletar um usuário pelo seu ID.
     * <p>
     * <strong>DELETE /users/{id}</strong>
     *
     * @param id O ID do usuário a ser deletado.
     * @return Um {@link ResponseEntity} com status 204 No Content, indicando que a operação
     * foi bem-sucedida e não há conteúdo a ser retornado.
     * Retornará 404 Not Found se o usuário não existir.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}