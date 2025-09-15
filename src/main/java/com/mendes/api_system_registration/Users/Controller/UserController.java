package com.mendes.api_system_registration.Users.Controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/all")
    public String getAllUsers() {
        return "Retornando todos os usuários.";
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





