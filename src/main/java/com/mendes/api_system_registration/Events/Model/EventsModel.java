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

    @Column(unique = true)
    private String nameEvent;

    private LocalDate dateEvent;
    private String descriptionEvent;

    // Relacionamento 1:N (um evento pode ter vários usuários vinculados)
    // "mappedBy" indica que o lado dono da relação é o atributo "events" dentro de UserModel
    @OneToMany(mappedBy = "events")
    private List<UserModel> users;


}
