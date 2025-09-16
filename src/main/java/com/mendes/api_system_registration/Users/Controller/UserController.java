package com.mendes.api_system_registration.Users.Controller;

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

    @PostMapping("/create")
    public String createUser() {
        return "Adicionando um novo usuário.";
    }

    @GetMapping("/{id}")
    public String getUserById() {
        return "Buscando usuário por ID.";
    }

    @PutMapping("/{id}")
    public String updateUser() {
        return "Alterando usuário por ID.";
    }

    @DeleteMapping("/{id}")
    public String deleteUser() {
        return "Deletando usuário.";
    }
}





