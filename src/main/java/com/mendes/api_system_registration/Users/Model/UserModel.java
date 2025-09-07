package com.mendes.api_system_registration.Users.Model;

import com.mendes.api_system_registration.Events.Model.EventsModel;
import jakarta.persistence.*;

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

     // Um usuário pertence a apenas um evento
     @ManyToOne
     @JoinColumn(name = "events_id") // Foreing Key ou chave estrangeira,  // Define a chave estrangeira que relaciona usuários e eventos
     private EventsModel events;

    //no-args constructor, Construtor padrao requerido pelo JPA
    public UserModel() {

    }

    // all-args constructor, Construtor de conveniencia para criar instancias
    public UserModel(String name, int age, String city, String profession) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.profession = profession;
    }

    // Métodos de acesso (getters e setters) para manipular os atributos da entidade
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}

