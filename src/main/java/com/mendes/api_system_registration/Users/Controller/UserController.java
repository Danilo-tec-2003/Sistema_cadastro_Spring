package com.mendes.api_system_registration.Users.Controller;

import com.mendes.api_system_registration.Users.DTO.UsersDTO;
import com.mendes.api_system_registration.Users.Model.UserModel;
import com.mendes.api_system_registration.Users.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public List<UsersDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UsersDTO findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    // Recebe um DTO no corpo da requisição, envia para o Service e retorna o usuário criado
    @PostMapping("/create")
    public UsersDTO createUser(@RequestBody UsersDTO userDTO) {

        //1 - chama o metodo do Service que converte o DTO em entidade e salva no banco
        //   depois retorna o DTO do usuário criado
        return userService.createUser(userDTO);
    }

    // Recebe o ID como parâmetro e um DTO com os novos dados, chama o Service e retorna o DTO atualizado
    @PutMapping("/edit/{id}")
    public UsersDTO editUser(@PathVariable Long id, @RequestBody UsersDTO userDetails) {

        //1 - chama o Service que atualiza o usuário e retorna o DTO atualizado
        return userService.editUser(id, userDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}