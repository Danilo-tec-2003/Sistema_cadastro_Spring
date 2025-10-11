package com.mendes.api_system_registration.Events.DTO;

import com.mendes.api_system_registration.Users.DTO.UserSummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EventsDTO {

    private Long id;
    private String nameEvent;
    private LocalDate dateEvent;
    private String descriptionEvent;
    private List<UserSummaryDTO> users;
}
