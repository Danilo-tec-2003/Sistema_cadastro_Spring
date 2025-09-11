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

    @Column(name = "nm_name")
     private String name;

    @Column(name = "nr_age")
     private Integer age;

    @Column(name = "nm_city")
     private  String city;

    @Column(name = "nm_profession")
     private String profession;

     @Column(unique = true)
     private String cpf;

     @Column(name = "url_img")
     private String urlImg;

     // Um usuário pertence a apenas um evento
     @ManyToOne
     @JoinColumn(name = "events_id") // Foreing Key ou chave estrangeira,  // Define a chave estrangeira que relaciona usuários e eventos
     private EventsModel events;


}

