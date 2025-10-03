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

    @GetMapping("/{id}")
    public UserModel findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/create")
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public String updateUser() {
        return "Alterando usuário por ID.";
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}





