package com.mendes.api_system_registration.Events.Model;

import com.mendes.api_system_registration.Users.Model.UserModel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_events")
public class EventsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEvent;
    private LocalDate dateEvent;
    private String descriptionEvent;

    // Relacionamento 1:N (um evento pode ter vários usuários vinculados)
    // "mappedBy" indica que o lado dono da relação é o atributo "events" dentro de UserModel
    @OneToMany(mappedBy = "events")
    private List<UserModel> users;

    public EventsModel() {

    }

    public EventsModel(String nameEvent, LocalDate dateEvent, String descriptionEvent) {
        this.nameEvent = nameEvent;
        this.dateEvent = dateEvent;
        this.descriptionEvent = descriptionEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }
    public void setNameEvent(String nameEvent) {
        this.nameEvent =nameEvent;
    }

    public LocalDate getDateEvent () {
        return dateEvent;
    }
    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }
    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }
}
