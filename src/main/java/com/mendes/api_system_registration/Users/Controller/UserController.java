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
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    // Recebe um DTO no corpo da requisição, envia para o service e retorna o usuario criado
    @PostMapping("/create")
    public UserModel createUser(@RequestBody UsersDTO userDTO) {

        //1 - chama o metodo do service que converte o DTO em entidade e salva no banco
        return userService.createUser(userDTO);
    }

    @PutMapping("/edit/{id}")
    public UserModel editUser(@PathVariable Long id, @RequestBody UserModel userDetails) {
        return userService.editUser(id, userDetails);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}