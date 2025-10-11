package com.mendes.api_system_registration.Users.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserSummaryDTO {

    private Long id;
    private String name;
    private  String city;

}
