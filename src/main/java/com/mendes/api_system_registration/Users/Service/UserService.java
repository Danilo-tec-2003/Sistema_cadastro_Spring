package com.mendes.api_system_registration.Users.Service;

import com.mendes.api_system_registration.Users.Model.UserModel;
import com.mendes.api_system_registration.Users.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    // Método para listar todos os usuários
    public List<UserModel> getAllUsers() {
        return usersRepository.findAll();
    }

    //lista usuario por id
    public UserModel findUserById(Long id) {
        Optional<UserModel> userById = usersRepository.findById(id);
                return userById.orElse(null);
    }

    //criar usuario
    public UserModel createUser (UserModel user) {
        return usersRepository.save(user);
    }

    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }

    public UserModel editUser(Long id, UserModel userDetails) {
        UserModel user = usersRepository.findById(id).orElse(null);

        if (usersRepository.existsById(id)) {
            userDetails.setId(id);
            return usersRepository.save(userDetails);
        }
        return null;
    }
}
