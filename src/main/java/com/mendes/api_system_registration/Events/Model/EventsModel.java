package com.mendes.api_system_registration.Events.Model;

import com.mendes.api_system_registration.Users.Model.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_events")
public class EventsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "nm_name_event")
    private String nameEvent;

    @Column(name = "dt_date_event")
    private LocalDate dateEvent;

    @Column(name = "nm_description_event")
    private String descriptionEvent;

    // Relacionamento 1:N (um evento pode ter vários usuários vinculados)
    // "mappedBy" indica que o lado dono da relação é o atributo "events" dentro de UserModel
    @OneToMany(mappedBy = "events")
    private List<UserModel> users;


}
