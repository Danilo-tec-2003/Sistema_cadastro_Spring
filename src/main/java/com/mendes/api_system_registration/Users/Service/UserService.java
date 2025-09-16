package com.mendes.api_system_registration.Users.Service;

import com.mendes.api_system_registration.Users.Model.UserModel;
import com.mendes.api_system_registration.Users.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    // Método para listar todos os usuários
    public List<UserModel> getAllUsers() {
        return usersRepository.findAll();
    }
}
