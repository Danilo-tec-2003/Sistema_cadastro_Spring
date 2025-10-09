package com.mendes.api_system_registration.Users.Mapper;

import com.mendes.api_system_registration.Users.DTO.UsersDTO;
import com.mendes.api_system_registration.Users.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {


    public UserModel toEntity(UsersDTO usersDTO) {
        //** mapeando, pega a entidade e trasnforma em um DTO
        //1 - inicializa o objeto
        UserModel userModel = new UserModel();

        //2 - pega o atributo do Model e atribui a entidade encapsulada do DTO
        userModel.setId(usersDTO.getId());
        userModel.setName(usersDTO.getName());
        userModel.setAge(usersDTO.getAge());
        userModel.setCity(usersDTO.getCity());
        userModel.setProfession(usersDTO.getProfession());
        userModel.setEvents(usersDTO.getEvents());
        userModel.setEmail(usersDTO.getEmail());

        return userModel;
    }

    //** mapeando, pega o DTO e trasnforma em uma entidade
    public UsersDTO toDTO(UserModel userModel) {

        //1 - inicializa o objeto
        UsersDTO usersDTO = new UsersDTO();

        //2 - pega as entidades encapsulada do DTO e as trasnforma em entidades
        usersDTO.setId(userModel.getId());
        usersDTO.setName(userModel.getName());
        usersDTO.setAge(userModel.getAge());
        usersDTO.setCity(userModel.getCity());
        usersDTO.setProfession(userModel.getProfession());
        usersDTO.setEvents(userModel.getEvents());
        usersDTO.setEmail(userModel.getEmail());

        return usersDTO;
    }
}
