package com.mendes.api_system_registration.Users.Model;

import com.mendes.api_system_registration.Events.Model.EventsModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_registration")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String name;
     private int age;
     private  String city;
     private String profession;

     @Column(unique = true)
     private String cpf;

     // Um usuário pertence a apenas um evento
     @ManyToOne
     @JoinColumn(name = "events_id") // Foreing Key ou chave estrangeira,  // Define a chave estrangeira que relaciona usuários e eventos
     private EventsModel events;


}

