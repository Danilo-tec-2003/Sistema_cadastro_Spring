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
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersMapper usersMapper;

    // Busca todas as entidades no banco, converte cada uma em DTO e retorna a lista
    public List<UsersDTO> getAllUsers() {

        //1 - busca todos os usuarios como entidades no banco de dados
       List<UserModel>  users = usersRepository.findAll();

       //2 - converte cada UserModel em UsersDTO usando o mapper e coleta em uma lista
       return users.stream()
               .map(usersMapper::toDTO)
               .collect(Collectors.toList());
    }

    // Recebe um ID, busca a entidade no banco e retorna como DTO
    public UsersDTO findUserById(Long id) {

        //1 - busca o usuário no banco pelo ID, retorna Optional<UserModel>
        Optional<UserModel> userById = usersRepository.findById(id);

        //2 - se o usuário existir, converte para DTO usando o mapper; caso contrário, retorna null
        return userById.map(usersMapper::toDTO).orElse(null);
    }

    //Mapeando o DTO recebido e salvando no banco de dados
    public UsersDTO createUser(UsersDTO userDTO) {

        //1 - converte o DTO em entidade
        UserModel userModel = usersMapper.toEntity(userDTO);

        //2 - salva no banco
        UserModel savedUser = usersRepository.save(userModel);

        //3 - converte a entidade salva de volta em DTO e retorna
        return usersMapper.toDTO(savedUser);
    }

    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }

    //** Metodo para editar/atualizar um usuário
// Recebe um ID e um DTO com os novos dados, atualiza a entidade no banco e retorna o DTO atualizado
    public UsersDTO editUser(Long id, UsersDTO userDetails) {

        //1 - busca o usuário no banco pelo ID
        Optional<UserModel> existingUser = usersRepository.findById(id);

        if (existingUser.isPresent()) {

            //2 - converte o DTO recebido em entidade
            UserModel updatedUser = usersMapper.toEntity(userDetails);

            //3 - garante que o ID da entidade seja o mesmo do usuário que queremos atualizar
            updatedUser.setId(id);

            //4 - salva a entidade atualizada no banco
            UserModel savedUser = usersRepository.save(updatedUser);

            //5 - converte a entidade salva em DTO e retorna
            return usersMapper.toDTO(savedUser);
        }

        //6 - se não encontrar o usuário pelo ID, retorna null
        return null;
    }

}