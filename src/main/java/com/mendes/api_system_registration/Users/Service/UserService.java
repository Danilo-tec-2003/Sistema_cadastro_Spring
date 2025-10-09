package com.mendes.api_system_registration.Users.Service;

import com.mendes.api_system_registration.Users.DTO.UsersDTO;
import com.mendes.api_system_registration.Users.Mapper.UsersMapper;
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

    @Autowired
    private UsersMapper usersMapper;

    // Método para listar todos os usuários
    public List<UserModel> getAllUsers() {
        return usersRepository.findAll();
    }

    //lista usuario por id
    public UserModel findUserById(Long id) {
        Optional<UserModel> userById = usersRepository.findById(id);
        return userById.orElse(null);
    }

    //Mapeando o DTO recebido e salvando no banco de dados
    public UserModel createUser (UsersDTO userDTO) {

        //1 - converte o objeto DTO em uma entidade Model usando o mapper
        UserModel userModel = usersMapper.toEntity(userDTO);

        //2 - salva a entidade convertida no banco de dados e retorna um objeto persistido
       return usersRepository.save(userModel);
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