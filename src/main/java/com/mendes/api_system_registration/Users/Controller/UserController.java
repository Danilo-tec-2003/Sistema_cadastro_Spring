package com.mendes.api_system_registration.Users.Controller;

import com.mendes.api_system_registration.Users.DTO.UsersDTO;
import com.mendes.api_system_registration.Users.Model.UserModel;
import com.mendes.api_system_registration.Users.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET /users/all → retorna todos os usuários
    @GetMapping("/all")
    public ResponseEntity <List>  getAllUsers() {
        List<UsersDTO> getUsers =  userService.getAllUsers();
        if (!getUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(getUsers);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }
    }

    // GET /users/{id} → retorna um usuário específico pelo ID
    @GetMapping("/{id}")
    public  ResponseEntity<UsersDTO> findUserById(@PathVariable Long id) {
        UsersDTO userById =  userService.findUserById(id);

        if (userById != null) {
            return ResponseEntity.ok(userById);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    // Recebe um DTO no corpo da requisição, envia para o Service e retorna o usuário criado
    // POST /users/create → cria um novo usuário
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UsersDTO userDTO) {

        // 1 - Recebe o objeto UsersDTO enviado no corpo da requisição (JSON)
        // 2 - Envia o DTO para o Service, onde ocorre:
        //     a) A conversão do DTO para entidade (UserModel)
        //     b) A persistência no banco de dados
        //     c) O retorno do DTO do usuário criado com seus dados atualizados (como o ID)
        UsersDTO newUser = userService.createUser(userDTO);

        // 3 - Retorna uma resposta HTTP 201 (Created) com uma mensagem personalizada (nome e ID)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario Criado com Sucesso: " + newUser.getName() + "(ID): " + newUser.getId());
    }

    // PUT /users/edit/{id} → atualiza usuário existente
    @PutMapping("/edit/{id}")
    public ResponseEntity <String> editUser (@PathVariable Long id, @RequestBody UsersDTO userDetails) {

        //1 - chama o Service que atualiza o usuário e retorna o DTO atualizado
        UsersDTO editUser = userService.editUser(id, userDetails);

        if (userService.findUserById(id) != null) {
            return ResponseEntity.ok(" Usuario com o ID " + id + "Foi alterado Com Sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(" ID: " + id + " Nao encontrado. ");
        }
    }

    // DELETE /users/delete/{id} → deleta usuário pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        if (userService.findUserById(id) != null) {
            userService.deleteUserById(id);
            return ResponseEntity.ok("Usuario com ID" + id + "deletado com Sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(" O Usuario com o id " + id + " Nao foi encontrado. ");
        }
    }
}