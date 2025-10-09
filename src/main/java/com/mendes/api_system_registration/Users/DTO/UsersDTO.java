package com.mendes.api_system_registration.Users.DTO;

import com.mendes.api_system_registration.Events.Model.EventsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//DTO
public class UsersDTO {

    private long id;
    private String name;
    private Integer age;
    private  String city;
    private String profession;
    private EventsModel events;
    private String email;

}

