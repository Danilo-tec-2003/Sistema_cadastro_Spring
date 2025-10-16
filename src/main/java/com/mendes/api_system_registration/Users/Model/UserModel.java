package com.mendes.api_system_registration.Users.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mendes.api_system_registration.Events.Model.EventsModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String name;
     private Integer age;
     private  String city;
     private String profession;

     @Column(unique = true, nullable = false) //CPF unico e nao nulo
     private String cpf;

     @Column(name = "url_img")
     private String urlImg;

    @Column(unique = true, nullable = false) //email unico e nao nulo
    private String email;

     // Um usuário pertence a apenas um evento
     @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY melhora a performance ao não carregar o evento a menos que seja necessário
     @JoinColumn(name = "events_id") // Foreing Key,  // Define Foreing Key que relaciona usuários e eventos
     @JsonBackReference //parte “filho” da relação, será ignorada na serialização (evita loop).
     private EventsModel events;


}

